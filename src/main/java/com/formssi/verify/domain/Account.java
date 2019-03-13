package com.formssi.verify.domain;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;

@Data
@TableName("account")
public class Account {
    @JSONField(name = "accountId")
    private String accountId;
    @JSONField(name = "pwd")
    private String pwd;
    @JSONField(name = "userName")
    private String userName;

    public static Account  parse(String json) {
        Account object=JSON.parseObject(json, Account.class);
        return object;
    }

    public String toJSON() {
        return JSONObject.toJSONString(this);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
