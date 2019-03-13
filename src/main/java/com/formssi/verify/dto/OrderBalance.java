package com.formssi.verify.dto;

import lombok.Data;

/**
 * Created by Jerry on 2019/1/7.
 */
@Data
public class OrderBalance {
    private int from            ;   //  0-招行，1-其他
    private String	date	    ;	//	日期
    private String	accNo	    ;	//	账户号
    private String	ordNo	    ;	//	订单号
    private String	cusAmt	    ;	//	本金总额
    private String	cusFee	    ;	//	手续费总额
    private String	totTimes	;	//	分期期数
    private String	wpTimes	    ;	//	剩余期数
    private String	wpAmt	    ;	//	剩余本金
    private String	partnerCd	;	//	合作方标识
    private String	fqCaseNo	;	//	分期案件编号
    private String	trans_hash	;	//	交易hash
    private String	reconciliationState	;	//	对账状态
}
