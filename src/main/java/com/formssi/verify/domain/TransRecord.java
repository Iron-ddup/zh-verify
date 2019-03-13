package com.formssi.verify.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Jerry on 2018/8/16.
 */
public class TransRecord {
	private String serialNum;			//流水号
	private Timestamp transTime;		//交易时间
	private String accountID;			//账户Id
	private BigDecimal amount;			//交易金额
	private int transferType;			//转出(0)/转入(1)
	private int transactionType;		//支出类型 0-异化 1-消费
	private int operAccount;			//操作子账户 0-贷款 1-自有 2-进项
	private BigDecimal selfOwnAccount;	//自有资金账户余额
	private BigDecimal loansAccount;	//贷款资金账户余额
	private BigDecimal transferAccount;	//进项资金账户余额
	private int isIllegality;			//交易是否违规 违规-0 不违规-1
	private BigDecimal illegalityFund;	//违规金额
	private BigInteger blockNum;		//块号
	
	private int pageNum; 				//当前是第几页
	private int pageSize; 				//每页显示多少条
	private String address;             //关联一下address
	private BigDecimal onlineBalance;	//联机金额
	private String identifyID;			//身份证号
	private String name;				//姓名
	
	@JSONField(name = "identifyID")
	public String getIdentifyID() {
		return identifyID;
	}
	@JSONField(name = "identifyID")
	public void setIdentifyID(String identifyID) {
		this.identifyID = identifyID;
	}
	
	@JSONField(name = "name")
	public String getName() {
		return name;
	}
	@JSONField(name = "name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JSONField(name = "onlineBalance")
	public BigDecimal getOnlineBalance() {
		return onlineBalance;
	}
	@JSONField(name = "onlineBalance")
	public void setOnlineBalance(BigDecimal onlineBalance) {
		this.onlineBalance = onlineBalance;
	}
	
	@JSONField(name = "address")
	public String getAddress() {
		return address;
	}
	@JSONField(name = "address")
	public void setAddress(String address) {
		this.address = address;
	}
	@JSONField(name = "serialNum")
	public String getSerialNum() {
		return serialNum;
	}
	@JSONField(name = "serialNum")
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	
	@JSONField(name = "transTime")
	public String getTransTime() {
		return transTime.toString();
	}
	@JSONField(name = "transTime")
	public void setTransTime(Timestamp transTime) {
		this.transTime = transTime;
	}
	
	@JSONField(name = "accountID")
	public String getAccountID() {
		return accountID;
	}
	@JSONField(name = "accountID")
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	
	@JSONField(name = "amount")
	public BigDecimal getAmount() {
		return amount;
	}
	@JSONField(name = "amount")
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@JSONField(name = "transferType")
	public int getTransferType() {
		return transferType;
	}
	@JSONField(name = "transferType")
	public void setTransferType(int transferType) {
		this.transferType = transferType;
	}
	
	@JSONField(name = "transactionType")
	public int getTransactionType() {
		return transactionType;
	}
	@JSONField(name = "transactionType")
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	
	@JSONField(name = "operAccount")
	public int getOperAccount() {
		return operAccount;
	}
	@JSONField(name = "operAccount")
	public void setOperAccount(int operAccount) {
		this.operAccount = operAccount;
	}
	
	@JSONField(name = "selfOwnAccount")
	public BigDecimal getSelfOwnAccount() {
		return selfOwnAccount;
	}
	@JSONField(name = "selfOwnAccount")
	public void setSelfOwnAccount(BigDecimal selfOwnAccount) {
		this.selfOwnAccount = selfOwnAccount;
	}
	
	@JSONField(name = "loansAccount")
	public BigDecimal getLoansAccount() {
		return loansAccount;
	}
	@JSONField(name = "loansAccount")
	public void setLoansAccount(BigDecimal loansAccount) {
		this.loansAccount = loansAccount;
	}
	
	@JSONField(name = "transferAccount")
	public BigDecimal getTransferAccount() {
		return transferAccount;
	}
	@JSONField(name = "transferAccount")
	public void setTransferAccount(BigDecimal transferAccount) {
		this.transferAccount = transferAccount;
	}
	
	@JSONField(name = "isIllegality")
	public int getIsIllegality() {
		return isIllegality;
	}
	@JSONField(name = "isIllegality")
	public void setIsIllegality(int isIllegality) {
		this.isIllegality = isIllegality;
	}
	
	@JSONField(name = "illegalityFund")
	public BigDecimal getIllegalityFund() {
		return illegalityFund;
	}
	@JSONField(name = "illegalityFund")
	public void setIllegalityFund(BigDecimal illegalityFund) {
		this.illegalityFund = illegalityFund;
	}
	
	@JSONField(name = "blockNum")
	public BigInteger getBlockNum() {
		return blockNum;
	}
	@JSONField(name = "blockNum")
	public void setBlockNum(BigInteger blockNum) {
		this.blockNum = blockNum;
	}
	
	@JSONField(name = "pageNum")
	public int getPageNum() {
		return pageNum;
	}
	@JSONField(name = "pageNum")
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	@JSONField(name = "pageSize")
	public int getPageSize() {
		return pageSize;
	}
	@JSONField(name = "pageSize")
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public static TransRecord parse(String json) {
		TransRecord object=JSON.parseObject(json, TransRecord.class);
		return object;
	}
	
	public String toJSON() {
		return JSONObject.toJSONString(this);
	}

	@Override
	public String toString() {
		return "TransRecord{" +
				"serialNum='" + serialNum + '\'' +
				", transTime=" + transTime +
				", accountID='" + accountID + '\'' +
				", amount=" + amount +
				", transferType=" + transferType +
				", transactionType=" + transactionType +
				", operAccount=" + operAccount +
				", selfOwnAccount=" + selfOwnAccount +
				", loansAccount=" + loansAccount +
				", transferAccount=" + transferAccount +
				", isIllegality=" + isIllegality +
				", illegalityFund=" + illegalityFund +
				", blockNum=" + blockNum +
				", pageNum=" + pageNum +
				", pageSize=" + pageSize +
				", address='" + address + '\'' +
				", onlineBalance=" + onlineBalance +
				", identifyID='" + identifyID + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
