package com.formssi.verify.server.impl;


import com.alibaba.fastjson.JSON;
import com.formssi.verify.domain.Block;
import com.formssi.verify.domain.OrderBalance;
import com.formssi.verify.domain.OrderGeneration;

import com.formssi.verify.server.BlockService;
import com.formssi.verify.wrapper.OrderBalanceVerify;
import com.formssi.verify.wrapper.OrderGenerationVerify;


import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.methods.response.BcosBlock;
import org.fisco.bcos.web3j.protocol.core.methods.response.BcosTransactionReceipt;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.math.BigInteger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class BlockServiceImpl implements BlockService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Web3j web3j;
	@Autowired
	private OrderGenerationVerify orderGenerationVerify;
	@Autowired
	private OrderBalanceVerify orderBalanceVerify;

	public int getBlockHigh() throws Exception {
		int high = 0;
		try {
			BigInteger blockHigh = web3j.getBlockNumber().send().getBlockNumber();
			high = Integer.valueOf(String.valueOf(blockHigh));
			logger.debug("getBlockHigh blockchain response:", blockHigh);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getBlockHigh to blockchain filed!!");
			throw new Exception("getBlockHigh to blockchain made a exception!");
		}
		return high;
	}

	public Block getBlockTransLogInfo(BigInteger blockNumber) throws Exception {
		Block block;
		try {


			block = getBlockTransInfo(blockNumber);
			System.out.println("read blockInfo from blockchain");
			logger.debug("getBlockTransLogInfo blockchain response:", block);
			return block;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getBlockTransLogInfo to blockchain filed!!");
			throw new Exception("getBlockTransLogInfo to blockchain made a exception!");
		}

	}

	public Block getBlockTransInfo(BigInteger blockNumber) {
		Block returnBlock = new Block();
		List<OrderBalance> blockOrderbInfoList = new ArrayList<>();
		List<OrderGeneration> blockOrdergInfoList = new ArrayList<>();
		 int blockSucCount=0;	//对账成功总笔数
		 int blockSucAmt=0;		//对账成功总金额
		 int blockExceCount=0;	//区块异常数据
		 int blockExceAmt=0;	//区块异常金额
		BcosBlock ethBlock = null;
		try {
			ethBlock = web3j.getBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), false).send();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BcosBlock.Block block = ethBlock.getBlock();

		returnBlock.setBlockNumber(String.valueOf(blockNumber));
		BigInteger blockTime = block.getTimestamp();
		//String blockTimeStr = DateUtils.formatDate(new Date(Long.valueOf(String.valueOf(blockTime))), "yyyy-MM-dd HH:mm:ss");
		Date blockTime2 = new Date(Long.valueOf(String.valueOf(blockTime)));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String blockTimeStr = formatter.format(blockTime2);


		returnBlock.setBlockTime(blockTimeStr);
		returnBlock.setPreBlockHash(block.getParentHash());
		returnBlock.setCurrBlockHash(block.getHash());

		List<BcosBlock.TransactionResult> transactionResults = block.getTransactions();

		for (BcosBlock.TransactionResult result : transactionResults) {
			BcosTransactionReceipt ethGetTransactionReceipt = null;
			try {
				ethGetTransactionReceipt = web3j.getTransactionReceipt((String) result.get()).send();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Optional<TransactionReceipt> opt = ethGetTransactionReceipt.getTransactionReceipt();
			if (opt.isPresent()) {
				TransactionReceipt receipt = opt.get();
				System.out.println(receipt.getLogs().size());
				if(receipt.getLogs().size()==0){continue;}

//				OrderBalanceVerify aa =new OrderBalanceVerify();
				List<OrderBalanceVerify.OrderBalanceReconciliationEventEventResponse> events= orderBalanceVerify.getOrderBalanceReconciliationEventEvents(receipt);
				if(events.size()==0){
					List<OrderGenerationVerify.OrderGeneratReconciliationEventEventResponse> events2 =orderGenerationVerify.getOrderGeneratReconciliationEventEvents(receipt);
					String[] sArray =devBlock(events2.get(0)._jsonStr.toString());
					if(sArray.length==1){
						OrderGeneration blockTxInfo=OrderGeneration.parse(sArray[0]);
						blockTxInfo.setJyType("1");
						blockTxInfo.setReconciliationState("0");
						blockOrdergInfoList.add(blockTxInfo);
						//统计成功的金额
						blockSucAmt +=Integer.parseInt(blockTxInfo.getCusAmt()) +Integer.parseInt(blockTxInfo.getCusFee());
						++blockSucCount;
					}else{
						OrderGeneration exceAmtObj=new OrderGeneration();
						for(int i=0 ;i< sArray.length;i++){
							System.out.println(sArray[i]);
							OrderGeneration blockTxInfo=OrderGeneration.parse(sArray[i]);
							blockTxInfo.setJyType("1");
							blockTxInfo.setReconciliationState("1");
							if(i==0){
								exceAmtObj=blockTxInfo;
							}

							blockOrdergInfoList.add(blockTxInfo);
						}
						//统计异常的金额
						blockExceAmt +=Integer.parseInt(exceAmtObj.getCusAmt()) +Integer.parseInt(exceAmtObj.getCusFee());
						++blockExceCount;
					}


				}else{
					String[] sArray =devBlock(events.get(0)._jsonStr.toString());
					if(sArray.length==1){
						OrderBalance blockTxBInfo=OrderBalance.parse(sArray[0]);
						blockTxBInfo.setJyType("0");
						blockTxBInfo.setReconciliationState("0");
						blockOrderbInfoList.add(blockTxBInfo);
						//统计成功的金额
						blockSucAmt+=Integer.parseInt(blockTxBInfo.getCusAmt()) +Integer.parseInt(blockTxBInfo.getCusFee());
						++blockSucCount;
					}else{
						OrderBalance exceAmtObj=new OrderBalance();
						for(int i=0 ;i< sArray.length;i++){

							OrderBalance blockTxBInfo=OrderBalance.parse(sArray[i]);
							blockTxBInfo.setJyType("0");
							blockTxBInfo.setReconciliationState("1");
							if(i==0){
								exceAmtObj=blockTxBInfo;
							}

							blockOrderbInfoList.add(blockTxBInfo);
						}
						//统计异常的金额
						blockExceAmt+=Integer.parseInt(exceAmtObj.getCusAmt()) +Integer.parseInt(exceAmtObj.getCusFee());
						++blockExceCount;
					}


				}


			}



			}

		returnBlock.setBlockSucCount(String.valueOf(blockSucCount));
		returnBlock.setBlockExceCount(String.valueOf(blockExceCount));
		returnBlock.setTransInfoG(blockOrdergInfoList);
		returnBlock.setTransInfoB(blockOrderbInfoList);
		returnBlock.setBlockSucAmt(String.valueOf(blockSucAmt));
		returnBlock.setBlockExceAmt(String.valueOf(blockExceAmt));
		return returnBlock;


	}


	//公共方法
	public String[] devBlock(String s){

		String sTemp = s.substring(1, s.length()-1);
		String[] sArray = sTemp.split("},");
		if(sArray.length>=2){
		for(int i=0;i<sArray.length-1;i++){
			sArray[i]=sArray[i]+"}";
		}
		}
		return  sArray;
	}
}

