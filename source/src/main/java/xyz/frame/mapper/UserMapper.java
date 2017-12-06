package xyz.frame.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import xyz.frame.pojo.po.UserPo;
import xyz.frame.utils.FrameMapper;

public interface UserMapper extends FrameMapper<UserPo>{
    
    /**
     * @return 所有user
     */
    @Select(value = { "select * from ",UserPo.TABLE_NAME})
    public List<UserPo> findAllUser();    
    
}