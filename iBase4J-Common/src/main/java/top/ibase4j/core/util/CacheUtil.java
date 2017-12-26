package top.ibase4j.core.util;

import org.apache.commons.lang3.StringUtils;

import top.ibase4j.core.exception.BusinessException;
import top.ibase4j.core.support.cache.CacheManager;

public class CacheUtil {
    private static CacheManager cacheManager;
    private static CacheManager lockManager;

    public static void setCacheManager(CacheManager cacheManager) {
        CacheUtil.cacheManager = cacheManager;
    }

    public static void setLockManager(CacheManager cacheManager) {
        CacheUtil.lockManager = cacheManager;
    }

    public static CacheManager getCache() {
        return cacheManager;
    }

    public static CacheManager getLockManager() {
        return lockManager;
    }

    /** 获取锁 */
    public static boolean tryLock(String key) {
        int expires = 1000 * PropertiesUtil.getInt("redis.lock.expires", 180);
        return lockManager.setnx(key, expires);
    }

    /** 获取锁 */
    public static boolean getLock(String key) {
        return lockManager.lock(key);
    }

    /** 解锁 */
    public static void unlock(String key) {
        lockManager.unlock(key);
    }

    /**
     * 次数检查
     * @param key
     * @param seconds 缓存时间
     * @param frequency 最多次数
     * @param message 超出次数提示信息
     */
    public static void refreshTimes(String key, int seconds, int frequency, String message) {
        if (CacheUtil.getLock(key + "-LOCK")) {
            try {
                Integer times = 1;
                String timesStr = (String)CacheUtil.getCache().get(key);
                if (StringUtils.isNotBlank(timesStr)) {
                    times = Integer.valueOf(timesStr) + 1;
                    if (times > frequency) {
                        throw new BusinessException(message);
                    }
                }
                CacheUtil.getCache().set(key, times.toString(), seconds);
            } finally {
                CacheUtil.unlock(key + "-LOCK");
            }
        } else {
            refreshTimes(key, seconds, frequency, message);
        }
    }
}
