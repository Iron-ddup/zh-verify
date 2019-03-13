package com.formssi.verify.dto;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.formssi.verify.domain.Account;
import lombok.Data;

@Data
public class AccountDTO {
 @JSONField(name ="accountId")
 private String accountId;
 @JSONField(name ="pwd")
 private String pwd;
 @JSONField(name ="userName")
 private String userName;
 public static AccountDTO parse(String json) {
  AccountDTO object=JSON.parseObject(json, AccountDTO.class);
  return object;
 }

 public String toJSON() {
  return JSONObject.toJSONString(this);
 }


}
