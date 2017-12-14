package xyz.frame.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import xyz.frame.pojo.po.ScheduleTask;
import xyz.frame.utils.FrameMapper;

public interface ScheduleTaskMapper extends FrameMapper<ScheduleTask>{
    
    /**
     * @return 所有cron任务
     */
    @Select(value = { "select ",ScheduleTask.COLUMN_LIST," from ",ScheduleTask.TABLE_NAME, " where type = 1"})
    public List<ScheduleTask> findScheduleTaskList();    
    
}