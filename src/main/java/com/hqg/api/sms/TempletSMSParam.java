package com.hqg.api.sms;

import java.io.Serializable;
import java.util.List;

/**
 * 单条模板短信请求参数

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
    "sign": "腾讯云", //短信签名，如果使用默认签名，该字段可缺省
    "tpl_id": 19, //业务在控制台审核通过的模板ID
     //假定这个模板为：您的{1}是{2}，请于{3}分钟内填写。如非本人操作，请忽略本短信。
    "params": [
        "验证码",
        "1234",
        "4"
    ], //参数，分别对应上面假定模板的{1}，{2}，{3}
    "sig": "ecab4881ee80ad3d76bb1da68387428ca752eb885e52621a3129dcf4d9bc4fd4", //app凭证，具体计算方式见下注
    "time": 1457336869, //unix时间戳，请求发起时间，如果和系统时间相差超过10分钟则会返回失败
    "extend": "", //通道扩展码，可选字段，默认没有开通(需要填空)。
    //在短信回复场景中，腾讯server会原样返回，开发者可依此区分是哪种类型的回复
    "ext": "" //用户的session内容，腾讯server回包中会原样返回，可选字段，不需要就填空。
}
 */
public class TempletSMSParam implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Tel tel;
	private String sig;//app凭证
	private String sign;//短信签名
	private Long time;//时间戳10位
	private String extend;//通道扩展码
	private String ext;//用户的session内容，原样返回，可选字段，不需要就填空。
	private List<String> params;//参数
	private Integer tpl_id;//模板id
	public Tel getTel() {
		return tel;
	}
	public void setTel(Tel tel) {
		this.tel = tel;
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
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
	public Integer getTpl_id() {
		return tpl_id;
	}
	public void setTpl_id(Integer tpl_id) {
		this.tpl_id = tpl_id;
	}

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
