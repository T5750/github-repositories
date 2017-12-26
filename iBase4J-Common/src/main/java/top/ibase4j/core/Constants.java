package top.ibase4j.core;

import java.util.Map;

import top.ibase4j.core.util.InstanceUtil;

/**
 * 常量表
 * 
 * @author ShenHuaJie
 * @version $Id: Constants.java, v 0.1 2014-2-28 上午11:18:28 ShenHuaJie Exp $
 */
public interface Constants {
    /**
     * 异常信息统一头信息<br>
     * 非常遗憾的通知您,程序发生了异常
     */
    static final String Exception_Head = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :";
    /** 缓存键值 */
    static final Map<Class<?>, String> cacheKeyMap = InstanceUtil.newHashMap();
    /** 操作名称 */
    static final String OPERATION_NAME = "OPERATION_NAME";
    /** 客户端语言 */
    static final String USERLANGUAGE = "userLanguage";
    /** 客户端主题 */
    static final String WEBTHEME = "webTheme";
    /** 当前用户 */
    static final String CURRENT_USER = "CURRENT_USER";
    /** 客户端信息 */
    static final String USER_AGENT = "USER-AGENT";
    /** 客户端信息 */
    static final String USER_IP = "USER_IP";
    /** 登录地址 */
    static final String LOGIN_URL = "/login.html";
    /** 缓存命名空间 */
    static final String CACHE_NAMESPACE = "iBase4J:";
    /** 缓存命名空间 */
    static final String SYSTEM_CACHE_NAMESPACE = "S:iBase4J:";
    /** 缓存命名空间 */
    static final String CACHE_NAMESPACE_LOCK = "L:iBase4J:";
    /** 上次请求地址 */
    static final String PREREQUEST = CACHE_NAMESPACE + "PREREQUEST";
    /** 上次请求时间 */
    static final String PREREQUEST_TIME = CACHE_NAMESPACE + "PREREQUEST_TIME";
    /** 非法请求次数 */
    static final String MALICIOUS_REQUEST_TIMES = CACHE_NAMESPACE + "MALICIOUS_REQUEST_TIMES";
    /** 在线用户数量 */
    static final String ALLUSER_NUMBER = SYSTEM_CACHE_NAMESPACE + "ALLUSER_NUMBER";
    /** TOKEN */
    static final String TOKEN_KEY = SYSTEM_CACHE_NAMESPACE + "TOKEN_KEY";
    /***/
    static final String DB_KEY = "90139119";

    /** 日志表状态 */
    interface JOBSTATE {
        /**
         * 日志表状态，初始状态，插入
         */
        static final String INIT_STATS = "I";
        /**
         * 日志表状态，成功
         */
        static final String SUCCESS_STATS = "S";
        /**
         * 日志表状态，失败
         */
        static final String ERROR_STATS = "E";
        /**
         * 日志表状态，未执行
         */
        static final String UN_STATS = "N";
    }

    /** 短信验证码类型 */
    public interface MSGCHKTYPE {
        /** 注册 */
        public static final String REGISTER = CACHE_NAMESPACE + "REGISTER:";
        /** 登录 */
        public static final String LOGIN = CACHE_NAMESPACE + "LOGIN:";
        /** 修改密码验证码 */
        public static final String CHGPWD = CACHE_NAMESPACE + "CHGPWD:";
        /** 身份验证验证码 */
        public static final String VLDID = CACHE_NAMESPACE + "VLDID:";
        /** 信息变更验证码 */
        public static final String CHGINFO = CACHE_NAMESPACE + "CHGINFO:";
        /** 活动确认验证码 */
        public static final String AVTCMF = CACHE_NAMESPACE + "AVTCMF:";
    }
}
