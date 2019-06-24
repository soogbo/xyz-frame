package xyz.frame.pojo.vo;

/**
 * 定时任务
 * @author shisp
 */
public class ScheduleTaskVo {
    
    private Long taskId;

    /** 任务名称 */
    private String taskName;

    /** 任务分组 */
    private String taskGroup;

    /** 任务状态：停止-NONE, 启动-NORMAL, 暂停-PAUSED, 执行中-RUNNING, 删除-DELETED, 已停止-STOPPED, 错误-ERROR  */
    private String status;

    /** 是否在运行 */
    private boolean isRunning;

    /** 任务运行时间表达式 */
    private String cronExpression;

    /** 任务描述 */
    private String desc;

    /** job运行class*/
    private String jobClass;

    /** 上次运行时间*/
    private String preRunningTime;

    /** 下次运行时间*/
    private String nextRunningTime;

    /** 运行间隔*/
    private Integer repeatInterval;

    /** tigger type 1: cron 2: simple*/
    private Integer type;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getPreRunningTime() {
        return preRunningTime;
    }

    public void setPreRunningTime(String preRunningTime) {
        this.preRunningTime = preRunningTime;
    }

    public String getNextRunningTime() {
        return nextRunningTime;
    }

    public void setNextRunningTime(String nextRunningTime) {
        this.nextRunningTime = nextRunningTime;
    }

    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public String toString() {
        return "ScheduleTaskVO [taskId=" + taskId + ", taskName=" + taskName + ", taskGroup=" + taskGroup + ", status=" + status
                + ", isRunning=" + isRunning + ", cronExpression=" + cronExpression + ", desc=" + desc + ", jobClass=" + jobClass
                + ", preRunningTime=" + preRunningTime + ", nextRunningTime=" + nextRunningTime + ", repeatInterval="
                + repeatInterval + ", type=" + type + "]";
    }
    
}
