package xyz.frame.datasource;

import javax.sql.DataSource;

/**
 * @author shisp
 * @date 2018-11-30 17:43:09
 */
public class DataSourceHolder {
    private DataSourceHolder() {
    }
    
    public enum DataSourceType {
        MASTER, SLAVE;
    }
    
	/**
	 * 数据源类型列
	 */
	private static final ThreadLocal<DataSourceType> DATA_SOURCES = new ThreadLocal<>();
	/**
	 * master数据源列表
	 */
	private static final ThreadLocal<DataSource> MASTER_LOCAL = new ThreadLocal<>();
	/**
	 * slave数据源列表
	 */
	private static final ThreadLocal<DataSource> SLAVE_LOCAL = new ThreadLocal<>();


    /**
	 * 设置数据源
	 * @param dataSourceKey
	 */
	private static void setDataSource(DataSourceType dataSourceKey) {
	    DATA_SOURCES.set(dataSourceKey);
	}

	/**
	 * 获取数据源
	 * @return
	 */
	private static DataSourceType getDataSource() {
		return DATA_SOURCES.get();
	}

	/**
	 * 设置数据源为master
	 */
	public static void setMaster() {
		setDataSource(DataSourceType.MASTER);
	}

	/**
	 * 设置数据源为slave
	 */
	public static void setSlave() {
		setDataSource(DataSourceType.SLAVE);
	}
    
    public static boolean isMaster() {
        return DataSourceType.MASTER.equals(getDataSource());
    }

    public static boolean isSlave() {
        return DataSourceType.SLAVE.equals(getDataSource());
    }
	
	/**
	 * 将master放入threadlocal
	 * @param master
	 */
	public static void setMaster(DataSource master) {
		MASTER_LOCAL.set(master);
	}
	
	/**
	 * 将slave放入threadlocal
	 * @param master
	 */
	public static void setSlave(DataSource slave) {
		SLAVE_LOCAL.set(slave);
	}

	/**
	 * 清除数据源 
	 */
	public static void clearDataSource() {
	    DATA_SOURCES.remove();
	    
		MASTER_LOCAL.remove();
		SLAVE_LOCAL.remove();
	}

}
