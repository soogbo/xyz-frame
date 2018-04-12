package xyz.frame.mongo;

import java.util.List;

public interface MongoCustomerMapper {
    
    void saveOrUpdateMongoCustomer(MongoCustomer customer);

    List<MongoCustomer> findByGroupId(Long groupId);

    MongoCustomer findById(String id);
    
    List<MongoCustomer> findAll();

}
