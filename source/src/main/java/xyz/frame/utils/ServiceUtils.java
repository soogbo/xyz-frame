package xyz.frame.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MethodInvocationException;

import xyz.frame.exception.ServiceException;
import xyz.frame.pojo.common.Constants;
import xyz.frame.pojo.common.RequestMethodEnum;
import xyz.frame.pojo.common.ResultEnum;

/**
 * 调用service辅助类
 */
public class ServiceUtils {

    public static final Logger logger = LoggerFactory.getLogger(ServiceUtils.class);

    /**
     * 获取service访问需要的方法，判断sessionAttitude
     *
     * @param target
     * @param methodName
     * @param paramsNum
     * @return
     */
    public static Method getInvokeMethod(Object target, String methodName) {

        String key = String.format("class:%s_method:%s_param:%s,%s", ServiceUtils.class.getName(), "getInvokeMethod", target.getClass().getName(), methodName);
        Method result = (Method) StaticCache.getInstance().get(key);
        if (result == null) {
            // 根据方法名字反射到接口中的方法
            List<Method> methodList = ClassHelper.getAllInterfaceMethods(target);

            // 拿到调用的方法
            for (Method method : methodList) {
                if (!methodName.equals(method.getName())) {
                    continue;
                }
                result = method;
                StaticCache.getInstance().put(key, result);
                break;
            }
        }

        return result;
    }

    /**
     * 获取service访问需要的方法，不判断 sessionAttitude
     *
     * @param target
     * @param methodName
     * @param paramsNum
     * @return
     */
    public static Method getInvokeMethodWithouSessionAtti(Object target, String methodName, int paramsNum) {
        return getInvokeMethodWithouSessionAttiWithClass(target.getClass(), methodName, paramsNum);
    }

    /**
     * 获取service访问需要的方法，不判断 sessionAttitude
     *
     * @param target
     * @param methodName
     * @param paramsNum
     * @return
     */
    public static Method getInvokeMethodWithouSessionAttiWithClass(Class<?> clazz, String methodName, int paramsNum) {
        String key = String.format("class:%s_method:%s_param:%s,%s,%s", ServiceUtils.class.getName(), "getInvokeMethodWithouSessionAtti", clazz.getName(),
                methodName, paramsNum);
        Method result = (Method) StaticCache.getInstance().get(key);

        if (result == null) {
            // 根据方法名字反射到接口中的方法
            List<Method> methodList = ClassHelper.getAllInterfaceMethods(clazz);

            // 拿到调用的方法
            for (Method method : methodList) {
                if (!methodName.equals(method.getName())) {
                    continue;
                }

                if (paramsNum != method.getParameterCount()) {
                    continue;
                }

                result = method;
                StaticCache.getInstance().put(key, result);
                break;
            }
        }

        return result;
    }

    /**
     * 判断当前对象的类上，是否有@Gateway注解
     *
     * @param object
     * @return
     * 
     *         public static boolean hasGatewayAnnotation(Class<?> clazz) {
     * 
     *         String key = String.format("class:%s_method:%s_param:%s",
     *         ServiceUtils.class.getName(), "hasGatewayAnnotation",
     *         clazz.getName()); Boolean result = (Boolean)
     *         StaticCache.getInstance().get(key); if (result == null) { result =
     *         false; // 找到所有接口的class Class<?>[] allInterfaceClass = null; if
     *         (clazz.isInterface()) { allInterfaceClass = new Class<?>[] { clazz };
     *         } else { allInterfaceClass = clazz.getInterfaces(); }
     * 
     *         // 遍历所有接口看是否有注解@Gateway for (Class<?> c : allInterfaceClass) {
     *         Gateway[] gwArr = c.getAnnotationsByType(Gateway.class); if (gwArr !=
     *         null && gwArr.length > 0) { result = true;
     *         StaticCache.getInstance().put(key, result); break; } FileGateway[]
     *         fgwArr = c.getDeclaredAnnotationsByType(FileGateway.class); if
     *         (fgwArr != null && fgwArr.length > 0) { result = true;
     *         StaticCache.getInstance().put(key, result); break; } } } return
     *         result;
     * 
     *         }
     */

