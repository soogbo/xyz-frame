package xyz.frame.mrshi.service;

import java.util.List;

import xyz.frame.mrshi.pojo.po.Customer;

/**
 * 客户service
 * 
 * @author shisp
 * @date 2018-3-19 18:46:07
 */
public interface CustomerService {

    List<Customer> listAll();

    Customer saveOrUpdate(Customer customer);

    Customer selectById(Long id);

    int deleteById(Long id);
}
