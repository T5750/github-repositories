package top.ibase4j.core.base;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import top.ibase4j.core.Constants;
import top.ibase4j.core.exception.BusinessException;
import top.ibase4j.core.util.CacheUtil;
import top.ibase4j.core.util.DataUtil;
import top.ibase4j.core.util.ExceptionUtil;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.PropertiesUtil;

/**
 * 业务逻辑层基类,继承基类后必须配置CacheConfig(cacheNames="")
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public abstract class BaseService<T extends BaseModel> implements ApplicationContextAware {
    protected Logger logger = LogManager.getLogger();
    @Autowired
    protected BaseMapper<T> mapper;

    protected ApplicationContext applicationContext;

    int maxThread = PropertiesUtil.getInt("db.reader.list.maxThread", 50);
    int threadSleep = PropertiesUtil.getInt("db.reader.list.threadWait", 5);
    ExecutorService executorService = Executors.newFixedThreadPool(maxThread);

    /**
     * 分页查询
     * @param params
     * @return
     */
    @SuppressWarnings({"unchecked"})
    public static Page<Long> getPage(Map<String, Object> params) {
        Integer current = 1;
        Integer size = 10;
        String orderBy = "id_", sortAsc = null, openSort = "Y";
        if (DataUtil.isNotEmpty(params.get("pageNumber"))) {
            current = Integer.valueOf(params.get("pageNumber").toString());
        }
        if (DataUtil.isNotEmpty(params.get("pageIndex"))) {
            current = Integer.valueOf(params.get("pageIndex").toString());
        }
        if (DataUtil.isNotEmpty(params.get("pageSize"))) {
            size = Integer.valueOf(params.get("pageSize").toString());
        }
        if (DataUtil.isNotEmpty(params.get("limit"))) {
            size = Integer.valueOf(params.get("limit").toString());
        }
        if (DataUtil.isNotEmpty(params.get("offset"))) {
            current = Integer.valueOf(params.get("offset").toString()) / size + 1;
        }
        if (DataUtil.isNotEmpty(params.get("sort"))) {
            orderBy = (String)params.get("sort");
            params.remove("sort");
        }
        if (DataUtil.isNotEmpty(params.get("orderBy"))) {
            orderBy = (String)params.get("orderBy");
            params.remove("orderBy");
        }
        if (DataUtil.isNotEmpty(params.get("sortAsc"))) {
            sortAsc = (String)params.get("sortAsc");
            params.remove("sortAsc");
        }
        if (DataUtil.isNotEmpty(params.get("openSort"))) {
            openSort = (String)params.get("openSort");
            params.remove("openSort");
        }
        Object filter = params.get("filter");
        if (filter != null) {
            params.putAll(JSON.parseObject(filter.toString(), Map.class));
        }
        if (size == -1) {
            Page<Long> page = new Page<Long>();
            page.setOrderByField(orderBy);
            page.setAsc("Y".equals(sortAsc));
            page.setOpenSort("Y".equals(openSort));
            return page;
        }
        Page<Long> page = new Page<Long>(current, size, orderBy);
        page.setAsc("Y".equals(sortAsc));
        page.setOpenSort("Y".equals(openSort));
        return page;
    }

    /**
     * @param id
     * @param userId
     */
    @Transactional
    public void del(Long id, Long userId) {
        try {
            T record = this.queryById(id);
            record.setEnable(0);
            record.setUpdateTime(new Date());
            record.setUpdateBy(userId);
            mapper.updateById(record);
            try {
                CacheUtil.getCache().set(getCacheKey(id), record);
            } catch (Exception e) {
                logger.error(Constants.Exception_Head, e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        try {
            mapper.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        try {
            CacheUtil.getCache().del(getCacheKey(id));
        } catch (Exception e) {
            logger.error(Constants.Exception_Head, e);
        }
    }

    /**
     * @param t
     * @return
     */
    @Transactional
    public Integer deleteByEntity(T t) {
        Wrapper<T> wrapper = new EntityWrapper<T>(t);
        return mapper.delete(wrapper);
    }

    /**
     * @param columnMap
     * @return
     */
    @Transactional
    public Integer deleteByMap(Map<String, Object> columnMap) {
        return mapper.deleteByMap(columnMap);
    }

    /**
     * 根据Id查询(默认类型T)
     * @param ids
     * @return
     */
    public List<T> getList(final List<Long> ids) {
        final List<T> list = InstanceUtil.newArrayList();
        if (ids != null) {
            for (int i = 0; i < ids.size(); i++) {
                list.add(null);
            }
            final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
            for (int i = 0; i < ids.size(); i++) {
                final int index = i;
                executorService.execute(new Runnable() {
                    public void run() {
                        try {
                            list.set(index, queryById(ids.get(index)));
                        } finally {
                            thread.put(index, 0);
                        }
                    }
                });
            }
            while (thread.size() < list.size()) {
                try {
                    Thread.sleep(threadSleep);
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
            }
        }
        return list;
    }

    /**
     * 根据Id查询(cls返回类型Class)
     * @param ids
     * @param cls
     * @return
     */
    public <K> List<K> getList(final List<Long> ids, final Class<K> cls) {
        final List<K> list = InstanceUtil.newArrayList();
        if (ids != null) {
            for (int i = 0; i < ids.size(); i++) {
                list.add(null);
            }
            final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
            for (int i = 0; i < ids.size(); i++) {
                final int index = i;
                executorService.execute(new Runnable() {
                    public void run() {
                        try {
                            T t = queryById(ids.get(index));
                            K k = InstanceUtil.to(t, cls);
                            list.set(index, k);
                        } finally {
                            thread.put(index, 0);
                        }
                    }
                });
            }
            while (thread.size() < list.size()) {
                try {
                    Thread.sleep(threadSleep);
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
            }
        }
        return list;
    }

    /**
     * 根据Id查询(默认类型T)
     * @param ids
     * @return
     */
    public Page<Map<String, Object>> getPageMap(final Page<Long> ids) {
        if (ids != null) {
            Page<Map<String, Object>> page = new Page<Map<String, Object>>(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            final List<Map<String, Object>> records = InstanceUtil.newArrayList();
            for (int i = 0; i < ids.getRecords().size(); i++) {
                records.add(null);
            }
            final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
            for (int i = 0; i < ids.getRecords().size(); i++) {
                final int index = i;
                executorService.execute(new Runnable() {
                    public void run() {
                        try {
                            records.set(index, InstanceUtil.transBean2Map(queryById(ids.getRecords().get(index))));
                        } finally {
                            thread.put(index, 0);
                        }
                    }
                });
            }
            while (thread.size() < records.size()) {
                try {
                    Thread.sleep(threadSleep);
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
            }
            page.setRecords(records);
            return page;
        }
        return new Page<Map<String, Object>>();
    }

    /**
     * @param params
     * @return
     */
    public Page<T> query(Map<String, Object> params) {
        Page<Long> page = getPage(params);
        page.setRecords(mapper.selectIdPage(page, params));
        return getPage(page);
    }

    /**
     * @param id
     * @return
     */
    @Transactional
    public T queryById(Long id) {
        return queryById(id, 1);
    }

    /**
     * @param params
     * @return
     */
    public List<T> queryList(Map<String, Object> params) {
        List<Long> ids = mapper.selectIdPage(params);
        List<T> list = getList(ids);
        return list;
    }

    /**
     * @param entity
     * @return
     */
    public List<T> selectList(Wrapper<T> entity) {
        return mapper.selectList(entity);
    }

    /**
     * @param entity
     * @return
     */
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.
     * ApplicationContext) */
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * @param record
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public T update(T record) {
        try {
            record.setUpdateTime(new Date());
            if (record.getId() == null) {
                record.setCreateTime(new Date());
                mapper.insert(record);
                try {
                    record = mapper.selectById(record.getId());
                    CacheUtil.getCache().set(getCacheKey(record.getId()), record);
                } catch (Exception e) {
                    logger.error(Constants.Exception_Head, e);
                }
            } else {
                String lockKey = getLockKey("U" + record.getId());
                if (CacheUtil.tryLock(lockKey)) {
                    try {
                        T org = null;
                        String key = getCacheKey(record.getId());
                        try {
                            org = (T)CacheUtil.getCache().getFire(key);
                        } catch (Exception e) {
                            logger.error(Constants.Exception_Head, e);
                        }
                        if (org == null) {
                            org = mapper.selectById(record.getId());
                        }

                        T update = InstanceUtil.getDiff(org, record);
                        update.setId(record.getId());
                        mapper.updateById(update);
                        record = mapper.selectById(record.getId());
                        try {
                            CacheUtil.getCache().set(key, record);
                        } catch (Exception e) {
                            logger.error(Constants.Exception_Head, e);
                        }
                    } finally {
                        CacheUtil.unlock(lockKey);
                    }
                } else {
                    throw new RuntimeException("数据不一致!请刷新页面重新编辑!");
                }
            }
        } catch (DuplicateKeyException e) {
            logger.error(Constants.Exception_Head, e);
            throw new BusinessException("已经存在相同的记录.");
        } catch (Exception e) {
            logger.error(Constants.Exception_Head, e);
            throw new RuntimeException(ExceptionUtil.getStackTraceAsString(e));
        }
        return record;
    }

    /**
     * 获取缓存键值
     * @param id
     * @return
     */
    protected String getCacheKey(Object id) {
        String cacheName = getCacheKey();
        return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
    }

    /**
     * 获取缓存键值
     * @param id
     * @return
     */
    protected String getLockKey(Object id) {
        String cacheName = getCacheKey();
        return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":LOCK:").append(id).toString();
    }

    /**
     * @param params
     * @param cls
     * @return
     */
    protected <P> Page<P> query(Map<String, Object> params, Class<P> cls) {
        Page<Long> page = getPage(params);
        page.setRecords(mapper.selectIdPage(page, params));
        return getPage(page, cls);
    }

    /**
     * @param millis
     */
    protected void sleep(int millis) {
        try {
            Thread.sleep(RandomUtils.nextLong(10, millis));
        } catch (InterruptedException e) {
            logger.error("", e);
        }
    }

    /**
     * @return
     */
    private String getCacheKey() {
        Class<?> cls = getClass();
        String cacheName = Constants.cacheKeyMap.get(cls);
        if (StringUtils.isBlank(cacheName)) {
            CacheConfig cacheConfig = cls.getAnnotation(CacheConfig.class);
            if (cacheConfig != null && ArrayUtils.isNotEmpty(cacheConfig.cacheNames())) {
                cacheName = cacheConfig.cacheNames()[0];
            } else {
                cacheName = getClass().getName();
            }
            Constants.cacheKeyMap.put(cls, cacheName);
        }
        return cacheName;
    }

    /** 根据Id查询(默认类型T) */
    private Page<T> getPage(final Page<Long> ids) {
        if (ids != null) {
            Page<T> page = new Page<T>(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            final List<T> records = InstanceUtil.newArrayList();
            for (int i = 0; i < ids.getRecords().size(); i++) {
                records.add(null);
            }
            final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
            for (int i = 0; i < ids.getRecords().size(); i++) {
                final int index = i;
                executorService.execute(new Runnable() {
                    public void run() {
                        try {
                            records.set(index, queryById(ids.getRecords().get(index)));
                        } finally {
                            thread.put(index, 0);
                        }
                    }
                });
            }
            while (thread.size() < records.size()) {
                try {
                    Thread.sleep(threadSleep);
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
            }
            page.setRecords(records);
            return page;
        }
        return new Page<T>();
    }

    /** 根据Id查询(cls返回类型Class) */
    private <K> Page<K> getPage(final Page<Long> ids, final Class<K> cls) {
        if (ids != null) {
            Page<K> page = new Page<K>(ids.getCurrent(), ids.getSize());
            page.setTotal(ids.getTotal());
            final List<K> records = InstanceUtil.newArrayList();
            for (int i = 0; i < ids.getRecords().size(); i++) {
                records.add(null);
            }
            final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
            for (int i = 0; i < ids.getRecords().size(); i++) {
                final int index = i;
                executorService.execute(new Runnable() {
                    public void run() {
                        try {
                            T t = queryById(ids.getRecords().get(index));
                            K k = InstanceUtil.to(t, cls);
                            records.set(index, k);
                        } finally {
                            thread.put(index, 0);
                        }
                    }
                });
            }
            while (thread.size() < records.size()) {
                try {
                    Thread.sleep(threadSleep);
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
            }
            page.setRecords(records);
            return page;
        }
        return new Page<K>();
    }

    @SuppressWarnings("unchecked")
    private T queryById(Long id, int times) {
        String key = getCacheKey(id);
        T record = null;
        try {
            record = (T)CacheUtil.getCache().getFire(key);
        } catch (Exception e) {
            logger.error(Constants.Exception_Head, e);
        }
        if (record == null) {
            String lockKey = getLockKey(id);
            if (CacheUtil.tryLock(lockKey)) {
                try {
                    record = mapper.selectById(id);
                    try {
                        CacheUtil.getCache().set(key, record);
                    } catch (Exception e) {
                        logger.error(Constants.Exception_Head, e);
                    }
                } finally {
                    CacheUtil.unlock(lockKey);
                }
            } else {
                if (times > 3) {
                    return mapper.selectById(id);
                }
                logger.debug(getClass().getSimpleName() + ":" + id + " retry queryById.");
                sleep(20);
                return queryById(id, times + 1);
            }
        }
        return record;
    }
}
