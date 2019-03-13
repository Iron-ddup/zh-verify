package com.formssi.verify.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("zh_order_generation")
public class OrderGeneration {
    @JSONField(name="from")
    private String from            ;           //  0-招行，1-其他
    @JSONField(name="ordNo")
    private String	ordNo       ;	        //	订单号
    @JSONField(name="txnTyp")
    private String	txnTyp	    ;	        //	交易类型
    @JSONField(name="accNo")
    private String	accNo	    ;	        //	账户号
    @JSONField(name="date")
    private String	date	    ;	        //	日期
    @JSONField(name="cusAmt")
    private String	cusAmt	    ;	        //	交易本金
    @JSONField(name="cusFee")
    private String	cusFee	    ;	        //	交易手续费
    @JSONField(name="totTimes")
    private String	totTimes	;	        //	分期期数
    @JSONField(name="partnerCd")
    private String	partnerCd  	;	        //	合作方标识
    @JSONField(name="fqCaseNo")
    private String	fqCaseNo	;	        //	分期案件编号
    @JSONField(name="transHash")
    private String	transHash	;	        //	交易hash	链下数据库存储
    @JSONField(name="reconciliationState")
    private String	reconciliationState	;	//	对账状态	0-正常，1-异常
    @JSONField(name="jyType")      //还款发生0 贷款发生1
    private String jyType;
    
    public static OrderGeneration parse(String json) {
        OrderGeneration object=JSON.parseObject(json, OrderGeneration.class);
        return object;
    }
    
    public String toJSON() {
        return JSONObject.toJSONString(this);
    }
    
}
