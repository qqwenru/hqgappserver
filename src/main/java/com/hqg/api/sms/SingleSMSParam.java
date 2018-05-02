package com.hqg.api.sms;

import java.io.Serializable;

/**
 * 单条短信请求参数

 * @Description
 * @date 2017年6月22日
 * @since 1.0
 * @version 1.0
 */
 /*	
 {
	    "tel": { //如需使用国际电话号码通用格式，如："+8613788888888" ，请使用sendisms接口见下注
	        "nationcode": "86", //国家码
	        "mobile": "13788888888" //手机号码
	    }, 
	    "type": 0, //0:普通短信;1:营销短信（强调：要按需填值，不然会影响到业务的正常使用）
	    "msg": "你的验证码是1234", //utf8编码 
	    "sig": "30db206bfd3fea7ef0db929998642c8ea54cc7042a779c5a0d9897358f6e9505", //app凭证，具体计算方式见下注
	    "time": 1457336869, //unix时间戳，请求发起时间，如果和系统时间相差超过10分钟则会返回失败
	    "extend": "", //通道扩展码，可选字段，默认没有开通(需要填空)。
	     //在短信回复场景中，腾讯server会原样返回，开发者可依此区分是哪种类型的回复
	    "ext": "" //用户的session内容，腾讯server回包中会原样返回，可选字段，不需要就填空。
	}
 */
public class SingleSMSParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Tel tel;
	private Integer type = 0;//0:普通短信;1:营销短信
	private String sig;//app凭证
	private Long time;//时间戳10位
	private String extend;//通道扩展码
	private String ext;//用户的session内容，原样返回，可选字段，不需要就填空。
	private String msg;//消息
	public Tel getTel() {
		return tel;
	}
	public void setTel(Tel tel) {
		this.tel = tel;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSig() {
		return sig;
	}
	public void setSig(String sig) {
		this.sig = sig;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
