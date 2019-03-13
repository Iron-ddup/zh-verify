package com.formssi.verify.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by Jerry on 2018/11/13.
 */
public class AccidentInfoVO {
	
	private String sqsj;   //申请时间，日期格式：yyyy-MM-dd HH:mm:ss
	private String ssyly;  //事实与理由(600个汉字)
	private String sqr;    //申请人(50个汉字)
	private String jjdjbh; //交通事故编号
	private String tjjg;   //调解机构
	private String tjry;   //调解人员
	private String bxjg;   //保险机构
	private String bzzy;   //保险专员
	private String jdjg;   //鉴定机构
	private String jdry;   //鉴定人员
	
	@JSONField(name="sqsj")
	public String getSqsj() {
		return sqsj;
	}
	@JSONField(name="sqsj")
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	
	@JSONField(name="ssyly")
	public String getSsyly() {
		return ssyly;
	}
	@JSONField(name="ssyly")
	public void setSsyly(String ssyly) {
		this.ssyly = ssyly;
	}
	@JSONField(name="sqr")
	public String getSqr() {
		return sqr;
	}
	@JSONField(name="sqr")
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	@JSONField(name="jjdjbh")
	public String getJjdjbh() {
		return jjdjbh;
	}
	@JSONField(name="jjdjbh")
	public void setJjdjbh(String jjdjbh) {
		this.jjdjbh = jjdjbh;
	}
	@JSONField(name="tjjg")
	public String getTjjg() {
		return tjjg;
	}
	@JSONField(name="tjjg")
	public void setTjjg(String tjjg) {
		this.tjjg = tjjg;
	}
	@JSONField(name="tjry")
	public String getTjry() {
		return tjry;
	}
	@JSONField(name="tjry")
	public void setTjry(String tjry) {
		this.tjry = tjry;
	}
	@JSONField(name="bxjg")
	public String getBxjg() {
		return bxjg;
	}
	@JSONField(name="bxjg")
	public void setBxjg(String bxjg) {
		this.bxjg = bxjg;
	}
	@JSONField(name="bzzy")
	public String getBzzy() {
		return bzzy;
	}
	@JSONField(name="bzzy")
	public void setBzzy(String bzzy) {
		this.bzzy = bzzy;
	}
	@JSONField(name="jdjg")
	public String getJdjg() {
		return jdjg;
	}
	@JSONField(name="jdjg")
	public void setJdjg(String jdjg) {
		this.jdjg = jdjg;
	}
	@JSONField(name="jdry")
	public String getJdry() {
		return jdry;
	}
	@JSONField(name="jdry")
	public void setJdry(String jdry) {
		this.jdry = jdry;
	}
	
	public static AccidentInfoVO parse(String json) {
		AccidentInfoVO object=JSON.parseObject(json, AccidentInfoVO.class);
		return object;
	}
	
	public String toJSON() {
		return JSONObject.toJSONString(this);
	}
	
	@Override
	public String toString() {
		return "AccidentInfoVO{" +
			"sqsj='" + sqsj + '\'' +
			", ssyly='" + ssyly + '\'' +
			", sqr='" + sqr + '\'' +
			", jjdjbh='" + jjdjbh + '\'' +
			", tjjg='" + tjjg + '\'' +
			", tjry='" + tjry + '\'' +
			", bxjg='" + bxjg + '\'' +
			", bzzy='" + bzzy + '\'' +
			", jdjg='" + jdjg + '\'' +
			", jdry='" + jdry + '\'' +
			'}';
	}
}
