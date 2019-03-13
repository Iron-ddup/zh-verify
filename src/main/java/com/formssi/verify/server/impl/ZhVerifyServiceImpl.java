package com.formssi.verify.server.impl;

import com.alibaba.fastjson.JSONObject;
import com.formssi.verify.utils.PropertiesUtil;
import com.formssi.verify.domain.OrderBalance;
import com.formssi.verify.domain.OrderGeneration;
import com.formssi.verify.domain.TotalData;
import com.formssi.verify.dto.SelectObj;
import com.formssi.verify.mapper.BlockMapper;
import com.formssi.verify.server.ZhVerifyService;
import com.formssi.verify.wrapper.OrderBalanceVerify;
import com.formssi.verify.wrapper.OrderGenerationVerify;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
public class ZhVerifyServiceImpl implements ZhVerifyService {

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;
    BigInteger gasPrice = new BigInteger("300000000000000");
    BigInteger gasLimit = new BigInteger("300000000000000");
    public TotalData getsummary(SelectObj selectObj){
        TotalData totalData=new TotalData();

        if(selectObj.getJxType().equals("0")){
            //jxType:0-还款，1-贷款
             totalData=blockMapper.getSummary(selectObj.getStartDate(),selectObj.getEndDate());
        }else if(selectObj.getJxType().equals("1")){
            //贷款的统计数据
            totalData=blockMapper.getSummaryGeneration(selectObj.getStartDate(),selectObj.getEndDate());
        }else{

            TotalData orderBalanceDataTotal=new TotalData();
            orderBalanceDataTotal=blockMapper.getSummary(selectObj.getStartDate(),selectObj.getEndDate());
            totalData=blockMapper.getSummaryGeneration(selectObj.getStartDate(),selectObj.getEndDate());
            //总交易数
           int totalCount=Integer.parseInt(orderBalanceDataTotal.getTotalCount())+Integer.parseInt(totalData.getTotalCount());
           int exceptionCount=Integer.parseInt(orderBalanceDataTotal.getExceptionCount())+Integer.parseInt(totalData.getExceptionCount());
           int exceptionAmt=Integer.parseInt(orderBalanceDataTotal.getExceptionAmt())+Integer.parseInt(totalData.getExceptionAmt());
           int totalAmt=Integer.parseInt(orderBalanceDataTotal.getTotalAmt())+Integer.parseInt(totalData.getTotalAmt());
           totalData.setTotalCount(String.valueOf(totalCount));
           totalData.setExceptionCount(String.valueOf(exceptionCount));
           totalData.setExceptionAmt(String.valueOf(exceptionAmt));
           totalData.setTotalAmt(String.valueOf(totalAmt));
        }


        return totalData ;
    }

    public List<OrderBalance> getOrderBalnaceDebitRecord(SelectObj selectObj){

        List<OrderBalance> OrderBalnaceDebitRecordList=blockMapper.getOrderBalnaceDebitRecordList(selectObj);

        return OrderBalnaceDebitRecordList ;
    }


    public List<OrderGeneration> getOrderGenerationDebitRecord(SelectObj selectObj){
       // PageHelper.startPage(selectObj.getPage(),selectObj.getPageSize());
        List<OrderGeneration> OrderGenerationDebitRecordList=blockMapper.getOrderGenerationDebitRecordList(selectObj);

        return OrderGenerationDebitRecordList ;
    }


    public  List<JSONObject> queryGenerationRecordById(SelectObj selectObj){
        String verifyGenMappingContAddr="";
        try {
            PropertiesUtil.readFile("application.properties");
            verifyGenMappingContAddr=PropertiesUtil.readValue("contract.address.genverifymapping");
        } catch (IOException e) {
            e.printStackTrace();
        }
        OrderGenerationVerify orderGenerationVerify = OrderGenerationVerify.load(verifyGenMappingContAddr, web3j, credentials, gasPrice, gasLimit);

        String valueByOrdNo=null;

             valueByOrdNo=orderGenerationVerify.GetById(new BigInteger(selectObj.getOrdNo())).toString();


        List<JSONObject> jsonList=JSONObject.parseArray(valueByOrdNo,JSONObject.class);


        return jsonList;
    }


    public List<JSONObject> queryBalanceRecordById(SelectObj selectObj){
        String verifyBalMappingContAddr="";
        try {
            PropertiesUtil.readFile("application.properties");
            verifyBalMappingContAddr=PropertiesUtil.readValue("contract.address.balverifymapping");
        } catch (IOException e) {
            e.printStackTrace();
        }
        OrderBalanceVerify orderBalrationVerify = OrderBalanceVerify.load(verifyBalMappingContAddr, web3j, credentials, gasPrice, gasLimit);

        String valueByOrdNo=null;

            valueByOrdNo=orderBalrationVerify.GetById(new BigInteger(selectObj.getOrdNo())).toString();

        List<JSONObject> jsonList=JSONObject.parseArray(valueByOrdNo,JSONObject.class);

        return jsonList;
    }


}
