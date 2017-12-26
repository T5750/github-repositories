/**
 * 
 */
package top.ibase4j.core.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

import top.ibase4j.core.support.pay.AliPayConfig;
import top.ibase4j.core.support.pay.vo.RefundResult;

/**
 * 支付宝
 * @author ShenHuaJie
 * @version 2017年10月21日 下午11:59:47
 */
public class AlipayUtil {
    private static final Logger logger = LogManager.getLogger(AlipayUtil.class);

    /**
     * 下单并获取支付签名
     * @param out_trade_no 商户订单号
     * @param subject 交易主题
     * @param body 交易详情
     * @param amount 交易金额
     * @param ip 客户端IP
     * @param timeout 订单失效时间
     * @param callBack 回调地址
     * @return 支付参数
     */
    public static String getSign(String out_trade_no, String subject, String body, BigDecimal amount, String ip,
        String timeout, String callBack) {
        // 实例化客户端
        AlipayClient alipayClient = AliPayConfig.build().getAlipayClient();
        // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setSubject(subject);
        model.setBody(body);
        model.setOutTradeNo(out_trade_no);
        model.setTimeoutExpress(timeout);
        model.setTotalAmount(amount.toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(callBack);
        try {
            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            logger.info(response.getBody());// 就是orderString 可以直接给客户端请求，无需再做处理。
            if (!response.isSuccess()) {
                throw new RuntimeException(response.getSubMsg());
            }
            return response.getBody();
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<?, ?> searchTreade(String outTradeNo, String tradeNo) {
        // 实例化客户端
        AlipayClient alipayClient = AliPayConfig.build().getAlipayClient();
        // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(outTradeNo);
        model.setTradeNo(tradeNo);
        request.setBizModel(model);
        Map<String, Object> result = InstanceUtil.newHashMap();
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (!response.isSuccess()) {
                result.put("trade_state_desc", response.getSubMsg());
                result.put("trade_state", "0");
            } else {
                Map<?, ?> body = JSON.parseObject(response.getBody(), Map.class);
                Map<?, ?> resultMap = JSON.parseObject(body.get("alipay_trade_query_response").toString());
                Object trade_status = resultMap.get("trade_status");
                if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
                    Date date = DateUtil.stringToDate((String)resultMap.get("send_pay_date"));
                    result.put("time_end", date);
                    result.put("trade_no", resultMap.get("trade_no"));
                    result.put("trade_state", "1");
                } else {
                    result.put("trade_state_desc", resultMap.get("msg"));
                    result.put("trade_state", "2");
                }
            }
        } catch (AlipayApiException e) {
            logger.error("", e);
            result.put("trade_state_desc", e.getMessage());
            result.put("trade_state", "0");
        }
        return result;
    }

    /**
     * 退款
     * @param outTradeNo 订单支付时传入的商户订单号,不能和 trade_no同时为空。
     * @param tradeNo 支付宝交易号，和商户订单号不能同时为空
     * @param outRequestNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     * @param refundAmount 退款金额
     * @param refundReason 退款原因
     * @return 支付参数
     */
    public static RefundResult refund(String outTradeNo, String tradeNo, String outRequestNo, BigDecimal refundAmount,
        String refundReason) {
        // 实例化客户端
        AlipayClient alipayClient = AliPayConfig.build().getAlipayClient();
        // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(outTradeNo);
        model.setTradeNo(tradeNo);
        model.setRefundAmount(refundAmount.toString());
        model.setRefundReason(refundReason);
        model.setOutRequestNo(outRequestNo);
        request.setBizModel(model);
        try {
            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            logger.info(response.getBody());
            if (!response.isSuccess()) {
                throw new RuntimeException(response.getSubMsg());
            }
            Map<?, ?> body = JSON.parseObject(response.getBody(), Map.class);
            Map<?, ?> result = JSON.parseObject(body.get("alipay_trade_refund_response").toString());
            return new RefundResult((String)result.get("trade_no"), outTradeNo, refundAmount.toString(),
                DateUtil.stringToDate((String)result.get("gmt_refund_pay")),
                "Y".equals(result.get("fund_change")) ? "1" : "2");
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }
}
