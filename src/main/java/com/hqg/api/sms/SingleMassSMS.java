package com.hqg.api.sms;

import java.io.Serializable;
import java.util.List;
/**
 * 单个群发
 * @Description
 * @author E.T
 * @date 2017-08-31 17:22
 * @updateby
 * @updatedate
 * @version 1.0
 * @since 1.0
 */
public class SingleMassSMS implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Tel> tel;
	private Integer type=0;//0:普通短信;1:营销短信
	private String sig;//app凭证
	private Long time;//时间戳10位
	private String extend;//通道扩展码
	private String ext;//用户的session内容，原样返回，可选字段，不需要就填空。
    private String msg;//消息内容
	public List<Tel> getTel() {
		return tel;
	}
	public void setTel(List<Tel> tel) {
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

