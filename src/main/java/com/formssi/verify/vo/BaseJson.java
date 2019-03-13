package com.formssi.verify.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by Jerry on 2018/11/14.
 */
public class BaseJson {
	private String blockNum;
	private String txnHash;
	@JSONField(name = "blockNum")
	public String getBlockNum() {
		return blockNum;
	}
	@JSONField(name = "blockNum")
	public void setBlockNum(String blockNum) {
		this.blockNum = blockNum;
	}
	@JSONField(name = "txnHash")
	public String getTxnHash() {
		return txnHash;
	}
	@JSONField(name = "txnHash")
	public void setTxnHash(String txnHash) {
		this.txnHash = txnHash;
	}
	
	public static BaseJson parse(String json) {
		BaseJson object=JSON.parseObject(json, BaseJson.class);
		return object;
	}
	
	public String toJSON() {
		return JSONObject.toJSONString(this);
	}
	
	@Override
	public String toString() {
		return "BaseJson{" +
			"blockNum=" + blockNum +
			", txnHash='" + txnHash + '\'' +
			'}';
	}
}
