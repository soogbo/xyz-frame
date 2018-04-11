package xyz.frame.mongo;

import java.util.List;

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

@Repository("mongoCustomerSmsListRepository")
public class MongoCustomerSmsListRepositoryImpl implements MongoCustomerSmsListRepository {
//	 private static final Logger logger = LoggerFactory.getLogger(MongoCustomerSmsListDaoImpl.class);

    @Autowired
    private MongoTemplate riskMongoTemplate;

	/**
	 * 保存mongo对象
	 */
	@Override
	public void saveMongoCustomerSmsList(MongoCustomerSmsList list) {

		// TODO Auto-generated method stub
	
		 Query query = new Query();
	       query.addCriteria(Criteria.where("id").is(list.getId()));
	       Update update = new Update();
	       update.inc("hit_num", 1);
	       riskMongoTemplate.updateFirst(query, update, MongoCustomerSmsList.class);
		if(null!=list){
			riskMongoTemplate.save(list);
		}
	}

	@Override
	public List<MongoCustomerSmsList> findMongoCustomerSmsListByIds(
			List<Long> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MongoCustomerSmsList findMongoCustomerSmsListById(Long id) {
		// TODO Auto-generated method stub
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

    @Override
    public List<MongoCustomerSmsList> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MongoCustomerSmsList> findAll(Sort arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomerSmsList> List<S> findAll(Example<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomerSmsList> List<S> findAll(Example<S> arg0, Sort arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomerSmsList> S insert(S arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomerSmsList> List<S> insert(Iterable<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomerSmsList> List<S> save(Iterable<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<MongoCustomerSmsList> findAll(Pageable arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(MongoCustomerSmsList arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Iterable<? extends MongoCustomerSmsList> arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean exists(String arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterable<MongoCustomerSmsList> findAll(Iterable<String> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MongoCustomerSmsList findOne(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomerSmsList> S save(S arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomerSmsList> long count(Example<S> arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends MongoCustomerSmsList> boolean exists(Example<S> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends MongoCustomerSmsList> Page<S> findAll(Example<S> arg0, Pageable arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends MongoCustomerSmsList> S findOne(Example<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }

}
