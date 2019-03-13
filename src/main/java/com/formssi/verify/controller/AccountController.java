package com.formssi.verify.controller;


import com.formssi.verify.domain.Account;
import com.formssi.verify.dto.AccountDTO;
import com.formssi.verify.dto.ReturnJson;

import com.formssi.verify.server.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
*
* */
@RestController
@RequestMapping("zhPoc")
public class AccountController {
@Autowired AccountService accountService;
    //登录
    @RequestMapping("login")
    public String login(@RequestParam("data") String data, HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
        AccountDTO accountDTO = AccountDTO.parse(data);
        ReturnJson returnJson = new ReturnJson();
        Account accountback= accountService.login(accountDTO);
        if (null == accountback) {
            returnJson.setSuccess(false);
            returnJson.setMessage("用户不存在！");

        }else{
            returnJson.setSuccess(true);
            returnJson.setMessage("登录成功！");
            returnJson.setObj(accountback);
        }

        return returnJson.toJSON();
    }

}
