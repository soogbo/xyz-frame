package xyz.frame.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository("mongoCustomerSmsListMapper")
public class MongoCustomerSmsListMapperImpl implements MongoCustomerSmsListMapper {

    @Autowired
    private MongoTemplate riskMongoTemplate;

    /**
     * 保存mongo对象
     */
    @Override
    public void saveMongoCustomerSmsList(MongoCustomerSmsList list) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(list.getId()));
        Update update = new Update();
        update.inc("hit_num", 1);
        riskMongoTemplate.updateFirst(query, update, MongoCustomerSmsList.class);
        if (null != list) {
            riskMongoTemplate.save(list);
        }
    }

    @Override
    public List<MongoCustomerSmsList> findMongoCustomerSmsListByIds(List<Long> list) {
        return null;
    }

    @Override
    public MongoCustomerSmsList findMongoCustomerSmsListById(Long id) {
        return null;
    }

    @Override
    public List<MongoCustomerSmsGroup> findMongoCustomerSmsListByCaseId(Long caseId) {
        Query query = new Query();

        query.addCriteria(Criteria.where("caseId").is(caseId));

        List<MongoCustomerSmsList> smsList = riskMongoTemplate.find(query, MongoCustomerSmsList.class);

        if (smsList != null && !smsList.isEmpty()) {
            return smsList.get(0).getSmsGroupList();
        } else {
            return null;
        }
    }

}
