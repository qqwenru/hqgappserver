package com.hqg.api.sms;

import java.io.Serializable;
import java.util.List;

/**
 * 模板群发
 * @Description
 * @author E.T
 * @date 2017-08-31 17:21
 * @updateby
 * @updatedate
 * @version 1.0
 * @since 1.0
 */
public class TempletMassSMS implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Tel> tel;
	private String sig;//app凭证
    private String sign;//短信签名
	private Long time;//时间戳10位
	private String extend;//通道扩展码
	private String ext;//用户的session内容，原样返回，可选字段，不需要就填空。
    private List<String> params;//参数
    private String tpl_id;//模板id
	public List<Tel> getTel() {
		return tel;
	}
	public void setTel(List<Tel> tel) {
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getTpl_id() {
        return tpl_id;
    }

    public void setTpl_id(String tpl_id) {
        this.tpl_id = tpl_id;
    }
}
