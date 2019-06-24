package xyz.frame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.frame.exception.ServiceException;
import xyz.frame.pojo.common.ResultEnum;

/**
 * @author shi 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class) // 表示可处理Exception以内异常
    @ResponseBody
    public ResponseResult<?> handle(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            return RestResultUtil.error(serviceException.getErrorCode(), serviceException.getErrorMsg());
        } else {
            logger.error(ResultEnum.UNKONW_ERROR.getMsg() + "：{}", e);
            return RestResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }
}
