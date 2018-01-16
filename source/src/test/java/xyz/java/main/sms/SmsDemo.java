package xyz.java.main.sms;

import xyz.frame.pojo.common.AliSmsFreeSignNameEnum;
import xyz.frame.pojo.common.AliSmsSentEnum;
import xyz.frame.pojo.vo.AliSmsSentParamVo;
import xyz.frame.utils.AliSmsSentUtils;

public class SmsDemo {

    
    public static void main(String[] args) {

    	AliSmsSentParamVo vo = new AliSmsSentParamVo();
    	vo.setExtend("ceshi123");
    	vo.setRecNum("18521000092");
    	vo.setSmsFreeSignName(AliSmsFreeSignNameEnum.soogbo测试.getName());
    	vo.setSmsParamString("{\"name\":\"1234\",\"time\":\"alidayu\"}");
    	vo.setSmsTemplateCode(AliSmsFreeSignNameEnum.SMS_64190036.getName());
    	vo.setSmsType("smstype");
    	
    	
    	String smsSent = AliSmsSentUtils.smsSent(vo, AliSmsSentEnum.smsSent);
    	System.out.println(smsSent);
    }
}
