package xyz.frame.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import xyz.frame.json.FrameJsonUtils;
import xyz.frame.mrshi.pojo.po.Customer;
import xyz.frame.mrshi.service.CustomerService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

@RestController
@Api(value = "客户")
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    
    
    @ApiOperation(value = "新增修改")
    @PostMapping(value = "/saveOrUpdate")
    public ResponseResult<?> saveOrUpdate(Customer customer,HttpServletRequest request, HttpServletResponse response) {
        logger.info("saveOrUpdate customer:{}",FrameJsonUtils.toJson(customer));
        customer = customerService.saveOrUpdate(customer);
        return RestResultUtil.success(customer);
    }
    @PostMapping(value = "/deleteById")
    public ResponseResult<?> deleteById(@RequestParam(value = "customerId") Long customerId) {
        logger.info("deleteById customerId:{}",customerId);
        int deleteById = customerService.deleteById(customerId);
        return RestResultUtil.success(deleteById);
    }
    
    @GetMapping(value = "/getById/{customerId}")
    public ResponseResult<?> getOneTask(@PathVariable(value = "customerId") Long customerId) {
        Customer customer = customerService.selectById(customerId);
        return RestResultUtil.success(customer);
    }
    @GetMapping(value = "/getAll")
    public ResponseResult<?> getAll() {
        List<Customer> listAll = customerService.listAll();
        return RestResultUtil.success(listAll);
    }
}