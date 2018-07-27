package xyz.frame.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.BaseMapper;
import xyz.frame.pojo.po.UserSharding;


public interface UserShardingMapper extends BaseMapper<UserSharding>{
    
    @Select(value = { "select * from ",UserSharding.TABLE_NAME, " order by user_id asc"})
    List<UserSharding> findAllUser();

    /**
     * @param userId
     * @return
     */
    @Select(value = { "select * from ",UserSharding.TABLE_NAME ," where user_id = #{userId}"})
    UserSharding findByUserId(Long userId);

}