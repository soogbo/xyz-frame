package com.dev.generator.vo;

public class TableVO {
	private String tableName;
	/**
	 * 注释
	 */
	private String comment = "";
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
