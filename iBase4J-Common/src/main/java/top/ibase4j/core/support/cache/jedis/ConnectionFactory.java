/**
 * 
 */
package top.ibase4j.core.support.cache.jedis;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPoolConfig;

/**
 * redis连接工厂
 * @author ShenHuaJie
 * @version 2017年12月1日 下午4:00:30
 */
public class ConnectionFactory extends JedisConnectionFactory {
    private Logger logger = LogManager.getLogger();
    private RedisClusterConfiguration clusterConfig;

    public ConnectionFactory(RedisClusterConfiguration clusterConfig, JedisPoolConfig poolConfig) {
        super(poolConfig);
        this.clusterConfig = clusterConfig;
    }

    public void afterPropertiesSet() {
        if (clusterConfig != null && clusterConfig.getClusterNodes() != null
            && !clusterConfig.getClusterNodes().isEmpty()) {
            try {
                // 得到私有字段
                Field clusterConfigField = JedisConnectionFactory.class.getDeclaredField("clusterConfig");
                clusterConfigField.setAccessible(true);
                clusterConfigField.set(this, clusterConfig);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        super.afterPropertiesSet();
    }
}
