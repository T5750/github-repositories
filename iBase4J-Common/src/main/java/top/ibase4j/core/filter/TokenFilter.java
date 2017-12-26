package top.ibase4j.core.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;

import top.ibase4j.core.support.HttpCode;
import top.ibase4j.core.support.Token;
import top.ibase4j.core.util.DataUtil;
import top.ibase4j.core.util.FileUtil;
import top.ibase4j.core.util.InstanceUtil;
import top.ibase4j.core.util.PropertiesUtil;
import top.ibase4j.core.util.TokenUtil;
import top.ibase4j.core.util.WebUtil;

/**
 * APP登录TOKEN过滤器, expire有效期(秒),默认永远有效
 * @author ShenHuaJie
 * @since 2017年3月19日 上午10:21:59
 */
public class TokenFilter implements Filter {
    private Logger logger = LogManager.getLogger();
    private String expire;
    // 白名单
    private List<String> whiteUrls;
    private int _size = 0;

    public void init(FilterConfig config) throws ServletException {
        // 读取文件
        String path = CsrfFilter.class.getResource("/").getFile();
        whiteUrls = FileUtil.readFile(path + "white/tokenWhite.txt");
        _size = null == whiteUrls ? 0 : whiteUrls.size();
        expire = config.getInitParameter("expire");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        boolean filter = DataUtil.isEmpty(PropertiesUtil.getString("token.filter.test"));
        String token = request.getHeader("UUID");
        logger.info("UUID==>" + token);
        if (StringUtils.isNotBlank(token)) {
            try {
                Token tokenInfo = TokenUtil.getTokenInfo(token);
                if (tokenInfo != null) {
                    WebUtil.saveCurrentUser(request, tokenInfo.getValue());
                    if (DataUtil.isNotEmpty(expire)) {
                        if (System.currentTimeMillis() - tokenInfo.getTime() > Long.valueOf(expire) * 1000) {
                            WebUtil.saveCurrentUser(request, null);
                        }
                    }
                } else if (filter) {
                    WebUtil.saveCurrentUser(request, null);
                }
            } catch (Exception e) {
                logger.error("token检查发生异常:", e);
            }
        } else if (filter) {
            WebUtil.saveCurrentUser(request, null);
        }
        String url = request.getRequestURI();
        if (isWhiteReq(url.toLowerCase())) {
            chain.doFilter(request, response);
        } else if (DataUtil.isEmpty(WebUtil.getCurrentUser(request)) && filter) {
            response.setContentType("text/html; charset=UTF-8");
            Map<String, Object> modelMap = InstanceUtil.newLinkedHashMap();
            modelMap.put("code", HttpCode.UNAUTHORIZED.value().toString());
            modelMap.put("msg", HttpCode.UNAUTHORIZED.msg());
            modelMap.put("timestamp", System.currentTimeMillis());
            String result = JSON.toJSONString(modelMap);
            logger.warn(url + " ====> " + result);
            PrintWriter out = response.getWriter();
            out.println(result);
            out.flush();
            out.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
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
}
