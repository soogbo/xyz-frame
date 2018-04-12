package xyz.frame.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoCustomerRepository extends MongoRepository<MongoCustomer, String>{
    
    void saveOrUpdateMongoCustomer(MongoCustomer customer);

    List<MongoCustomer> findByGroupId(Long groupId);

    MongoCustomer findById(String id);

}
