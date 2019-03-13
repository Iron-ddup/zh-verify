package com.formssi.verify.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 出入参拦截器日志记录
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Map<String, String[]> requestData = request.getParameterMap();
        logger.info("请求地址:{}", (request).getRequestURI());
        logger.info("客户端信息:{}", request.getHeader("user-agent"));
        logger.info("请求的参数:{}", JSON.toJSON(requestData) );
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
    }
}
