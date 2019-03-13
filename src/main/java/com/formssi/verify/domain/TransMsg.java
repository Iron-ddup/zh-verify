package com.formssi.verify.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Jerry on 2018/8/24.
 * 该对象是每个区块中的交易对象
 */
public class TransMsg {
	private String transHash;	//交易hash
	private int transNumber;	//交易序号
	private String transMsg;	//交易信息
	
	@JSONField(name = "transHash")
	public String getTransHash() {
		return transHash;
	}
	@JSONField(name = "transHash")
	public void setTransHash(String transHash) {
		this.transHash = transHash;
	}
	
	@JSONField(name = "transNumber")
	public int getTransNumber() {
		return transNumber;
	}
	@JSONField(name = "transNumber")
	public void setTransNumber(int transNumber) {
		this.transNumber = transNumber;
	}
	
	@JSONField(name = "transMsg")
	public String getTransMsg() {
		return transMsg;
	}
	@JSONField(name = "transMsg")
	public void setTransMsg(String transMsg) {
		this.transMsg = transMsg;
	}
	
	public static TransMsg parse(String json) {
		TransMsg object=JSON.parseObject(json, TransMsg.class);
		return object;
	}
	
	public String toJSON() {
		return JSONObject.toJSONString(this);
	}
	
	@Override
	public String toString() {
		return "TransMsg{" +
			"transHash='" + transHash + '\'' +
			", transNumber=" + transNumber +
			", transMsg='" + transMsg + '\'' +
			'}';
	}
}
