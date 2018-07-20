package xyz.frame.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import xyz.frame.pojo.po.ParamClass;
import xyz.frame.pojo.po.ParamParam;
import xyz.frame.utils.FrameMapper;

@Repository
public interface ParamParamMapper extends FrameMapper<ParamParam> {

    @Update({ "update ", ParamParam.TABLE_NAME, " set class_id = null where class_id = #{class_id}" })
    int updateClassIdToNull(@Param("classId") Long classId);

    @Select({ "<script>", "select", ParamParam.COLUMN_LIST_ALIAS_T, " from ", ParamParam.TABLE_NAME, " t, ", ParamClass.TABLE_NAME,
            " pc where t.class_id = pc.id and pc.class_code in ",
            " <foreach item=\"item\" index=\"index\" collection=\"classCodeList\" open=\"(\" separator =\",\" close=\")\">#{item}</foreach>", "</script>" })
    List<ParamParam> findListByClassCode(@Param("classCodeList") List<String> classCodeList);
    
//    @Delete({"delete from param_param"})
    @Update({ "update ", ParamParam.TABLE_NAME, " set class_id = 123"})
    void testDeleteAll();
}