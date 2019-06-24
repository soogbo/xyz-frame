package xyz.dev.generator.vo;


/**
 * 属性值对象
 * @author LS
 * @version 1.0,2014-10-25
 * @since JDK1.6
 */
public class PropertyVO {	
	
	/**
	 * 注释
	 */
	private String comment = "";
	private String columnName = "";
	private String convertColumnName = "";
	private String property = "";
	
	private String type = "";
	private boolean primaryKey;
	private boolean identity;

	/**
	 * 首字母大小 属性
	 * @return
	 */
	public String getFirstWordLargeProperty(){
		if(property==null){
			return "";
		}
		if(property.length()<=1){
			return property;
		}
		return property.substring(0, 1).toUpperCase()+property.substring(1);
	}	

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isIdentity() {
		return identity;
	}

	public void setIdentity(boolean identity) {
		this.identity = identity;
	}

	public String getConvertColumnName() {
		return convertColumnName;
	}

	public void setConvertColumnName(String convertColumnName) {
		this.convertColumnName = convertColumnName;
	}	 	
	
	
}
