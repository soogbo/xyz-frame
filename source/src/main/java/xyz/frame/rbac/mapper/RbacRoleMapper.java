package xyz.frame.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import xyz.frame.rbac.pojo.po.RbacRole;
import xyz.frame.rbac.pojo.po.RbacUserRole;
import xyz.frame.utils.FrameMapper;

public interface RbacRoleMapper extends FrameMapper<RbacRole> {

    @Select(value = { "select ", RbacRole.COLUMN_LIST_ALIAS_T, " from ", 
            RbacRole.TABLE_NAME, " t, ", 
            RbacUserRole.TABLE_NAME, " y ",
            " where y.user_id=#{userId} and y.role_id=t.id and t.valid=1" })
    List<RbacRole> selectByUserId(Long userId);

}