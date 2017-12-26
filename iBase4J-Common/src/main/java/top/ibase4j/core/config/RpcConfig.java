package top.ibase4j.core.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.type.AnnotatedTypeMetadata;

import top.ibase4j.core.util.PropertiesUtil;

/**
 * RPC服务配置
 * @author ShenHuaJie
 * @since 2017年8月14日 上午10:16:18
 */
public class RpcConfig {
    public static class EnableDubbo implements Condition {
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return "dubbo".equals(PropertiesUtil.getString("rpc.type"));
        }
    }

    public static class EnableMotan implements Condition {
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return "motan".equals(PropertiesUtil.getString("rpc.type"));
        }
    }

    @Configuration
    @Conditional(EnableDubbo.class)
    @ImportResource({"classpath*:spring/dubbo.xml"})
    static class DubboConfig {
    }

    @Configuration
    @Conditional(EnableMotan.class)
    @ImportResource({"classpath*:spring/motan.xml"})
    static class MotanConfig {
    }
}
