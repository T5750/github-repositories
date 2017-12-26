/**
 * 
 */
package top.ibase4j.core.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.toolkit.IdWorker;

import top.ibase4j.core.support.pay.WxPay;
import top.ibase4j.core.support.pay.WxPayment;
import top.ibase4j.core.support.pay.vo.RefundResult;

/**
 * 微信
 * @author ShenHuaJie
 * @version 2017年10月21日 下午10:52:22
 */
public class WeChatUtil {
    private static final Logger logger = LogManager.getLogger();

    /**
     * APP下单并获取支付签名
     * @param out_trade_no 商户订单号
     * @param body 商品描述
     * @param detail 交易详情
     * @param amount 交易金额
     * @param scene_info 场景信息
     * @param ip 客户端IP
     * @param callBack 回调地址
     * @return 支付参数
     */
    public static Map<String, String> pushOrder(String out_trade_no, String body, String detail, BigDecimal amount,
        String scene_info, String ip, String callBack) {
        return pushOrder("APP", out_trade_no, body, detail, amount, scene_info, ip, callBack);
    }

    /**
     * 下单并获取支付签名
     * @param trade_type 交易类型(APP/MWEB)
     * @param out_trade_no 商户订单号
     * @param body 商品描述
     * @param detail 商品详细描述
     * @param amount 交易金额
     * @param scene_info 场景信息
     * @param ip 客户端IP
     * @param callBack 回调地址
     * @return 支付参数
     */
    public static Map<String, String> pushOrder(String trade_type, String out_trade_no, String body, String detail,
        BigDecimal amount, String scene_info, String ip, String callBack) {
        return pushOrder(PropertiesUtil.getString("wx.mch_id"), PropertiesUtil.getString("wx.appId"),
            PropertiesUtil.getString("wx.partnerKey"), trade_type, out_trade_no, body, detail, amount, scene_info, ip,
            callBack);
    }

    /**
     * 下单并获取支付签名
     * @param mch_id 商户号
     * @param appId APPID
     * @param partnerKey 安全密钥
     * @param trade_type 交易类型(APP/MWEB)
     * @param out_trade_no 商户订单号
     * @param body 商品描述
     * @param detail 商品详细描述
     * @param amount 交易金额
     * @param scene_info 场景信息
     * @param ip 客户端IP
     * @param callBack 回调地址
     * @return 支付参数
     */
    public static Map<String, String> pushOrder(String mch_id, String appId, String partnerKey, String trade_type,
        String out_trade_no, String body, String detail, BigDecimal amount, String scene_info, String ip,
        String callBack) {
        String total_fee = amount.multiply(new BigDecimal("100")).setScale(0).toString();
        Map<String, String> params = WxPayment.buildUnifiedOrderParasMap(appId, null, mch_id, null, null, body, detail,
            null, out_trade_no, total_fee, ip, callBack, trade_type, partnerKey, null, scene_info);
        logger.info("WeChart order parameter : " + JSON.toJSONString(params));
        String result = WxPay.pushOrder(params);
        logger.info("WeChart order result : " + result);
        Map<String, String> resultMap = WxPayment.xmlToMap(result);
        String return_code = resultMap.get("return_code");
        if (WxPayment.codeIsOK(return_code)) {
            String result_code = resultMap.get("result_code");
            if (WxPayment.codeIsOK(result_code)) {
                String sign = resultMap.get("sign");
                String mySign = WxPayment.createSign(resultMap, partnerKey);
                if (mySign.equals(sign)) {
                    String prepay_id = resultMap.get("prepay_id");
                    String mweb_url = resultMap.get("mweb_url");
                    resultMap.clear();
                    resultMap.put("appid", appId);
                    resultMap.put("partnerid", mch_id);
                    resultMap.put("prepayid", prepay_id);
                    resultMap.put("tradeType", trade_type);
                    resultMap.put("mwebUrl", mweb_url);
                    resultMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
                    resultMap.put("noncestr", IdWorker.get32UUID());
                    sign = WxPayment.buildOrderPaySign(appId, mch_id, prepay_id, "Sign=WXPay",
                        resultMap.get("timestamp"), resultMap.get("noncestr"), partnerKey);
                    resultMap.put("sign", sign);
                    return resultMap;
                } else {
                    throw new RuntimeException("微信返回数据异常.");
                }
            } else {
                throw new RuntimeException(resultMap.get("err_code_des"));
            }
        } else {
            throw new RuntimeException(resultMap.get("return_msg"));
        }
    }

