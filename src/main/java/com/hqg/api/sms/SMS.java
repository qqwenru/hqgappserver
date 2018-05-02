package com.hqg.api.sms;

import com.hqg.api.util.HttpUtil;
import com.hqg.api.util.SignUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.List;


public class SMS implements ISMS{
	private String appId;
	private String appKey;
	private String singleUrl;//单个url
	private String massUrl;//群发url

    public SMS(){}
	public SMS(String appId, String appKey, String singleUrl, String massUrl){
		this.appId = appId;
		this.appKey = appKey;
		this.singleUrl = singleUrl;
		this.massUrl = massUrl;
	}
	/**
	 * 单个
	 */
	@Override
	public SingleSMSResult sendSingleSMS(SingleSMSParam param)throws Exception {
        param.setTime(System.currentTimeMillis()/1000);
		long random = (long)(10000000000l*(Math.random()));
		String sig = SignUtil.singleSmsSign(appKey, random, param.getTime(), param.getTel().getMobile());
		param.setSig(sig);
		String paramJson = JSONObject.toJSONString(param);
		String url = singleUrl+"?sdkappid="+appId+"&random="+random;
		String result = HttpUtil.post(url, paramJson);
		return JSONObject.parseObject(result,SingleSMSResult.class);
	}

    /**
     * 发送模板单条信息
     * @param param
     * @param params
     * @return
     * @date 2017年6月23日
     * @version 1.0
     * @since 1.0
     */
    @Override
    public SingleSMSResult sendTemplateSMS(TempletSMSParam param, List<String> params) throws Exception {
        param.setParams(params);
        param.setTime(System.currentTimeMillis()/1000);
        long random = (long)(10000000000l*(Math.random()));
        String sig = SignUtil.singleSmsSign(appKey, random, param.getTime(), param.getTel().getMobile());
        param.setSig(sig);
        String paramJson = JSONObject.toJSONString(param);
        String url = singleUrl+"?sdkappid="+appId+"&random="+random;
        String result = HttpUtil.post(url, paramJson);
        return JSONObject.parseObject(result,SingleSMSResult.class);
    }

    /**
	 * 群发
	 */
	@Override
	public MassSMSResult sendSingleMassSMS(SingleMassSMS param) throws Exception{
        param.setTime(System.currentTimeMillis()/1000);
		long random = (long)(10000000000l*(Math.random()));
		String sig = SignUtil.massSmsSign(appKey,param.getTel(),param.getTime(),random);
		param.setSig(sig);
		String paramJson = JSONObject.toJSONString(param);
		String url = massUrl+"?sdkappid="+appId+"&random="+random;
		return JSONObject.parseObject(HttpUtil.post(url, paramJson),MassSMSResult.class);
	}

    /**
     * @param param
     * @param params
     * @return
     * @Description 模板群发信息
     * @date 2017年6月23日
     * @version 1.0
     * @since 1.0
     */
    @Override
    public MassSMSResult sendTemplateMassSMS(TempletMassSMS param, List<String> params) throws Exception {
        param.setParams(params);
        param.setTime(System.currentTimeMillis()/1000);
        long random = (long)(10000000000l*(Math.random()));
        String sig = SignUtil.massSmsSign(appKey,param.getTel(),param.getTime(),random);
        param.setSig(sig);
        String paramJson = JSONObject.toJSONString(param);
        String url = massUrl+"?sdkappid="+appId+"&random="+random;
        String result = HttpUtil.post(url, paramJson);
        return JSONObject.parseObject(result,MassSMSResult.class);
    }
}
