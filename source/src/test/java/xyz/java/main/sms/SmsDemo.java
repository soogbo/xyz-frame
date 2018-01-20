package xyz.java.main.sms;

import xyz.frame.pojo.common.AliSmsFreeSignNameEnum;
import xyz.frame.pojo.common.AliSmsSentEnum;
import xyz.frame.pojo.vo.AliSmsSentParamVo;
import xyz.frame.utils.AliSmsSentUtils;

public class SmsDemo {

    
    public static void main(String[] args) {

    	AliSmsSentParamVo vo = new AliSmsSentParamVo();
    	vo.setExtend("ceshi123");
//    	vo.setRecNum("18521000092");
    	vo.setRecNum("18521000092,15290071816,15839498697");
    	vo.setSmsFreeSignName(AliSmsFreeSignNameEnum.石保华种子站.getName());
    	vo.setSmsTemplateCode(AliSmsFreeSignNameEnum.SMS_122055063.getName());
    	
//    	vo.setSmsParamString("{\"name\":\"1234\",\"time\":\"alidayu\"}");
    	vo.setSmsParamString("{\"seed\":\"迪卡653玉米种\",\"num\":\"1900\",\"phone\":\"15290071816\",\"adds\":\"陈寨中学路口西20米路北\"}");
    	vo.setSmsType("smstype");
    	
    	
    	String smsSent = AliSmsSentUtils.smsSent(vo, AliSmsSentEnum.smsSent);
    	System.out.println(smsSent);
    }
}
