package xyz.frame.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import xyz.frame.rbac.pojo.po.RbacPermission;
import xyz.frame.rbac.pojo.po.RbacRolePermission;
import xyz.frame.utils.FrameMapper;

public interface RbacPermissionMapper extends FrameMapper<RbacPermission> {

    @Select(value = { "<script>", "select ", RbacPermission.COLUMN_LIST_ALIAS_T, " from ", 
            RbacPermission.TABLE_NAME, " t, ", 
            RbacRolePermission.TABLE_NAME, " y ", 
            " where y.role_id in ",
            "<foreach item=\"item\" index=\"index\" collection=\"roleIdList\" open=\"(\" separator =\",\" close=\")\">#{item}</foreach>",
            " and y.permission_id=t.id and t.valid=1", "</script>" })
    List<RbacPermission> selectByRoleIds(List<Long> roleIdList);

}