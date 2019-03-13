package com.formssi.verify.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jerry on 2018/11/13.
 */
public class ResponseJson {
	private int result;
	private String data;
	
	@JSONField(name = "result")
	public int getResult() {
		return result;
	}
	@JSONField(name = "result")
	public void setResult(int result) {
		this.result = result;
	}
	@JSONField(name = "data")
	public String getData() {
		return data;
	}
	@JSONField(name = "data")
	public void setData(String data) {
		this.data = data;
	}
	
	public static ResponseJson parse(String json) {
		ResponseJson object=JSON.parseObject(json, ResponseJson.class);
		return object;
	}
	
	public String toJSON() {
		return JSONObject.toJSONString(this);
	}
	
	@Override
	public String toString() {
		return "ResponseJson{" +
			"result=" + result +
			", data='" + data + '\'' +
			'}';
	}
}
