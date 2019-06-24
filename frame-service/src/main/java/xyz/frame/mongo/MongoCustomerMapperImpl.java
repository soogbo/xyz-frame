package xyz.frame.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import xyz.frame.exception.ServiceException;
import xyz.frame.pojo.common.ResultEnum;

@Repository
public class MongoCustomerMapperImpl implements MongoCustomerMapper {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveOrUpdateMongoCustomer(MongoCustomer customer) {
        if (null == customer) {
            throw new ServiceException(ResultEnum.SERVICE_ERROR);
        } else if (null == customer.getId()) {
            mongoTemplate.save(customer);
        } else {
            Query query = new Query().addCriteria(Criteria.where("id").is(customer.getId()));
            Update update = new Update().set("groupId", customer.getGroupId()).set("name", customer.getName()).set("phone", customer.getPhone());
            mongoTemplate.updateFirst(query, update, MongoCustomer.class);
        }
    }

    @Override
    public List<MongoCustomer> findByGroupId(Long groupId) {
        Query query = new Query().addCriteria(Criteria.where("groupId").is(groupId));
        return mongoTemplate.find(query, MongoCustomer.class);
    }

    @Override
    public MongoCustomer findById(String id) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, MongoCustomer.class);
    }

    @Override
    public List<MongoCustomer> findAll() {
        return mongoTemplate.findAll(MongoCustomer.class);
    }

}
