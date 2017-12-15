package xyz.frame.json;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 平台json服务jackson版本
 * @author marshal.liu
 */
public class JacksonFormatDate {

	public static ObjectMapper objectMapper;
	static{
		objectMapper = getObjectMapper();
	}
	
	/**
	 * 生成jackson
	 * @return
	 */
	private static ObjectMapper getObjectMapper(){				
		ObjectMapper mapper = new ObjectMapper();
        //序列化日期时以timestamps输出
//        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);        
//        mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);    
        SimpleModule newModule = new SimpleModule("dateDeserializer", PackageVersion.VERSION);
		newModule.addDeserializer(Date.class, new JacksonDateDeserializer());
		newModule.addSerializer(Date.class, new JacksonDateSerializer());
		mapper.registerModule(newModule);
        //定义当属性不存在则忽略
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        /*//自定义jackson序列化排除策略
        JacksonAnnotationIntrospector ai = new JacksonAnnotationIntrospector(){
        	private static final long serialVersionUID = 5323816788110639377L;
        	@Override
        	protected boolean _isIgnorable(Annotated a) {
        		boolean flag = super._isIgnorable(a);
        		if(flag==false){
        			Not2Front ann = _findAnnotation(a, Not2Front.class);
        	        if (ann != null) {
        	        	flag = true;
        	        }
        		}
        		return flag;
        	}
        };        
        mapper.setAnnotationIntrospector(ai);*/
        return mapper;
	}
	
//	@Override
	public String toJson(Object src) {		
		try {
			return objectMapper.writeValueAsString(src);
		} catch (JsonProcessingException e) {
			throw new FrameworkJsonException("json序列化失败", e);
		}
	}

//	@Override
	public <T> T fromJson(String json, Class<T> classOfT) {
		try {		
			return objectMapper.readValue(json, classOfT);
		} catch (IOException e) {
			throw new FrameworkJsonException("json反序列化失败", e);
		}
	}

//	@Override
	public <T> T fromJson(String json, Type typeOfT) {
		try {
			TypeReference<T> typeReference = new TypeReference<T>() {
				public Type getType() { return typeOfT; }
			};
			return objectMapper.readValue(json, typeReference);
		} catch (IOException e) {
			throw new FrameworkJsonException("json反序列化失败", e);
		}
	}
}
