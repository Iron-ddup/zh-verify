package com.formssi.verify.dto;

import lombok.Data;

/**
 * Created by Jerry on 2019/1/7.
 */
@Data
public class OrderGeneration {
    private String	ordNo       ;	        //	订单号
    private String	txnTyp	    ;	        //	交易类型
    private String	accNo	    ;	        //	账户号
    private String	date	    ;	        //	日期
    private String	cusAmt	    ;	        //	交易本金
    private String	cusFee	    ;	        //	交易手续费
    private String	totTimes	;	        //	分期期数
    private String	partnerCd  	;	        //	合作方标识
    private String	fqCaseNo	;	        //	分期案件编号
    private String	transHash	;	        //	交易hash	链下数据库存储
    private String	reconciliationState	;	//	对账状态	0-正常，1-异常
}
