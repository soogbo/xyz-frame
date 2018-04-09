package xyz.frame.mrshi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.mrshi.mapper.CustomerMapper;
import xyz.frame.mrshi.pojo.po.Customer;
import xyz.frame.mrshi.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> listAll() {
        return customerMapper.selectAll();
    }
    
    @Override
    public Customer saveOrUpdate(Customer customer) {
        if (null != customer.getId()) {
            customerMapper.updateByPrimaryKeySelective(customer);
        } else {
            customer.setCreateAt(new Date());
            customerMapper.insert(customer);
        }
        return customer;
    }
    
    @Override
    public Customer selectById(Long id) {
        return customerMapper.selectByPrimaryKey(id);
    }
    @Override
    public int deleteById(Long id) {
        return customerMapper.deleteByPrimaryKey(id);
    }
    
}
