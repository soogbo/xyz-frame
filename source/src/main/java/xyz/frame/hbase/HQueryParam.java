package xyz.frame.hbase;

import org.apache.hadoop.hbase.client.Scan;

/**
 * Hbase查询参数
 * 
 * @author shisp
 * @date 2018-8-10 14:04:27
 */
public class HQueryParam {

    /**
     * 默认列族
     */
    private String familyName = "cf_1";
    private String tableName;
    private String rowkey;
    private String qualifier;
    private String startRow;
    private String stopRow;
    private Scan scan;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getStartRow() {
        return startRow;
    }

    public void setStartRow(String startRow) {
        this.startRow = startRow;
    }

    public String getStopRow() {
        return stopRow;
    }

    public void setStopRow(String stopRow) {
        this.stopRow = stopRow;
    }

    public Scan getScan() {
        return scan;
    }

    public void setScan(Scan scan) {
        this.scan = scan;
    }

    @Override
    public String toString() {
        return "HQueryParam [familyName=" + familyName + ", tableName=" + tableName + ", rowkey=" + rowkey + ", qualifier=" + qualifier + ", startRow="
                + startRow + ", stopRow=" + stopRow + ", scan=" + scan + "]";
    }

}