    /**
     * 判断当前对象的类上，是否有@Gateway注解
     *
     * @param object
     * @return public static boolean hasGatewayAnnotation(Object obj) { return
     *         hasGatewayAnnotation(obj.getClass()); }
     */

    /**
     * 判断当前对象的类上，是否有@AllowAnonymous注解
     * 
     * @param object
     * @return
     * 
     *         public static boolean hasAllowAnonymousAnnotation(Object object,
     *         Method method) {
     * 
     *         String key = String.format("class:%s_method:%s_param:%s,%s",
     *         ServiceUtils.class.getName(), "hasAllowAnonymousAnnotation",
     *         object.getClass().getName(), method.getName()); Boolean result =
     *         (Boolean) StaticCache.getInstance().get(key); if (result == null) {
     *         result = false;
     * 
     *         if(method.getAnnotation(AllowAnonymous.class) != null) {
     *         StaticCache.getInstance().put(key, true); return true; } //
     *         找到所有接口的class Class<?>[] allInterfaceClass =
     *         object.getClass().getInterfaces(); // 遍历所有接口看是否有注解@AllowAnonymous for
     *         (Class<?> c : allInterfaceClass) { AllowAnonymous[] anArr =
     *         c.getDeclaredAnnotationsByType(AllowAnonymous.class); if (anArr !=
     *         null && anArr.length > 0) { result = true;
     *         StaticCache.getInstance().put(key, result); break; } } } return
     *         result;
     * 
     *         }
     */

    /**
     * 查看各种类型是否有执行权限
     *
     * @param
     * @param
     * @param type
     * @return
     * @throws Exception
     * 
     *             @SuppressWarnings("unchecked") public static boolean
     *             hasPermission(String serviceName,String funcName,Object object,
     *             Method method,Logger logger,FrameworkSession frameworkSession) {
     *             // 检查请求的方法是否为标识为不可执行 @Forbid checkForbiddenMethod(object,
     *             funcName, logger); // 是否允许匿名访问 if
     *             (hasAllowAnonymousAnnotation(object, method)) { return true; }
     * 
     *             // 检验用户是否已经登陆 if (frameworkSession == null ||
     *             !frameworkSession.isAuthenticated()) { throw new
     *             NoLogonException(); } // 从threadLocal中拿到sessionId String
     *             sessionId = frameworkSession.getSessionId(); Subject subject =
     *             new Subject.Builder().sessionId(sessionId).buildSubject();
     * 
     *             // 检验用户是否已经登陆 if (!subject.isAuthenticated()) {
     *             logger.info("没有认证"); return false; } // 判断权限在不在
     *             session中，如果不在的话从realm取了放到session中 Session session =
     *             subject.getSession(); Collection<String> permissions = null;
     *             permissions = (Collection<String>)
     *             session.getAttribute(Constants.SESSION_PERMISSION); if
     *             (permissions == null) { return false; } // 拿到指定类型的权限 String[]
     *             permissionArr = null; for (String permissionArrStr : permissions)
     *             { permissionArr = permissionArrStr.split(Constants.COMMA);
     *             if(permissionArr == null||permissionArr.length==0){ continue; }
     *             for(String permission:permissionArr){ String[] nameArray =
     *             permission.split(Constants.PUNCTUATION); if
     *             (serviceName.contains(nameArray[0]) ||
     *             Constants.STAR.equals(nameArray[0])) { //
     *             支持某一个repository中所有方法dao:Myrepository.* if
     *             (nameArray[1].contains(Constants.STAR)) { return true; } if
     *             (funcName.equals(nameArray[1])) { return true; } } } }
     *             logger.info("没有访问方法的权限 : {}.{}",serviceName ,funcName); return
     *             false; }
     */
    /**
     * 检查请求的方法是否标识为不可执行 @Forbid
     *
     * @param
     * @throws PermissionException
     * 
     *             private static void checkForbiddenMethod(Object object, String
     *             methodName, Logger logger) throws PermissionException {
     * 
     *             if (hasForbiddenMethod(object, methodName)) {
     *             logger.info("forbidden method annotation declared : " +
     *             AopUtils.getTargetClass(object).getSimpleName() + " " +
     *             methodName); throw new PermissionException("forbidden method
     *             annotation declared"); } }
     * 
     *             private static boolean hasForbiddenMethod(Object object, String
     *             methodName) { String key =
     *             String.format("class:%s_method:%s_param:%s,%s",
     *             ServiceUtils.class.getName(), "hasForbiddenMethod",
     *             object.getClass().getName(), methodName); Boolean result =
     *             (Boolean) StaticCache.getInstance().get(key); if (result == null)
     *             { result = false; Class<?>[] interfacesClasses =
     *             object.getClass().getInterfaces(); // 检查不可被执行方法的注解 //
     *             遍历所有接口看是否有注解@Forbid for (Class<?> c : interfacesClasses) {
     *             Annotation annotation = c.getDeclaredAnnotation(Forbid.class); if
     *             (annotation == null) { continue; } Forbid forbid = (Forbid)
     *             annotation; String[] forbiddenMethods =
     *             forbid.forbiddenMethods(); for (String name : forbiddenMethods) {
     *             if (name.equals(methodName)) { result = true; break; } } if
     *             (result) break; } StaticCache.getInstance().put(key, result); }
     *             return result;
     * 
     *             }
     */
    /**
     * 一个方法含有@sessionAttribute的参数个数
     *
     * @param method
     * @return
     * */
    /*public static int getSessionAttributeAnnotationCount(Method method) {
        String key = String.format("class:%s_method:%s_param:%s,%s,%s", ServiceUtils.class.getName(), "getSessionAttributeAnnotationCount",
                method.getDeclaringClass().getName(), method.getName(), method.getParameterCount());
        Integer result = (Integer) StaticCache.getInstance().get(key);
        if (result == null) {
            result = 0;
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                if (parameter.getAnnotation(SessionAttribute.class) != null) {
                    result++;
                }
            }
            StaticCache.getInstance().put(key, result);
        }

        return result;
    }*/
     
