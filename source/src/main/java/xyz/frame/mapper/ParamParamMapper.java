package xyz.frame.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import xyz.frame.pojo.po.ParamParam;
import xyz.frame.utils.FrameMapper;

public interface ParamParamMapper extends FrameMapper<ParamParam> {

    @Update({ "update ", ParamParam.TABLE_NAME, " set class_id = null where class_id = #{class_id}" })
    int updateClassIdToNull(@Param("classId") Long classId);

}