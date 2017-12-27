package xyz.frame.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import xyz.frame.pojo.po.ParamClass;
import xyz.frame.utils.FrameMapper;

public interface ParamClassMapper extends FrameMapper<ParamClass> {

    @Select({ "select ", ParamClass.COLUMN_LIST, " from ", ParamClass.TABLE_NAME, " where class_code = #{classCode}" })
    ParamClass getByClassCode(@Param("classCode") String classCode);

}