/**
 * 
 */
package xyz.frame.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import xyz.frame.mongo.MongoCustomerSmsGroup;
import xyz.frame.mongo.MongoCustomerSmsList;
import xyz.frame.mongo.MongoCustomerSmsListMapper;
import xyz.frame.mongo.MongoCustomerSmsListRepository;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * @Description 测试mongo连接
 * @author shisp
 * @date 2017年12月14日  下午1:14:37
 */

@RestController
@RequestMapping("/mongo")
@Api(value = "测试mongo任务执行")
public class MongoController {
    private static final Logger logger = LoggerFactory.getLogger(MongoController.class);
    @Autowired
    @Qualifier("mongoCustomerSmsListRepository")
    private MongoCustomerSmsListRepository mongoRepository;
    @Autowired
    @Qualifier("mongoCustomerSmsListMapper")
    private MongoCustomerSmsListMapper mongoMapper;

    @ApiOperation(value = "查询全部")
    @GetMapping(value = "/findAll")
    public ResponseResult<?> findAll() {
        logger.info("查询mongo全部！！！");
        List<MongoCustomerSmsList> findAll = mongoRepository.findAll();
        return RestResultUtil.success(findAll);
    }
    @ApiOperation(value = "方法一：通过继承Repository接口，一般查询已被sprignboot自实现！！！")
    @GetMapping(value = "/findByCaseId")
    public ResponseResult<?> findByCaseId() {
        logger.info("查询mongo by caseId by Repository interface！！！");
        List<MongoCustomerSmsGroup> list = mongoRepository.findMongoCustomerSmsListByCaseId(4386892L);
        return RestResultUtil.success(list);
    }
    
    @ApiOperation(value = "方法二：自定义接口，可自定义查询更新等条件！！！")
    @GetMapping(value = "/findByCaseIdForMapper")
    public ResponseResult<?> findByCaseIdForMapper() {
        logger.info("查询mongo by caseId by user-defined Mapper interface！！！");
        List<MongoCustomerSmsGroup> list = mongoMapper.findMongoCustomerSmsListByCaseId(4386892L);
        return RestResultUtil.success(list);
    }
    
}
