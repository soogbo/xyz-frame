package com.dev.generator.vo;

/**
 * 字段值对像
 * @author LS
 * @version 1.0,2014-10-25
 * @since JDK1.6
 */
public class ColumnVO {
	private String tableName;
	private String columnName;
	private String comments;
	private String dataType;
	private String columnKey;
	private String extra;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}	 	
	
}
