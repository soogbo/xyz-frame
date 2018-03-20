package xyz.frame.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.exception.ServiceException;
import xyz.frame.pojo.common.Constants;
import xyz.frame.pojo.common.RequestMethodEnum;
import xyz.frame.pojo.common.SystemErrorCodeEnum;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.ServiceUtils;

/**
 * restful服务处理器 根据服务名，方法名转发到相应的服务.
 * 
 * @author marshal.liu
 */
@RestController
public class RestfulServiceController {
    private static final Logger logger = LoggerFactory.getLogger(RestfulServiceController.class);
    /**
     * spring context
     */
    @Autowired(required = true)
    private ApplicationContext context;

    @RequestMapping(value = "/restfulservice/{serviceName}/{funcName}", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
            RequestMethod.PUT }, produces = { Constants.FORMAT_JSON, Constants.FORMAT_TEXT })
    public ResponseResult<Object> service(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceName,
            @PathVariable String funcName) throws Exception {
        RequestMethodEnum requestMethodEnum = RequestMethodEnum.getByName(request.getMethod());
        return doService(request, response, serviceName, funcName, requestMethodEnum);
    }

    private ResponseResult<Object> doService(HttpServletRequest request, HttpServletResponse response, String serviceName, String funcName,
            RequestMethodEnum requestMethodEnum) throws Exception {
        ResponseResult<Object> result = new ResponseResult<>();
        // 得到对应service名字的服务
        if (!context.containsBean(serviceName)) {
            logger.info("请求无对应服务: {}.{}", serviceName, funcName);
            throw new ServiceException(SystemErrorCodeEnum.ERROR_CODE_NO_SERVICE);
        }
        Object service = context.getBean(serviceName);

        // 参数
        Map<String, String> params = new HashMap<>();
        if (request.getParameterNames() != null && request.getParameterNames().hasMoreElements()) {
            Enumeration<String> enumList = request.getParameterNames();
            String key = null;
            String value = null;
            while (enumList.hasMoreElements()) {
                key = enumList.nextElement();
                value = request.getParameter(key);
                params.put(key, value);
            }
        }

        Object data = ServiceUtils.callRestfulService(service, serviceName, funcName, params, requestMethodEnum);
        result.setData(data);

		/*CurrentSessionStore store = getCurrentSessionStore();		
		FrameworkSession session = store.getCurrentSession();
		if (session != null) {
			try {
				// 会话touch
				session.touchSession();
				// 设置cookies到返回头
				setSessionCookies(request, response, session);
			} catch (InvalidSessionException e) {
				delSessionCookies(request, response);
			}		
		}		*/
		// 转换成json返回
		return result;
	}	
}
