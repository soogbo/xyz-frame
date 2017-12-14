package xyz.frame.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import xyz.frame.pojo.po.User;
import xyz.frame.utils.FrameMapper;

public interface UserMapper extends FrameMapper<User>{
    
    /**
     * @return 所有user
     */
    @Select(value = { "select * from ",User.TABLE_NAME})
    public List<User> findAllUser();    
    
}