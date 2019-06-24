package xyz.frame.datasource;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源datasource
 * 
 * @author shisp
 * @date 2018-11-30 15:23:52
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * master库 dataSource
     */
    private DataSource master;

    /**
     * slaves库 dataSource
     */
    private List<DataSource> slaves;
    
    public DataSource getMaster() {
        return master;
    }

    public void setMaster(DataSource master) {
        this.master = master;
    }

    public List<DataSource> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<DataSource> slaves) {
        this.slaves = slaves;
    }



    /**
     * 根据标识 获取数据源
     * 
     */
    @Override
    protected DataSource determineTargetDataSource() {
        DataSource resultDataSource = null;
        if (DataSourceHolder.isSlave() && slaves != null && !slaves.isEmpty()) {
            resultDataSource = slaves.get(0);
        } else {
            resultDataSource = master;
        }
        return resultDataSource;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        // do nothing
        return null;
    }

    @Override
    public void afterPropertiesSet() {
        // do nothing
    }
}