package xyz.frame.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoCustomerSmsListRepository extends MongoRepository<MongoCustomerSmsList, String>{
    void saveMongoCustomerSmsList(MongoCustomerSmsList list);

    List<MongoCustomerSmsList> findMongoCustomerSmsListByIds(List<Long> list);

    MongoCustomerSmsList findMongoCustomerSmsListById(Long id);

    /**
     * 根据caseId查找短信
     *
     * @param caseId 案件列表
     * @return 短信列表
     */
    List<MongoCustomerSmsGroup> findMongoCustomerSmsListByCaseId(Long caseId);

}
