/**
 * 
 */
package xyz.frame.controller;

import java.util.Date;
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
import xyz.frame.mongo.MongoCustomer;
import xyz.frame.mongo.MongoCustomerMapper;
import xyz.frame.mongo.MongoCustomerRepository;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * @Description 测试mongo连接
 * @author shisp
 * @date 2017年12月14日 下午1:14:37
 */

@RestController
@RequestMapping("/mongo")
@Api(value = "测试mongo任务执行")
public class MongoController {
    private static final Logger logger = LoggerFactory.getLogger(MongoController.class);
    @Autowired
    @Qualifier("mongoCustomerRepository")
    private MongoCustomerRepository mongoRepository;
    @Autowired
    private MongoCustomerMapper mongoMapper;

    @ApiOperation(value = "查询全部")
    @GetMapping(value = "/findAll")
    public ResponseResult<?> findAll() {
        logger.info("查询mongo全部！！！");
        List<MongoCustomer> findAll = mongoRepository.findAll();
        return RestResultUtil.success(findAll);
    }

    @ApiOperation(value = "方法一：通过继承Repository接口，一般查询已被sprignboot自实现！！！")
    @GetMapping(value = "/findByGroupId")
    public ResponseResult<?> findByGroupId() {
        logger.info("查询mongo by GroupId by Repository interface！！！");
        List<MongoCustomer> list = mongoRepository.findByGroupId(123L);
        return RestResultUtil.success(list);
    }

    @ApiOperation(value = "方法二：自定义接口，可自定义查询更新等条件！！！")
    @GetMapping(value = "/findByGroupIdForMapper")
    public ResponseResult<?> findByGroupIdForMapper() {
        logger.info("查询mongo by caseId by user-defined Mapper interface！！！");
        List<MongoCustomer> list = mongoMapper.findByGroupId(123L);
        return RestResultUtil.success(list);
    }
    @ApiOperation(value = "saveOrUpdate！！！")
    @GetMapping(value = "/saveOrUpdate")
    public ResponseResult<?> saveOrUpdate() {
        logger.info("saveOrUpdate to mongo！！！");
        MongoCustomer customer = new MongoCustomer();
        customer.setGroupId(123L);
        customer.setName("zhangsan");
        customer.setPhone("12345678909");
        customer.setCreateAt(new Date());
        customer.setUpdateAt(new Date());
        mongoMapper.saveOrUpdateMongoCustomer(customer);
        return RestResultUtil.success();
    }

}
