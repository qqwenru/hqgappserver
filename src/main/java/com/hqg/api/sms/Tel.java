package com.hqg.api.sms;

public class Tel {

	private String nationCode;//国家码,如86
	private String mobile;//手机号码
	
	public String getNationCode() {
		return nationCode;
	}
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
