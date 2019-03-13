package com.formssi.verify.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.formssi.verify.domain.OrderBalance;
import com.formssi.verify.domain.OrderGeneration;
import com.formssi.verify.domain.TotalData;
import com.formssi.verify.dto.ReturnJson;
import com.formssi.verify.dto.SelectObj;
import com.formssi.verify.server.ZhVerifyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("zhPoc")
public class ZhVerify {

@Autowired
private ZhVerifyService zhVerifyService;
    //首页统计本月对账总笔数、本月对账异常笔数、本月对账异常总金额显示（包括贷款和还款）
@RequestMapping(value ="/summary" , produces = "application/json;charset=UTF-8")
public String  summary(@RequestParam("data") String data, HttpServletResponse response){
    response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
    System.out.println(data);
    ReturnJson returnJson = new ReturnJson();
    SelectObj selectObj=SelectObj.parse(data);
    TotalData accountback= zhVerifyService.getsummary(selectObj);
    returnJson.setSuccess(true);
    returnJson.setMessage("查询成功！");
   returnJson.setObj(accountback);
    return returnJson.toJSON();
}

    @RequestMapping(value ="/queryDebitRecord" , produces = "application/json;charset=UTF-8")
    public String  queryDebitRecord(@RequestParam("data") String data, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
        System.out.println(data);
        ReturnJson returnJson = new ReturnJson();
        SelectObj selectObj=SelectObj.parse(data);
        PageHelper.startPage(selectObj.getPage(),selectObj.getPageSize());
        List<OrderBalance> OrderBalnaceDebitRecordList= zhVerifyService.getOrderBalnaceDebitRecord(selectObj);
        PageInfo<OrderBalance> page = new PageInfo(OrderBalnaceDebitRecordList, selectObj.getPageSize());
        returnJson.setSuccess(true);
        returnJson.setMessage("记录订单余额表成功！");
        returnJson.setObj(page);
        return returnJson.toJSON();
    }

    @RequestMapping(value ="/queryLoanRecord" , produces = "application/json;charset=UTF-8")
    public String  queryLoanRecord(@RequestParam("data") String data, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
        System.out.println(data);
        ReturnJson returnJson = new ReturnJson();
        SelectObj selectObj=SelectObj.parse(data);
        PageHelper.startPage(selectObj.getPage(),selectObj.getPageSize());
        List<OrderGeneration> orderGenerationDebitRecordList= zhVerifyService.getOrderGenerationDebitRecord(selectObj);
        PageInfo<OrderGeneration> page = new PageInfo(orderGenerationDebitRecordList, selectObj.getPageSize());
        returnJson.setSuccess(true);
        returnJson.setMessage("记录订单余额表成功！");
        returnJson.setObj(page);
        return returnJson.toJSON();
    }

    @RequestMapping(value = "/queryBalanceRecordById",produces = "application/json;charset=UTF-8")
    public String queryBalanceRecordById(@RequestParam("data") String data,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
        ReturnJson returnJson = new ReturnJson();
        SelectObj selectObj=SelectObj.parse(data);

        List<JSONObject>  OrderBalnaceDebitRecordList= zhVerifyService.queryBalanceRecordById(selectObj);

        returnJson.setSuccess(true);
        returnJson.setMessage("orderId查询结果！");
        returnJson.setObj(OrderBalnaceDebitRecordList);
        return returnJson.toJSON();
    }

    @RequestMapping(value = "/queryGenerationRecordById",produces = "application/json;charset=UTF-8")
    public String queryGenerationRecordById(@RequestParam("data") String data,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
        ReturnJson returnJson = new ReturnJson();
        SelectObj selectObj=SelectObj.parse(data);

        List<JSONObject> orderGenerationDebitRecordList= zhVerifyService.queryGenerationRecordById(selectObj);

        returnJson.setSuccess(true);
        returnJson.setMessage("orderId查询结果！");
        returnJson.setObj(orderGenerationDebitRecordList);
        return returnJson.toJSON();
    }

}
