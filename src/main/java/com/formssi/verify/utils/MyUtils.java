package com.formssi.verify.utils;


import com.formssi.verify.vo.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class MyUtils {
	private final static Logger logger = LoggerFactory.getLogger(MyUtils.class);
	
	public static ResponseJson catchExcp(Exception e, ResponseJson responseJson, String msg){
		responseJson.setResult(1);
		responseJson.setData(null);
		logger.error("(|||ﾟдﾟ)===>'"+msg+"'失败");
		e.printStackTrace();
		return responseJson;
	}
	/**
	 * 检查区块链返回的结果
	 */
	public static ResponseJson checkResult(ResponseJson responseJson,String result){

		logger.info("(◉ω◉)===>resultJson:"+result);
		if (ValidateUtils.areEqual("2",result)){
			responseJson.setResult(2);
			responseJson.setData("查区块链失败，回执为空！");
		}else if (ValidateUtils.areEqual("3",result)){
			responseJson.setResult(3);
			responseJson.setData("查区块链成功，返回数据为空！");
		}else {
			responseJson.setResult(0);
			responseJson.setData(result);
		}
		return responseJson;
	}
}
