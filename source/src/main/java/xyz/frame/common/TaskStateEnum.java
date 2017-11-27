package xyz.frame.common;

/**
 * 任务状态
 * @author lius
 */
public enum TaskStateEnum {
    /**
     * 停止-NONE
     */
	NONE,
	/**
	 * 启动-NORMAL
	 */
	NORMAL,
	/**
	 * 暂停-PAUSED
	 */
	PAUSED,
	/**
	 * 执行中-RUNNING
	 */
	RUNNING,
	/**
	 * 删除-DELETED
	 */
	DELETED,
	/**
	 * 已停止-STOPPED
	 */
	STOPPED,
	/**
	 * 错误-ERROR
	 */
	ERROR
}
