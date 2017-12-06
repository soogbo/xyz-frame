package xyz.frame.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import xyz.frame.pojo.po.ScheduleTaskPo;
import xyz.frame.utils.FrameMapper;

public interface ScheduleTaskMapper extends FrameMapper<ScheduleTaskPo>{
    
    /**
     * @return 所有cron任务
     */
    @Select(value = { "select ",ScheduleTaskPo.COLUMN_LIST," from ",ScheduleTaskPo.TABLE_NAME, " where type = 1"})
    public List<ScheduleTaskPo> findScheduleTaskList();    
    
}