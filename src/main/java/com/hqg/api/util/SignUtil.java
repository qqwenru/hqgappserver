package com.hqg.api.util;


import com.hqg.api.sms.Tel;

import java.util.List;

public class SignUtil {
		
	/**
	 * @Description 短信签名
	 * @date 2017年6月22日
	 * @since 1.0
	 * @version 1.0
	 */
	public static String singleSmsSign(String appkey, long random, long time, String mobile)throws Exception{
		StringBuffer sb = new StringBuffer("appkey="+appkey);
		sb.append("&random="+random);
		sb.append("&time="+time);
		sb.append("&mobile="+mobile);
//        System.out.println("sign:->"+sb.toString());
        return EncryptUtil.SHA256(sb.toString());
	}
	
	
	/**
	 * @Description 群发发短信的签名
	 * @date 2017年6月22日
	 * @since 1.0
	 * @version 1.0
	 */
	public static String massSmsSign(String appkey, List<Tel> mobileList, long time, long random)throws Exception{
		StringBuffer mobile = new StringBuffer() ;
		for(Tel item : mobileList){
			mobile.append(item.getMobile()+",");
		}
		if(mobile.toString().endsWith(",")){
			mobile.deleteCharAt(mobile.length()-1);
		}
		StringBuffer sb = new StringBuffer("appkey="+appkey);
		sb.append("&random="+random);
		sb.append("&time="+time);
		sb.append("&mobile="+mobile);
		return EncryptUtil.SHA256(sb.toString());
	}
	
}
