package xyz.frame.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import xyz.frame.exception.ServiceException;
import xyz.frame.pojo.common.ResultEnum;

@Repository("mongoCustomerRepository")
public class MongoCustomerRepositoryImpl implements MongoCustomerRepository {

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
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public <S extends MongoCustomer> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<MongoCustomer> findById(String s) {
        return Optional.empty();
    }

    @Override
    public List<MongoCustomer> findAll() {
        return mongoTemplate.findAll(MongoCustomer.class);
    }

    @Override
    public Iterable<MongoCustomer> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public List<MongoCustomer> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomer> S insert(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomer> List<S> insert(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends MongoCustomer> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomer> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<MongoCustomer> findAll(Pageable arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }


    @Override
    public void delete(MongoCustomer arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends MongoCustomer> entities) {

    }


    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }


    @Override
    public <S extends MongoCustomer> S save(S arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomer> long count(Example<S> arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends MongoCustomer> boolean exists(Example<S> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends MongoCustomer> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        // TODO Auto-generated method stub
        return null;
    }


}
