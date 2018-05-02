package com.hqg.api.sms;

import java.util.List;

/**
 * 
 * @author E.T
 * @Description
 * @date 2017年6月22日
 * @since 1.0
 * @version 1.0
 */
public interface ISMS {
	/**
	 * @Description 发送单条信息
	 * @return
	 * @date 2017年6月23日
	 * @since 1.0
	 * @version 1.0
	 */
	SingleSMSResult sendSingleSMS(SingleSMSParam param)throws Exception;
    /**
     * @Description 模板单条信息
     * @return
     * @date 2017年6月23日
     * @since 1.0
     * @version 1.0
     */
    SingleSMSResult sendTemplateSMS(TempletSMSParam param, List<String> params)throws Exception;
	/**
	 * 群发普通短信
	 * @return
	 */
	MassSMSResult sendSingleMassSMS(SingleMassSMS param)throws Exception;

    /**
     * @Description 模板群发信息
     * @return
     * @date 2017年6月23日
     * @since 1.0
     * @version 1.0
     */
    MassSMSResult sendTemplateMassSMS(TempletMassSMS param, List<String> params)throws Exception;
	
}

