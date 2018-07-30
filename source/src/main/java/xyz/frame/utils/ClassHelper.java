package xyz.frame.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.TypeReference;

import xyz.frame.exception.ServiceException;
import xyz.frame.json.FrameJsonUtils;
import xyz.frame.pojo.common.Constants;
import xyz.frame.pojo.common.ResultEnum;

/**
 * @author shisp
 * @date 2018-3-19 20:09:09
 */
public class ClassHelper {
    private static final Logger logger = LoggerFactory.getLogger(ClassHelper.class);
    private ClassHelper() {
        super();
    }

    /**
     * 根据方法名字反射得到接口中的方法
     * 
     * @param o
     *            对象
     * @return 所有接口的方法
     */
    public static List<Method> getAllInterfaceMethods(Object o) {
        return getAllInterfaceMethods(o.getClass());
    }

    /**
     * 根据方法名字反射得到接口中的方法
     * 
     * @param o
     *            对象
     * @return 所有接口的方法
     */
    private static List<Method> getAllInterfaceMethods(Class<?> clazz) {
        // 反射得到所有的接口
        Class<?>[] interfaces = null;
        if (clazz.isInterface()) {
            interfaces = new Class<?>[] { clazz };
        } else {
            interfaces = clazz.getInterfaces();
        }
        List<Method> methodList = new ArrayList<>();
        for (Class<?> inter : interfaces) {
            // 得到每个接口的方法
            Method[] methods = inter.getMethods();
            for (Method method : methods) {
                methodList.add(method);
            }
        }
        return methodList;
    }
    
    /**
     * 根据方法的参数类型，将json转换成java对象
     * 
     * @param array
     *            json数组
     * @param method
     *            服务的方法
     * @param parameterCount
     *            参数个数
     * @return
     * @throws JSONException
     */
    public static Object[] json2ObjectArray(String jsonStr, Method method,
             boolean hasSessionAttributeAnnotation) {
        return json2ObjectArray(jsonStr, method, hasSessionAttributeAnnotation,
                null);
    }
     
    public static Object[] json2ObjectArray(String jsonStr, Method method,
            boolean hasSessionAttributeAnnotation, Object service){     
        List<Object> array = FrameJsonUtils.fromJson(jsonStr, new TypeReference<List<Object>>(){});
        int parameterCount = array.size();
        Parameter[] parameters = method.getParameters();
        /*if (Helper.isParameterizedType(service)) {
            Method targetMethod = Helper.getTargetMethod(service, method);
            parameters = targetMethod.getParameters();
        }*/
        Object[] objects = null;
        if (parameterCount > 0) {
            int number = parameterCount;

            objects = new Object[number];
            try{
                // json转换成java对象并放入数组
                for (int i = 0; i < parameterCount; i++) {              
                    if (array.get(i) == null || Constants.NULL_UP.equals(array.get(i))) {
                        objects[i] = null;
                        continue;
                    } else if (int.class.equals(parameters[i].getType()) || Integer.class.equals(parameters[i].getType())) {                        
                        objects[i] = ((Number)array.get(i)).intValue();
                        continue;
                    } else if (long.class.equals(parameters[i].getType()) || Long.class.equals(parameters[i].getType())) {
                        objects[i] = ((Number)array.get(i)).longValue();
                        continue;
                    } else if (double.class.equals(parameters[i].getType())
                            || Double.class.equals(parameters[i].getType())) {
                        objects[i] = ((Number)array.get(i)).doubleValue();
                        continue;
                    } else if (String.class.equals(parameters[i].getType())) {
                        objects[i] = array.get(i).toString();
                        continue;
                    } /*else {
                        if ((parameters[i].getParameterizedType() instanceof Class)
                                && ((Class<?>) parameters[i].getParameterizedType())
                                        .isAssignableFrom(FrameworkPageable.class)) {
                            objects[i] = FrameworkJson.fromJson(FrameworkJson.toJson(array.get(i)),
                                    FrameworkPageableImpl.class);
                        } else {
                            objects[i] = FrameworkJson.fromJson(FrameworkJson.toJson(array.get(i)),
                                    parameters[i].getParameterizedType());
                        }

                        continue;
                    }*/
                }
            }catch(Exception ex){
                throw new ServiceException(ResultEnum.ERROR_CODE_PARAM_JSON);
            }
        }
        return objects;
    }
    
    
    
    public static Object[] parameterMap2ObjectArray(Map<String, String> params, Method method, Object service) {

        Parameter[] parameters = method.getParameters();
        /*if (Helper.isParameterizedType(service)) {
            Method targetMethod = Helper.getTargetMethod(service, method);
            parameters = targetMethod.getParameters();
        }*/
        if (parameters == null || parameters.length <= 0) {
            return null;
        }
        Object[] objects = new Object[parameters.length];
        String key = null;
        String value = null;
        try{
            for (int i = 0; i < parameters.length; i++) {
                /*if (parameters[i].getAnnotation(SessionAttribute.class) != null) {
                    continue;
                }
                if (parameters[i].getType() == InputStream.class) {
                    continue;
                }*/
                key = parameters[i].getName();
                value = params.get(key);
                if (value == null) {
                    if (!BaseTypeUtils.isBaseType(parameters[i].getType())
                            && !String.class.equals(parameters[i].getType())) {
                        objects[i] = parameterMap2Object(parameters[i].getType(), params);
                    } else {
                        objects[i] = null;
                    }
                } else if (BaseTypeUtils.isBaseType(parameters[i].getType())) {
                    objects[i] = BaseTypeUtils.string2BaseType(value, parameters[i].getType());
                } else if (String.class.equals(parameters[i].getType())) {
                    objects[i] = value;
                }/*else if(FrameworkPageable.class.equals(parameters[i].getType())){
                    objects[i] = FrameworkJson.fromJson(value, FrameworkPageableImpl.class);
                }*/else {
                    objects[i] = FrameJsonUtils.fromJson(value, new TypeReference<Object[]>(){});
                }
            }
        }catch(Exception ex){
            logger.error("参数转换异常：{}",ex.getMessage(),ex);
            throw new ServiceException(ResultEnum.ERROR_CODE_PARAM_JSON);
        }
        return objects;
    }
    
    
    private static Object parameterMap2Object(Class<?> cls,
            Map<String, String> params) throws InstantiationException, IllegalAccessException {
        Object result = null;
        boolean flag = false;
        /*if (cls.isAssignableFrom(FrameworkPageable.class)) {
            result = new FrameworkPageableImpl();           
        }else */if(cls.isInterface()||cls.isArray()){
            return result;
        }else {         
            result = cls.newInstance();
        }

        Field[] fields = result.getClass().getDeclaredFields();
        String property = null;
        String value = null;
        Object targetValue = null;
        if (fields != null) {
            for (Field field : fields) {
                property = field.getName();
                value = params.get(property);
                if (value == null) {
                    continue;
                }
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                if (BaseTypeUtils.isBaseType(field.getType())) {
                    targetValue = BaseTypeUtils.string2BaseType(value, field.getType());
                } else if (String.class.equals(field.getType())) {
                    targetValue = value;
                } else {
                    targetValue = FrameJsonUtils.fromJson(value, field.getClass());
                }
                field.setAccessible(true);
                field.set(result, targetValue);
                flag = true;
            }
        }

        if (flag == false) {
            result = null;
        }

        return result;
    }
}
