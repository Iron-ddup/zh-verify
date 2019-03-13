package com.formssi.verify.domain;

import lombok.Data;

@Data
public class TotalData {
    private String totalCount;//总笔数

    private String exceptionCount; //异常笔数

    private String exceptionAmt; //异常金额

    private String    totalAmt; //总金额


}
