package xyz.frame.utils;

//import com.taobao.api.ApiException;
//import com.taobao.api.DefaultTaobaoClient;
//import com.taobao.api.TaobaoClient;
//import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
//import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import xyz.frame.pojo.common.AliSmsSentEnum;
import xyz.frame.pojo.vo.AliSmsSentParamVo;

/**
 * 阿里大鱼短信工具类
 * @author shi
 */
public class AliSmsSentUtils {

	public static String smsSent(AliSmsSentParamVo vo, AliSmsSentEnum sentEnum){
	    String response = null;
		/*TaobaoClient client = new DefaultTaobaoClient(sentEnum.getUrl(), sentEnum.getAppKey(), sentEnum.getAppSecret());
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(vo.getExtend());
		req.setSmsType(vo.getSmsType());
		req.setSmsFreeSignName(vo.getSmsFreeSignName());
		req.setSmsParamString(vo.getSmsParamString());
		req.setRecNum(vo.getRecNum());
		req.setSmsTemplateCode(vo.getSmsTemplateCode());
		
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			response = rsp.getBody();
		} catch (ApiException e) {
			e.printStackTrace();
		}*/
		return response;
	}
	
	public static String smsQuery(){
		return null;
	}
	
}
