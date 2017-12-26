package top.ibase4j.core.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import top.ibase4j.core.Constants;
import top.ibase4j.core.support.HttpCode;
import top.ibase4j.core.util.CacheUtil;
import top.ibase4j.core.util.FileUtil;
import top.ibase4j.core.util.WebUtil;

/**
 * 恶意请求拦截器
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:16:57
 */
public class MaliciousRequestInterceptor extends BaseInterceptor {
    private boolean allRequest = false; // 拦截所有请求,否则拦截相同请求
    private boolean containsParamter = true; // 包含参数
    private int minRequestIntervalTime = 500; // 允许的最小请求间隔
    private int maxMaliciousTimes = 0; // 允许的最大恶意请求次数

    // 白名单
    private List<String> whiteUrls;
    private int _size = 0;

    public MaliciousRequestInterceptor() {
        // 读取文件
        String path = MaliciousRequestInterceptor.class.getResource("/").getFile();
        whiteUrls = FileUtil.readFile(path + "white/mrqWhite.txt");
        _size = null == whiteUrls ? 0 : whiteUrls.size();
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers",
            "x-requested-with,Access-Control-Allow-Origin,EX-SysAuthToken,EX-JSESSIONID");

        String url = request.getServletPath();
        if (url.endsWith("/unauthorized") || url.endsWith("/forbidden") || isWhiteReq(url.toLowerCase())) {
            return super.preHandle(request, response, handler);
        }
        if (containsParamter) {
            url += JSON.toJSONString(WebUtil.getParameterMap(request));
        }
        Object userId = WebUtil.getCurrentUser(request);
        String user = userId != null ? userId.toString() : WebUtil.getHost(request) + request.getHeader("USER-AGENT");
        String preRequest = (String)CacheUtil.getCache().getFire(Constants.PREREQUEST + user);
        Long preRequestTime = (Long)CacheUtil.getCache().getFire(Constants.PREREQUEST_TIME + user);
        int seconds = minRequestIntervalTime / 500;
        if (preRequestTime != null && preRequest != null) { // 过滤频繁操作
            if ((url.equals(preRequest) || allRequest)
                && System.currentTimeMillis() - preRequestTime < minRequestIntervalTime) {
                Integer maliciousRequestTimes = (Integer)CacheUtil.getCache()
                    .getFire(Constants.MALICIOUS_REQUEST_TIMES + user);
                if (maliciousRequestTimes == null) {
                    maliciousRequestTimes = 1;
                } else {
                    maliciousRequestTimes++;
                }
                CacheUtil.getCache().set(Constants.MALICIOUS_REQUEST_TIMES + user, maliciousRequestTimes, seconds);
                if (maliciousRequestTimes > maxMaliciousTimes) {
                    CacheUtil.getCache().set(Constants.MALICIOUS_REQUEST_TIMES + user, 0, seconds);
                    logger.warn("To intercept a malicious request : {}", url);
                    ModelMap modelMap = new ModelMap();
                    modelMap.put("code", HttpCode.MULTI_STATUS.value().toString());
                    modelMap.put("msg", HttpCode.MULTI_STATUS.msg());
                    modelMap.put("timestamp", System.currentTimeMillis());
                    logger.info("RESPONSE : " + JSON.toJSON(modelMap));
                    byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
                    response.getOutputStream().write(bytes);
                    return false;
                }
            } else {
                CacheUtil.getCache().set(Constants.MALICIOUS_REQUEST_TIMES + user, 0, seconds);
            }
        }
        CacheUtil.getCache().set(Constants.PREREQUEST + user, url, seconds);
        CacheUtil.getCache().set(Constants.PREREQUEST_TIME + user, System.currentTimeMillis(), seconds);
        return super.preHandle(request, response, handler);
    }

    /* 判断是否是白名单 */
    private boolean isWhiteReq(String requestUrl) {
        if (_size == 0) {
            return false;
        } else {
            for (String urlTemp : whiteUrls) {
                if (requestUrl.indexOf(urlTemp.toLowerCase()) > -1) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setAllRequest(boolean allRequest) {
        this.allRequest = allRequest;
    }

    public void setContainsParamter(boolean containsParamter) {
        this.containsParamter = containsParamter;
    }

    public void setMinRequestIntervalTime(int minRequestIntervalTime) {
        this.minRequestIntervalTime = minRequestIntervalTime;
    }

    public void setMaxMaliciousTimes(int maxMaliciousTimes) {
        this.maxMaliciousTimes = maxMaliciousTimes;
    }
}
