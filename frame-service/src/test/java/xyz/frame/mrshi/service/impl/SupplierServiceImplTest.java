package xyz.frame.mrshi.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.frame.AbstractTest;
import xyz.frame.json.FrameJsonUtils;
import xyz.frame.mrshi.pojo.po.Supplier;
import xyz.frame.mrshi.service.SupplierService;

public class SupplierServiceImplTest extends AbstractTest{

    @Autowired
    private SupplierService supplierService;
    
    @Test
    public void testListAll() {
        List<Supplier> listAll = supplierService.listAll();
        System.out.println(FrameJsonUtils.toJson(listAll));
    }

    @Test
    public void testSaveOrUpdate() {
        Supplier supplier = new Supplier();
        supplier.setAddress("ceshi dizhi ...");
        Supplier saveOrUpdate = supplierService.saveOrUpdate(supplier);
        System.out.println(FrameJsonUtils.toJson(saveOrUpdate));
    }

    @Test
    public void testSelectById() {
        Supplier selectById = supplierService.selectById(1L);
        System.out.println(FrameJsonUtils.toJson(selectById));
    }

    @Test
    public void testDeleteById() {
        int deleteById = supplierService.deleteById(1L);
        System.out.println(deleteById);
    }

}
