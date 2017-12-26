/**
 * 
 */
package top.ibase4j.core.support.pay.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author ShenHuaJie
 * @version 2017年11月30日 下午6:04:37
 */
@SuppressWarnings("serial")
public class RefundResult implements Serializable {
    private String refundId;
    private String outRefundNo;
    private String refundFee;
    private Date refundTime;
    private String refundResult = "1";

    public RefundResult(String refundId, String outRefundNo, String refundFee, Date refundTime) {
        this.refundId = refundId;
        this.outRefundNo = outRefundNo;
        this.refundFee = refundFee;
        this.refundTime = refundTime;
    }

    public RefundResult(String refundId, String outRefundNo, String refundFee, Date refundTime, String refundResult) {
        super();
        this.refundId = refundId;
        this.outRefundNo = outRefundNo;
        this.refundFee = refundFee;
        this.refundTime = refundTime;
        this.refundResult = refundResult;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getRefundResult() {
        return refundResult;
    }

    public void setRefundResult(String refundResult) {
        this.refundResult = refundResult;
    }
}
