package com.hqg.api.sms;

import java.util.List;

public class MassSMSResult {
	private Integer result;//0表示成功(计费依据)，非0表示失败
	private String errmsg;//result非0时的具体错误信息
	private String ext;//用户的session内容，腾讯server回包中会原样返回
	private List<MassDetail> detail;//短信计费的条数
	
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

	public List<MassDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<MassDetail> detail) {
		this.detail = detail;
	}

	class MassDetail{
		private Integer result;//0表示成功(计费依据)，非0表示失败
		private String errmsg;//result非0时的具体错误信息
		private String mobile;//手机号码
		private String nationcode;//国家码
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
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getNationcode() {
			return nationcode;
		}
		public void setNationcode(String nationcode) {
			this.nationcode = nationcode;
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
}

