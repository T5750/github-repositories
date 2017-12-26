package top.ibase4j.core.support.pay;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

@Configuration
@ConditionalOnProperty("alipay")
@PropertySource(value = "classpath:config/alipay.properties")
public class AliPayConfig {
    @Autowired
    public Environment env;
    private String privateKey;
    private String alipayPublicKey;
    private String appId;
    private String serviceUrl;
    private String charset;
    private String signType;
    private String format;

    private AlipayClient alipayClient;
    private static AliPayConfig config;

    @Bean
    public AliPayConfig aliPayConfigs() {
        config = new AliPayConfig();
        config.setPrivateKey(env.getProperty("alipay.privateKey"));
        config.setAlipayPublicKey(env.getProperty("alipay.alipayPulicKey"));
        config.setAppId(env.getProperty("alipay.appId"));
        config.setServiceUrl(env.getProperty("alipay.serverUrl"));
        config.setCharset(env.getProperty("alipay.charset"));
        return config;
    }

    public static AliPayConfig build() {
        config.alipayClient = new DefaultAlipayClient(config.getServiceUrl(), config.getAppId(), config.getPrivateKey(),
            config.getFormat(), config.getCharset(), config.getAlipayPublicKey(), config.getSignType());
        return config;
    }

    public String getPrivateKey() {
        if (StringUtils.isBlank(privateKey)) throw new IllegalStateException("privateKey 未被赋值");
        return privateKey;
    }

    public AliPayConfig setPrivateKey(String privateKey) {
        if (StringUtils.isBlank(privateKey)) throw new IllegalArgumentException("privateKey 值不能为 null");
        this.privateKey = privateKey;
        return this;
    }

    public String getAlipayPublicKey() {
        if (StringUtils.isBlank(alipayPublicKey)) throw new IllegalStateException("alipayPublicKey 未被赋值");
        return alipayPublicKey;
    }

    public AliPayConfig setAlipayPublicKey(String alipayPublicKey) {
        if (StringUtils.isBlank(alipayPublicKey)) throw new IllegalArgumentException("alipayPublicKey 值不能为 null");
        this.alipayPublicKey = alipayPublicKey;
        return this;
    }

    public String getAppId() {
        if (StringUtils.isBlank(appId)) throw new IllegalStateException("appId 未被赋值");
        return appId;
    }

    public AliPayConfig setAppId(String appId) {
        if (StringUtils.isBlank(appId)) throw new IllegalArgumentException("appId 值不能为 null");
        this.appId = appId;
        return this;
    }

    public String getServiceUrl() {
        if (StringUtils.isBlank(serviceUrl)) throw new IllegalStateException("serviceUrl 未被赋值");
        return serviceUrl;
    }

    public AliPayConfig setServiceUrl(String serviceUrl) {
        if (StringUtils.isBlank(serviceUrl)) serviceUrl = "https://openapi.alipay.com/gateway.do";
        this.serviceUrl = serviceUrl;
        return this;
    }

    public String getCharset() {
        if (StringUtils.isBlank(charset)) charset = "UTF-8";
        return charset;
    }

    public AliPayConfig setCharset(String charset) {
        if (StringUtils.isBlank(charset)) charset = "UTF-8";
        this.charset = charset;
        return this;
    }

    public String getSignType() {
        if (StringUtils.isBlank(signType)) signType = "RSA2";
        return signType;
    }

    public AliPayConfig setSignType(String signType) {
        if (StringUtils.isBlank(signType)) signType = "RSA2";
        this.signType = signType;
        return this;
    }

    public String getFormat() {
        if (StringUtils.isBlank(format)) format = "json";
        return format;
    }

    public AlipayClient getAlipayClient() {
        if (alipayClient == null) throw new IllegalStateException("alipayClient 未被初始化");
        return alipayClient;
    }
}
