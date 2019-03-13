package com.formssi.verify.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("zh_order_balance")
public class OrderBalance {
    @JSONField(name="from")
    private String from            ;   //  0-招行，1-其他
    @JSONField(name="date")
    private String	date	    ;	//	日期
    @JSONField(name="accNo")
    private String	accNo	    ;	//	账户号
    @JSONField(name="ordNo")
    private String	ordNo	    ;	//	订单号
    @JSONField(name="cusAmt")
    private String	cusAmt	    ;	//	本金总额
    @JSONField(name="cusFee")
    private String	cusFee	    ;	//	手续费总额
    @JSONField(name="totTimes")
    private String	totTimes	;	//	分期期数
    @JSONField(name="wpTimes")
    private String	wpTimes	    ;	//	剩余期数
    @JSONField(name="wpAmt")
    private String	wpAmt	    ;	//	剩余本金
    @JSONField(name="partnerCd")
    private String	partnerCd	;	//	合作方标识
    @JSONField(name="fqCaseNo")
    private String	fqCaseNo	;	//	分期案件编号
    @JSONField(name="trans_hash")
    private String	trans_hash	;	//	交易hash
    @JSONField(name="reconciliationState")
    private String	reconciliationState	;	//	对账状态
    @JSONField(name="jyType")      //还款发生0 贷款发生1
    private String jyType;
    
    public static OrderBalance parse(String json) {
        OrderBalance object=JSON.parseObject(json, OrderBalance.class);
        return object;
    }
    
    public String toJSON() {
        return JSONObject.toJSONString(this);
    }
}