    public static Object callService(Object service, String serviceName, String funcName, Map<String, String> params,
            xyz.frame.pojo.common.RequestMethodEnum requestMethodEnum) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        if (params == null) {
            params = new HashMap<String, String>();
        }

        // 拿到调用的方法
        Method method = getInvokeMethod(service, funcName);
        if (method == null) {
            logger.info("请求无对应服务: {}.{} ", serviceName, funcName);
            throw new ServiceException(ResultEnum.ERROR_CODE_NO_SERVICE);
        }

        /*
         * // 方法有DisenableLog注解的不打印请求参数日志 if
         * (logger.isInfoEnabled()&&method.getAnnotation(DisabledLog.class) == null) { }
         */
        // 打印请求中的参数到日志中
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        logger.info("请求的参数requestParameters: {}", sb.toString());

        String jsonStr = params.get(Constants.PARAMS);
        /*
         * // 判断此方法和类是否为外部可以访问 if (!hasGatewayAnnotation(service)) {
         * logger.info("there is no gateway annotation: {}",serviceName); throw new
         * PermissionException(); }
         * 
         * // 外部可访问的则做权限判断 if (!hasPermission(serviceName,funcName,service, method,
         * logger, frameworkSession)) { throw new PermissionException("没有访问方法的权限:" +
         * serviceName + "." + funcName); }
         * 
         * // 检查请求方法是否正确 if (!requestMethodCheck(service, serviceName, funcName,
         * requestMethodEnum)) {
         * logger.info("there is unmatch RequestMethodEnum : {}.{}",serviceName,funcName
         * ); throw new ServiceException(SystemErrorCodeEnum.ERROR_CODE_METHOD_UNMATCH);
         * }
         */
        // 反序列化，根据方法的参数类型，将json转换成java对象
//        int sessionAttributeAnnotationCount = ServiceUtils.getSessionAttributeAnnotationCount(method);
        Object[] objects = ClassHelper.json2ObjectArray(jsonStr, method, false, service);

        // 反射调用方法
        // 切面处理 session注入，sessionattitude赋值，和入参校验
        Object data = method.invoke(service, objects);