    /**
     * 生成签名
     * @param params 参数
     * @param partnerKey 支付密钥
     * @return 签名
     */
    public static String createSign(Map<String, String> params, String partnerKey) {
        return WxPayment.createSign(params, partnerKey);
    }

    /**
     * 生成签名
     * @param params 参数
     * @return 签名
     */
    public static String createSign(Map<String, String> params) {
        return WxPayment.createSign(params, PropertiesUtil.getString("wx.partnerKey"));
    }

    /**
     * 关闭订单
     * @param out_trade_no 商户订单号
     * @return
     */
    public static Map<String, String> closeOrder(String out_trade_no) {
        return closeOrder(PropertiesUtil.getString("wx.mch_id"), PropertiesUtil.getString("wx.appId"),
            PropertiesUtil.getString("wx.partnerKey"), out_trade_no);
    }

    /**
     * 关闭订单
     * @param mch_id 商户号
     * @param appId APPID
     * @param partnerKey 安全密钥
     * @param out_trade_no 商户订单号
     * @return
     */
    public static Map<String, String> closeOrder(String mch_id, String appId, String partnerKey, String out_trade_no) {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("appid", appId);
            params.put("mch_id", mch_id);
            params.put("out_trade_no", out_trade_no);
            params = WxPayment.buildSignAfterParasMap(params, partnerKey);
            String result = WxPay.closeOrder(params);
            Map<String, String> resultMap = WxPayment.xmlToMap(result);
            logger.info(JSON.toJSONString(resultMap));
            return resultMap;
        } catch (Exception e) {
            logger.error("删除微信订单异常", e);
        }
        return null;
    }

    /**
     * 查询订单状态
     * @param out_trade_no 商户订单号
     * @return
     */
    public static Map<String, Object> queryOrder(String out_trade_no) {
        return queryOrder(PropertiesUtil.getString("wx.mch_id"), PropertiesUtil.getString("wx.appId"),
            PropertiesUtil.getString("wx.partnerKey"), out_trade_no);
    }

    /**
     * 查询订单状态
     * @param out_trade_no 商户订单号
     * @return
     */
    public static Map<String, Object> queryOrder(String mch_id, String appId, String partnerKey, String out_trade_no) {
        Map<String, Object> result = InstanceUtil.newHashMap();
        Map<String, String> params = WxPayment.buildParasMap(appId, null, mch_id, null, null, out_trade_no, partnerKey);
        Map<String, String> resultMap = WxPayment.xmlToMap(WxPay.orderQuery(params));
        String return_code = resultMap.get("return_code");
        if (WxPayment.codeIsOK(return_code)) {
            String result_code = resultMap.get("result_code");
            if (WxPayment.codeIsOK(result_code)) {
                String trade_state = resultMap.get("trade_state");
                if (WxPayment.codeIsOK(trade_state)) {
                    Date date = DateUtil.stringToDate(resultMap.get("time_end"));
                    result.put("time_end", date);
                    result.put("trade_no", resultMap.get("transaction_id"));
                    result.put("trade_state", "1");
                } else {
                    result.put("trade_state_desc", resultMap.get("trade_state_desc"));
                    result.put("trade_state", "2");
                }
            } else {
                logger.warn(resultMap.get("err_code_des"));
                result.put("trade_state_desc", resultMap.get("err_code_des"));
                result.put("trade_state", "0");
            }
        } else {
            logger.warn(resultMap.get("return_msg"));
            result.put("trade_state_desc", resultMap.get("return_msg"));
            result.put("trade_state", "0");
        }
        return result;
    }

    /**
     * 退款
     * @param transaction_id
     * @param out_trade_no
     * @param out_refund_no
     * @param amount
     * @param refund
     * @param refund_desc
     * @return
     */
    public static RefundResult refund(String transaction_id, String out_trade_no, String out_refund_no,
        BigDecimal amount, BigDecimal refund, String refund_desc) {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            String path = resolver.getResources(PropertiesUtil.getString("wx.certPath"))[0].getFile().getAbsolutePath();
            return refund(path, PropertiesUtil.getString("wx.certPass"), transaction_id, out_trade_no, out_refund_no,
                amount, refund, refund_desc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 退款
     * @param transaction_id
     * @param out_trade_no
     * @param out_refund_no
     * @param amount
     * @param refund
     * @param refund_desc
     * @return
     */
    public static RefundResult refund(String certPath, String certPass, String transaction_id, String out_trade_no,
        String out_refund_no, BigDecimal amount, BigDecimal refund, String refund_desc) {
        return refund(PropertiesUtil.getString("wx.mch_id"), PropertiesUtil.getString("wx.appId"), null, null,
            PropertiesUtil.getString("wx.partnerKey"), certPath, certPass, transaction_id, out_trade_no, out_refund_no,
            amount, refund, "CNY", null, refund_desc);
    }

    /**
     * 退款
     * @param mch_id 商户号
     * @param appId APPID
     * @param partnerKey 安全密钥
     * @param transaction_id
     * @param out_trade_no
     * @param out_refund_no
     * @param amount
     * @param refund
     * @param refund_fee_type
     * @param refund_account
     * @param refund_desc
     * @return
     */
    public static RefundResult refund(String mch_id, String appid, String sub_mch_id, String sub_appid,
        String paternerKey, String certPath, String certPass, String transaction_id, String out_trade_no,
        String out_refund_no, BigDecimal amount, BigDecimal refund, String refund_fee_type, String refund_account,
        String refund_desc) {
        String total_fee = amount.multiply(new BigDecimal("100")).setScale(0).toString();
        String refund_fee = refund.multiply(new BigDecimal("100")).setScale(0).toString();
        Map<String, String> params = WxPayment.buildRefundParams(appid, mch_id, null, null, transaction_id,
            out_trade_no, out_refund_no, total_fee, refund_fee, refund_fee_type, refund_account, refund_desc,
            paternerKey);
        logger.info("WeChart order parameter : " + JSON.toJSONString(params));
        String result = WxPay.orderRefund(params, certPath, certPass);
        logger.info("WeChart order result : " + result);
        Map<String, String> resultMap = WxPayment.xmlToMap(result);
        String return_code = resultMap.get("return_code");
        if (WxPayment.codeIsOK(return_code)) {
            String result_code = resultMap.get("result_code");
            if (WxPayment.codeIsOK(result_code)) {
                String sign = resultMap.get("sign");
                String mySign = WxPayment.createSign(resultMap, paternerKey);
                if (mySign.equals(sign)) {
                    String refund_id = resultMap.get("refund_id");
                    return new RefundResult(refund_id, out_refund_no, refund_fee, new Date());
                } else {
                    throw new RuntimeException("微信返回数据异常.");
                }
            } else {
                throw new RuntimeException(resultMap.get("err_code_des"));
            }
        } else {
            throw new RuntimeException(resultMap.get("return_msg"));
        }
    }

    /**
    * 判断接口返回的code是否是SUCCESS
    * @param return_code
    * @return
    */
    public static boolean codeIsOK(String return_code) {
        return WxPayment.codeIsOK(return_code);
    }
}
