package xyz.frame.pojo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 定时任务表
 */
@Entity
@Table(name = ScheduleTask.TABLE_NAME)
public class ScheduleTask implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "s_schedule_task";
    public static final String COLUMN_LIST = "id,name,task_group,type,job_class,status,cron_expression,repeat_interval,description,pre_running_time,next_running_time,create_at,update_at";
    
    /**
     * 定时任务ID
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * 任务名称
     */
    @Column(name = "name")
    private String name;
    
    /**
     * 任务分组
     */
    @Column(name = "task_group")
    private String taskGroup;
    
    /**
     * 触发类型: 1-cron, 2-simple
     */
    @Column(name = "type")
    private Integer type;
    
    /**
     * 执行类jobClass
     */
    @Column(name = "job_class")
    private String jobClass;
    
    /**
     * 任务状态：停止-NONE, 启动-NORMAL, 暂停-PAUSED, 执行中-RUNNING, 删除-DELETED, 已停止-STOPPED, 错误-ERROR
     */
    @Column(name = "status")
    private String status;
    
    /**
     * 任务运行时间表达式
     */
    @Column(name = "cron_expression")
    private String cronExpression;
    
    /**
     * 时间间隔
     */
    @Column(name = "repeat_interval")
    private Integer repeatInterval;
    
    /**
     * 任务描述
     */
    @Column(name = "description")
    private String description;
    
    /**
     * 上次执行时间
     */
    @Column(name = "pre_running_time")
    private java.util.Date preRunningTime;
    
    /**
     * 下次执行时间
     */
    @Column(name = "next_running_time")
    private java.util.Date nextRunningTime;
    
    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private java.util.Date createAt;
    
    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private java.util.Date updateAt;
    
	/**
	 * 设置定时任务ID
	 * @param id 定时任务ID
	 */    
    public void setId(Long id){
        this.id = id;
    }
    
	/**
	 * 获取定时任务ID
	 * @return id
	 */    
    public Long getId(){
        return this.id;
    }    
    
	/**
	 * 设置任务名称
	 * @param name 任务名称
	 */    
    public void setName(String name){
        this.name = name;
    }
    
	/**
	 * 获取任务名称
	 * @return name
	 */    
    public String getName(){
        return this.name;
    }    
    
	/**
	 * 设置任务分组
	 * @param taskGroup 任务分组
	 */    
    public void setTaskGroup(String taskGroup){
        this.taskGroup = taskGroup;
    }
    
	/**
	 * 获取任务分组
	 * @return taskGroup
	 */    
    public String getTaskGroup(){
        return this.taskGroup;
    }    
    
	/**
	 * 设置触发类型: 1-cron, 2-simple
	 * @param type 触发类型: 1-cron, 2-simple
	 */    
    public void setType(Integer type){
        this.type = type;
    }
    
	/**
	 * 获取触发类型: 1-cron, 2-simple
	 * @return type
	 */    
    public Integer getType(){
        return this.type;
    }    
    
	/**
	 * 设置执行类jobClass
	 * @param jobClass 执行类jobClass
	 */    
    public void setJobClass(String jobClass){
        this.jobClass = jobClass;
    }
    
	/**
	 * 获取执行类jobClass
	 * @return jobClass
	 */    
    public String getJobClass(){
        return this.jobClass;
    }    
    
	/**
	 * 设置任务状态：停止-NONE, 启动-NORMAL, 暂停-PAUSED, 执行中-RUNNING, 删除-DELETED, 已停止-STOPPED, 错误-ERROR
	 * @param status 任务状态
	 */    
    public void setStatus(String status){
        this.status = status;
    }
    
	/**
	 * 获取任务状态：停止-NONE, 启动-NORMAL, 暂停-PAUSED, 执行中-RUNNING, 删除-DELETED, 已停止-STOPPED, 错误-ERROR
	 * @return status
	 */    
    public String getStatus(){
        return this.status;
    }    
    
	/**
	 * 设置任务运行时间表达式
	 * @param cronExpression 任务运行时间表达式
	 */    
    public void setCronExpression(String cronExpression){
        this.cronExpression = cronExpression;
    }
    
	/**
	 * 获取任务运行时间表达式
	 * @return cronExpression
	 */    
    public String getCronExpression(){
        return this.cronExpression;
    }    
    
	/**
	 * 设置时间间隔
	 * @param repeatInterval 时间间隔
	 */    
    public void setRepeatInterval(Integer repeatInterval){
        this.repeatInterval = repeatInterval;
    }
    
	/**
	 * 获取时间间隔
	 * @return repeatInterval
	 */    
    public Integer getRepeatInterval(){
        return this.repeatInterval;
    }    
    
	/**
	 * 设置任务描述
	 * @param description 任务描述
	 */    
    public void setDescription(String description){
        this.description = description;
    }
    
	/**
	 * 获取任务描述
	 * @return description
	 */    
    public String getDescription(){
        return this.description;
    }    
    
	/**
	 * 设置上次执行时间
	 * @param preRunningTime 上次执行时间
	 */    
    public void setPreRunningTime(java.util.Date preRunningTime){
        this.preRunningTime = preRunningTime;
    }
    
	/**
	 * 获取上次执行时间
	 * @return preRunningTime
	 */    
    public java.util.Date getPreRunningTime(){
        return this.preRunningTime;
    }    
    
	/**
	 * 设置下次执行时间
	 * @param nextRunningTime 下次执行时间
	 */    
    public void setNextRunningTime(java.util.Date nextRunningTime){
        this.nextRunningTime = nextRunningTime;
    }
    
	/**
	 * 获取下次执行时间
	 * @return nextRunningTime
	 */    
    public java.util.Date getNextRunningTime(){
        return this.nextRunningTime;
    }    
    
	/**
	 * 设置创建时间
	 * @param createAt 创建时间
	 */    
    public void setCreateAt(java.util.Date createAt){
        this.createAt = createAt;
    }
    
	/**
	 * 获取创建时间
	 * @return createAt
	 */    
    public java.util.Date getCreateAt(){
        return this.createAt;
    }    
    
	/**
	 * 设置更新时间
	 * @param updateAt 更新时间
	 */    
    public void setUpdateAt(java.util.Date updateAt){
        this.updateAt = updateAt;
    }
    
	/**
	 * 获取更新时间
	 * @return updateAt
	 */    
    public java.util.Date getUpdateAt(){
        return this.updateAt;
    }
}