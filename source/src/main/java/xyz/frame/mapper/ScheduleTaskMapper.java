package xyz.frame.mapper;

import java.util.List;

import xyz.frame.pojo.entity.ScheduleTask;

public interface ScheduleTaskMapper /*extends FrameworkMapper<ScheduleTask,Long>*/{
    
    /**
     * @return 所有任务
     */
    /*@Select(value = { "select ",ScheduleTask.COLUMN_LIST," from ",ScheduleTask.TABLE_NAME, " where type = 1"})
    @ResultMap(BASER_ESULT_MAP)*/
    public List<ScheduleTask> findScheduleTaskList();    
    
}