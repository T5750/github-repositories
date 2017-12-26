/**
 * 
 */
package top.ibase4j.core.support.scheduler.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import top.ibase4j.core.base.BaseProvider;
import top.ibase4j.core.base.Parameter;
import top.ibase4j.core.support.scheduler.TaskScheduled.TaskType;
import top.ibase4j.core.util.CacheUtil;

/**
 * 默认调度(非阻塞)
 * 
 * @author ShenHuaJie
 * @version 2016年12月29日 上午11:52:32
 */
public class BaseJob implements Job {
    private final Logger logger = LogManager.getLogger();

    public void execute(JobExecutionContext context) throws JobExecutionException {
        long start = System.currentTimeMillis();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String taskType = jobDataMap.getString("taskType");
        String targetObject = jobDataMap.getString("targetObject");
        String targetMethod = jobDataMap.getString("targetMethod");
        String key = targetMethod + "." + targetObject;
        try {
            logger.info("定时任务[{}.{}]开始", targetObject, targetMethod);
            if (CacheUtil.getCache().lock(key)) {
                try {
                    ApplicationContext applicationContext = (ApplicationContext)context.getScheduler().getContext()
                        .get("applicationContext");
                    if (TaskType.local.equals(taskType)) {
                        Object refer = applicationContext.getBean(targetObject);
                        refer.getClass().getDeclaredMethod(targetMethod).invoke(refer);
                    } else if (TaskType.dubbo.equals(taskType)) {
                        BaseProvider provider = (BaseProvider)applicationContext
                            .getBean(jobDataMap.getString("targetSystem"));
                        provider.execute(new Parameter(targetObject, targetMethod));
                    }
                    Double time = (System.currentTimeMillis() - start) / 1000.0;
                    logger.info("定时任务[{}.{}]用时：{}s", targetObject, targetMethod, time.toString());
                } finally {
                    unLock(key);
                }
            }
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

    private void unLock(String key) {
        try {
            CacheUtil.getCache().del(key);
        } catch (Exception e) {
            logger.error("", e);
            try {
                Thread.sleep(1000);
            } catch (Exception e2) {
                logger.error("", e2);
            }
            unLock(key);
        }
    }
}
