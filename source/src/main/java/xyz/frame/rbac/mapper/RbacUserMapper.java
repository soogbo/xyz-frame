package xyz.frame.rbac.mapper;

import org.apache.ibatis.annotations.Select;

import xyz.frame.rbac.pojo.po.RbacUser;
import xyz.frame.utils.FrameMapper;

public interface RbacUserMapper extends FrameMapper<RbacUser> {

    @Select(value = { "select ", RbacUser.COLUMN_LIST, " from ", RbacUser.TABLE_NAME, " where username=#{username}" })
    RbacUser selectByUsername(String username);

}