        // 方法有DisenableLog注解的不打印请求参数日志
        // file返回值不打印日志
        // inputstream返回值不打印日志
        /*
         * if (logger.isInfoEnabled()&&method.getAnnotation(DisabledLog.class) == null
         * && !(data instanceof File) && !(data instanceof InputStream)) { }
         */ StringBuilder sb2 = new StringBuilder();
        sb.append(data == null ? "" : data.toString());
        logger.info("从controller返回的结果 : {}", (sb2.substring(0, sb2.length() > 500 ? 500 : sb2.length())));
        return data;
    }

    public static Object callRestfulService(Object service, String serviceName, String funcName, Map<String, String> params,
            RequestMethodEnum requestMethodEnum) throws MethodInvocationException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {

        if (params == null) {
            params = new HashMap<>();
        }
        // 拿到调用的方法
        Method method = getInvokeMethod(service, funcName);
        if (method == null) {
            logger.info("请求无对应服务: {}.{}", serviceName, funcName);
            throw new ServiceException(ResultEnum.ERROR_CODE_NO_SERVICE);
        }

        // 方法有DisenableLog注解的不打印请求参数日志
        /*
         * if (logger.isInfoEnabled()&&method.getAnnotation(DisabledLog.class) == null)
         * { // 打印请求中的参数到日志中 }
         */
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        logger.info("请求的参数requestParameters: {}", sb.toString());

        /*
         * // 判断此方法和类是否为外部可以访问 if (!hasGatewayAnnotation(service)) {
         * logger.info("there is no gateway annotation: {}",serviceName); throw new
         * PermissionException("没有访问方法的权限:" + serviceName + "." + funcName); }
         * 
         * // 外部可访问的则做权限判断 if (!hasPermission(serviceName,funcName,service, method,
         * logger, frameworkSession)) { throw new PermissionException("没有访问方法的权限:" +
         * serviceName + "." + funcName); }
         * 
         * // 检查请求方法是否正确 if (!requestMethodCheck(service, serviceName, funcName,
         * requestMethodEnum)) {
         * logger.info("there is unmatch RequestMethodEnum : {}.{}",serviceName,funcName
         * ); throw new SystemException(SystemErrorCodeEnum.ERROR_CODE_METHOD_UNMATCH);
         * }
         */

        // 反序列化，根据方法的参数类型，将json转换成java对象
        Object[] objects = ClassHelper.parameterMap2ObjectArray(params, method, service);
        // 反射调用方法
        // 切面处理 session注入，sessionattitude赋值，和入参校验
        Object data = method.invoke(service, objects);

        /*
         * // 方法有DisenableLog注解的不打印请求参数日志 if
         * (logger.isInfoEnabled()&&method.getAnnotation(DisabledLog.class) == null&&
         * !(data instanceof File) && !(data instanceof InputStream)) { }
         */
        StringBuilder sb2 = new StringBuilder();
        sb2.append(data == null ? "" : data.toString());
        logger.info("从controller返回的结果 : {}", (sb2.substring(0, sb2.length() > 500 ? 500 : sb2.length())));
        return data;
    }

    /**
     * 获取服务名
     * 
     * @param serviceName
     * @return
     */
    public static String getBeanClassName(Object service) {
        String className = "";
        Class<?> clsArr[] = service.getClass().getInterfaces();
        if (clsArr != null) {
            for (Class<?> cls : clsArr) {
                if (!cls.getName().startsWith("com.alibaba") && !cls.getName().startsWith("org.springframework")) {
                    className = cls.getName();
                    break;
                }
            }
        }
        return className;
    }

    /**
     * 请求方法检查
     * 
     * @param service
     * @param funcName
     * @param requestMethodEnum
     * @return private static boolean requestMethodCheck(Object service,String
     *         serviceName, String funcName, RequestMethodEnum requestMethodEnum) {
     *         boolean flag = false; Method method = getInvokeMethod(service,
     *         funcName); RequestMethod rm =
     *         method.getAnnotation(RequestMethod.class); if (rm != null &&
     *         rm.value() != null) { for (RequestMethodEnum str : rm.value()) { if
     *         (str.equals(requestMethodEnum)) { flag = true; break; } } }else{
     *         throw new PermissionException("没有访问方法的权限:" + serviceName + "." +
     *         funcName); } return flag; }
     */
}
