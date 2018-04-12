/**
 * 
 */
package xyz.frame.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import xyz.frame.configure.redis.RedisService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * @Description 测试redis
 * @author shisp
 * @date 2018-4-12 17:40:34
 */
@RestController
@RequestMapping("/redis")
@Api(value = "测试redis")
public class RedisController {
    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "redis set")
    @GetMapping(value = "/set")
    public ResponseResult<?> set(@RequestParam(value = "key") String key,@RequestParam(value = "value") String value) {
        logger.info("redis set:key={},value={}",key,value);
        boolean set = redisService.set(key, value);
        return RestResultUtil.success(set);
    }
    @ApiOperation(value = "redis get")
    @GetMapping(value = "/get")
    public ResponseResult<?> get(@RequestParam(value = "key") String key) {
        logger.info("redis get:key={}",key);
        String value = redisService.get(key);
        return RestResultUtil.success(value);
    }
    
}
