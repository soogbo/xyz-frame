package xyz.frame.mrshi.service;

import java.util.List;

import xyz.frame.mrshi.pojo.po.Supplier;

/**
 * 供应商service
 * 
 * @author shisp
 * @date 2018-3-19 18:46:07
 */
public interface SupplierService {

    List<Supplier> listAll();
    
    Supplier saveOrUpdate(Supplier supplier);
    
    Supplier selectById(Long id);
    
    int deleteById(Long id);
    
}
