package xyz.frame.elasticsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.controller.BatchController;
import xyz.frame.utils.GeneralResponse;

@RestController
@RequestMapping("/elasticSearch")
public class ElasticSearchController {
    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    // 增加
    @RequestMapping("/add")
    public GeneralResponse<Employee> add() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("xuxu");
        employee.setLastName("zh");
        employee.setAge(26);
        employee.setAbout("i am in peking");
        employeeRepository.save(employee);
        logger.info("add a obj");
        return GeneralResponse.success(employee);
    }

    // 删除
    @RequestMapping("/delete")
    public GeneralResponse<String> delete() {
        employeeRepository.delete(null);
        logger.info("del a obj");
        return GeneralResponse.success();
    }

    // 局部更新
    @RequestMapping("/update")
    public GeneralResponse<Employee> update() {
        Employee employee = employeeRepository.queryEmployeeById("1");
        employee.setFirstName("哈哈");
        employeeRepository.save(employee);
        logger.info("update a obj");
        return GeneralResponse.success(employee);
    }

    // 查询
    @RequestMapping("/query")
    public GeneralResponse<Employee> query() {
        Employee employee = employeeRepository.queryEmployeeById("1");
        logger.info("query a obj");
        return GeneralResponse.success(employee);
    }

}