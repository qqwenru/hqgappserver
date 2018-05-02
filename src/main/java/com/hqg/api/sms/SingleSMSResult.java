package com.hqg.api.sms;

import java.io.Serializable;

/**
 * 单次短信应答结果
 * @author 1
 *
 */
public class SingleSMSResult implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer result;//0表示成功(计费依据)，非0表示失败
	private String errmsg;//result非0时的具体错误信息
	private String ext;//用户的session内容，腾讯server回包中会原样返回
	private String sid;//标识本次发送id，标识一次短信下发记录
	private Integer fee;//短信计费的条数
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	
}
