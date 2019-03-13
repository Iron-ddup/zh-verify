package com.formssi.verify.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.List;

@TableName("block")
public class Block {
	
	private String blockNumber;		//	区块号
	private String blockTime;		//	创建区块时间
	private String preBlockHash;	//	前区块hash
	private String currBlockHash;	//	当前区块hash
	private String blockSucCount;	//对账成功总笔数
	private String blockSucAmt;		//对账成功总金额
	private String blockExceCount;	//区块异常数据
	private String blockExceAmt;	//区块异常金额

	private List<OrderBalance> transInfoB;		//	交易信息
	private List<OrderGeneration> transInfoG;		//	交易信息
	@JSONField(name = "transInfoB")
	public List<OrderBalance> getTransInfoB() {
		return transInfoB;
	}
	@JSONField(name = "transInfoB")
	public void setTransInfoB(List<OrderBalance> transInfoB) {
		this.transInfoB = transInfoB;
	}
	@JSONField(name = "transInfoG")
	public List<OrderGeneration> getTransInfoG() {
		return transInfoG;
	}
	@JSONField(name = "transInfoG")
	public void setTransInfoG(List<OrderGeneration> transInfoG) {
		this.transInfoG = transInfoG;
	}

	

	@JSONField(name = "blockSucCount")
	public String getBlockSucCount() {
		return blockSucCount;
	}
	@JSONField(name = "blockSucCount")
	public void setBlockSucCount(String blockSucCount) {
		this.blockSucCount = blockSucCount;
	}
	@JSONField(name = "blockSucAmt")
	public String getBlockSucAmt() {
		return blockSucAmt;
	}
	@JSONField(name = "blockSucAmt")
	public void setBlockSucAmt(String blockSucAmt) {
		this.blockSucAmt = blockSucAmt;
	}
	@JSONField(name = "blockExceCount")
	public String getBlockExceCount() {
		return blockExceCount;
	}
	@JSONField(name = "blockExceCount")
	public void setBlockExceCount(String blockExceCount) {
		this.blockExceCount = blockExceCount;
	}
	@JSONField(name = "blockExceAmt")
	public String getBlockExceAmt() {
		return blockExceAmt;
	}
	@JSONField(name = "blockExceAmt")
	public void setBlockExceAmt(String blockExceAmt) {
		this.blockExceAmt = blockExceAmt;
	}

	@JSONField(name = "blockNumber")
	public String getBlockNumber() {
		return blockNumber;
	}

	@JSONField(name = "blockNumber")
	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}

	@JSONField(name = "blockTime")
	public String getBlockTime() {
		return blockTime;
	}

	@JSONField(name = "blockTime")
	public void setBlockTime(String blockTime) {
		this.blockTime = blockTime;
	}
	
	@JSONField(name = "currBlockHash")
	public String getCurrBlockHash() {
		return currBlockHash;
	}

	@JSONField(name = "currBlockHash")
	public void setCurrBlockHash(String currBlockHash) {
		this.currBlockHash = currBlockHash;
	}

	@JSONField(name = "preBlockHash")
	public String getPreBlockHash() {
		return preBlockHash;
	}

	@JSONField(name = "preBlockHash")
	public void setPreBlockHash(String preBlockHash) {
		this.preBlockHash = preBlockHash;
	}
	


	public static Block parse(String json) {
		Block object=JSON.parseObject(json, Block.class);
		return object;
	}
	
	public String toJSON() {
		return JSONObject.toJSONString(this);
	}

	@Override
	public String toString() {
		return "Block{" +
				"blockNumber='" + blockNumber + '\'' +
				", blockTime='" + blockTime + '\'' +
				", preBlockHash='" + preBlockHash + '\'' +
				", currBlockHash='" + currBlockHash + '\'' +
				", blockSucCount='" + blockSucCount + '\'' +
				", blockSucAmt='" + blockSucAmt + '\'' +
				", blockExceCount='" + blockExceCount + '\'' +
				", blockExceAmt='" + blockExceAmt + '\'' +
				", transInfoB=" + transInfoB +
				", transInfoG=" + transInfoG +
				'}';
	}
}
