package com.formssi.verify.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.formssi.verify.domain.Block;
import lombok.Data;

@Data
public class SelectObj {
    @JSONField(name = "ordNo")
    private String ordNo;
    @JSONField(name = "accNo")
    private String accNo;
    @JSONField(name = "startDate")
    private String startDate;
    @JSONField(name = "endDate")
    private String endDate;
    @JSONField(name = "jxType")
    private String jxType;
    @JSONField(name = "dzType")
    private String dzType; //0-对账成功，1-对账失败
    @JSONField(name = "page")
    private int page;
    @JSONField(name = "pageSize")
    private int pageSize;

    public static SelectObj parse(String json) {
        SelectObj object=JSON.parseObject(json, SelectObj.class);
        return object;
    }

    public String toJSON() {
        return JSONObject.toJSONString(this);
    }
}
