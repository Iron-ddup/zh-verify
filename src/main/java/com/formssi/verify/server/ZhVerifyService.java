package com.formssi.verify.server;

import com.alibaba.fastjson.JSONObject;
import com.formssi.verify.domain.OrderBalance;
import com.formssi.verify.domain.OrderGeneration;
import com.formssi.verify.domain.TotalData;
import com.formssi.verify.dto.SelectObj;

import java.util.List;

public interface ZhVerifyService {
    TotalData getsummary(SelectObj selectObj);


    List<OrderBalance> getOrderBalnaceDebitRecord(SelectObj selectObj);

    List<OrderGeneration> getOrderGenerationDebitRecord(SelectObj selectObj);

    List<JSONObject> queryBalanceRecordById(SelectObj selectObj);

    List<JSONObject> queryGenerationRecordById(SelectObj selectObj);
}
