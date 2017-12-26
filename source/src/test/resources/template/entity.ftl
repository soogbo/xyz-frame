package ${parameters.orgName}.${parameters.projectName}.<#if parameters.moduleName?has_content>${parameters.moduleName}.</#if>pojo.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
<#if parameters.primaryKey>import javax.persistence.Id;
</#if><#if parameters.identity>import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
</#if>import javax.persistence.Table;

/**
 * ${parameters.tableVO.comment}
 */
@Entity
@Table(name = ${parameters.entity}.TABLE_NAME)
public class ${parameters.entity} implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = " ${parameters.tableVO.tableName} ";
    public static final String COLUMN_LIST = " <#list parameters.columnList as pv>${pv.convertColumnName}<#if pv_has_next>,</#if></#list> ";
    public static final String COLUMN_LIST_ALIAS_T = " <#list parameters.columnList as pv>t.${pv.convertColumnName}<#if pv_has_next>,</#if></#list> ";        
    <#list parameters.columnList as pv>
    
    /**
     * ${pv.comment}
     */
    <#if pv.primaryKey>@Id
    </#if><#if pv.identity>@GeneratedValue(strategy=GenerationType.IDENTITY)
    </#if>@Column(name = "${pv.columnName}")
    private ${pv.type} ${pv.property};
    </#list>        
    <#list parameters.columnList as pv>
    
	/**
	 * 设置${pv.comment}
	 * @param ${pv.property} ${pv.comment}
	 */    
    public void set${pv.firstWordLargeProperty}(${pv.type} ${pv.property}){
        this.${pv.property} = ${pv.property};
    }
    
	/**
	 * 获取${pv.comment}
	 * @return ${pv.property}
	 */    
    public ${pv.type} get${pv.firstWordLargeProperty}(){
        return this.${pv.property};
    }    
    </#list>    
}