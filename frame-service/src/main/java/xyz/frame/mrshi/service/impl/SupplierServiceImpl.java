package xyz.frame.mrshi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.frame.mrshi.mapper.SupplierMapper;
import xyz.frame.mrshi.pojo.po.Supplier;
import xyz.frame.mrshi.service.SupplierService;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<Supplier> listAll() {
        return supplierMapper.selectAll();
    }
    
    @Override
    public Supplier saveOrUpdate(Supplier supplier) {
        if (null != supplier.getId()) {
            supplierMapper.updateByPrimaryKeySelective(supplier);
        } else {
            supplier.setCreateAt(new Date());
            supplierMapper.insert(supplier);
        }
        return supplier;
    }
    
    @Override
    public Supplier selectById(Long id) {
        return supplierMapper.selectByPrimaryKey(id);
    }
    @Override
    public int deleteById(Long id) {
        return supplierMapper.deleteByPrimaryKey(id);
    }
    
}
