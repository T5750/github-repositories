package top.ibase4j.core.support.pay;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import top.ibase4j.core.util.HttpUtil;

public class WxPay {
    static Logger logger = LogManager.getLogger();
    // 统一下单接口
    private static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // 订单查询
    private static final String ORDERQUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    // 关闭订单
    private static final String CLOSEORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
    // 撤销订单
    private static final String REVERSE_URL = "https://api.mch.weixin.qq.com/secapi/pay/reverse";
    // 申请退款
    private static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    // 查询退款
    private static final String REFUNDQUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
    // 下载对账单
    private static final String DOWNLOADBILLY_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
    // 交易保障
    private static final String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
    // 转换短链接
    private static final String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
    // 授权码查询openId接口
    private static final String AUTHCODETOOPENID_URL = "https://api.mch.weixin.qq.com/tools/authcodetoopenid";
    // 刷卡支付
    private static final String MICROPAY_URL = "https://api.mch.weixin.qq.com/pay/micropay";
    // 企业付款
    private static final String TRANSFERS_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    // 查询企业付款
    private static final String GETTRANSFERINFO_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";

    private WxPay() {
    }

    /**
     * 交易类型枚举
     * 
     * @author Javen 2017年4月15日
     *         JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
     *         MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
     */
    public static enum TradeType {
            JSAPI, NATIVE, APP, WAP, MICROPAY
    }

    /**
     * 统一下单
     * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/native_sl.php?chapter=9_1
     * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
     * 
     * @param params
     * @return
     */
    public static String pushOrder(Map<String, String> params) {
        return doPost(UNIFIEDORDER_URL, params);
    }

    /**
     * 订单查询
     * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_2
     * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_2
     * 
     * @param params
     *            请求参数
     * @return
     */
    public static String orderQuery(Map<String, String> params) {
        return doPost(ORDERQUERY_URL, params);
    }

    /**
     * 关闭订单
     * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_3
     * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3
     * 
     * @param params
     * @return
     */
    public static String closeOrder(Map<String, String> params) {
        return doPost(CLOSEORDER_URL, params);
    }

    /**
     * 撤销订单
     * 
     * @param params
     *            请求参数
     * @param certPath
     *            证书文件目录
     * @param certPass
     *            证书密码
     * @return
     */
    public static String orderReverse(Map<String, String> params, String certPath, String certPass) {
        return doPostSSL(REVERSE_URL, params, certPath, certPass);
    }

    /**
     * 申请退款
     * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_4
     * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_4
     * 
     * @param params
     *            请求参数
     * @param certPath
     *            证书文件目录
     * @param certPass
     *            证书密码
     * @return
     */
    public static String orderRefund(Map<String, String> params, String certPath, String certPass) {
        return doPostSSL(REFUND_URL, params, certPath, certPass);
    }

    /**
     * 查询退款
     * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_5
     * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_5
     * 
     * @param params
     *            请求参数
     * @return
     */
    public static String orderRefundQuery(Map<String, String> params) {
        return doPost(REFUNDQUERY_URL, params);
    }

    /**
     * 下载对账单
     * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_6
     * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_6
     * 
     * @param params
     *            请求参数
     * @return
     */
    public static String downloadBill(Map<String, String> params) {
        return doPost(DOWNLOADBILLY_URL, params);
    }

    /**
     * 交易保障
     * 
     * @param params
     *            请求参数
     * @return
     */
    public static String orderReport(Map<String, String> params) {
        return doPost(REPORT_URL, params);
    }

    /**
     * 转换短链接
     * 
     * @param params
     *            请求参数
     * @return
     */
    public static String toShortUrl(Map<String, String> params) {
        return doPost(SHORT_URL, params);
    }

    /**
     * 授权码查询openId
     * 
     * @param params
     *            请求参数
     * @return
     */
    public static String authCodeToOpenid(Map<String, String> params) {
        return doPost(AUTHCODETOOPENID_URL, params);
    }

    /**
     * 刷卡支付
     *
     * @param params
     *            请求参数
     * @return
     */
    public static String micropay(Map<String, String> params) {
        return WxPay.doPost(MICROPAY_URL, params);
    }

    /**
     * 企业付款
     * 
     * @param params
     *            请求参数
     * @param certPath
     *            证书文件目录
     * @param certPassword
     *            证书密码
     * @return {String}
     */
    public static String transfers(Map<String, String> params, String certPath, String certPassword) {
        return WxPay.doPostSSL(TRANSFERS_URL, params, certPath, certPassword);
    }

    /**
     * 查询企业付款
     * 
     * @param params
     *            请求参数
     * @param certPath
     *            证书文件目录
     * @param certPassword
     *            证书密码
     * @return {String}
     */
    public static String getTransferInfo(Map<String, String> params, String certPath, String certPassword) {
        return WxPay.doPostSSL(GETTRANSFERINFO_URL, params, certPath, certPassword);
    }

    /**
     * 商户模式下 扫码模式一之生成二维码
     * 
     * @param appid
     * @param mch_id
     * @param product_id
     * @param partnerKey
     * @param isToShortUrl
     *            是否转化为短连接
     * @return
     */
    public static String getCodeUrl(String appid, String mch_id, String product_id, String partnerKey,
        boolean isToShortUrl) {
        String url = "weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXX&time_stamp=XXXXX&nonce_str=XXXXX";
        String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
        String nonceStr = Long.toString(System.currentTimeMillis());
        Map<String, String> packageParams = new HashMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("product_id", product_id);
        packageParams.put("time_stamp", timeStamp);
        packageParams.put("nonce_str", nonceStr);
        String packageSign = WxPayment.createSign(packageParams, partnerKey);
        String qrCodeUrl = WxPayment.replace(url, "XXXXX", packageSign, appid, mch_id, product_id, timeStamp, nonceStr);
        if (isToShortUrl) {
            String shortResult = WxPay
                .toShortUrl(WxPayment.buildShortUrlParasMap(appid, null, mch_id, null, qrCodeUrl, partnerKey));
            logger.info(shortResult);
            Map<String, String> shortMap = WxPayment.xmlToMap(shortResult);
            String return_code = shortMap.get("return_code");
            if (WxPayment.codeIsOK(return_code)) {
                String result_code = shortMap.get("result_code");
                if (WxPayment.codeIsOK(result_code)) {
                    qrCodeUrl = shortMap.get("short_url");
                }
            }
        }

        return qrCodeUrl;
    }

    public static String doPost(String url, Map<String, String> params) {
        return HttpUtil.post(url, WxPayment.toXml(params));
    }

    public static String doPostSSL(String url, Map<String, String> params, String certPath, String certPass) {
        return HttpUtil.postSSL(url, WxPayment.toXml(params), certPath, certPass);
    }
}
