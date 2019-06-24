package xyz.frame.datasource2;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author shisp
 * @date 2018-12-03 13:52:42
 */
public class DynamicDataSourceContextHolder {
    private DynamicDataSourceContextHolder() {
    }

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    static List<String> dataSourceIds = new ArrayList<>();

    static void setDataSourceType(String dataSourceType) {
        CONTEXT_HOLDER.set(dataSourceType);
    }

    static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }

    /**
     * 判断指定DataSource当前是否存在
     *
     * @param dataSourceId
     * @return
     */
    static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }

    static boolean addDataSourceIds(String dataSourceId) {
        return dataSourceIds.add(dataSourceId);
    }
}