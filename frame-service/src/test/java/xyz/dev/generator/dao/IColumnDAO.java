package xyz.dev.generator.dao;

import java.util.List;

import xyz.dev.generator.vo.ColumnVO;
import xyz.dev.generator.vo.TableVO;

 

/**
 * 字段DAO接口
 * @author LS
 */
public interface IColumnDAO{
	
	/**
	 * 查询主键字段
	 * @param tableSchema
	 * @param tableName
	 * @return
	 */
	public List<String> queryKeyColumn(String tableSchema,String tableName);
	
	/**
	 * 查询字段信息
	 * @param tableSchema
	 * @param tableName
	 * @return
	 */
	public List<ColumnVO> queryColmun(String tableSchema,String tableName);
	
	/**
	 * 查询表注释
	 * @param tableSchema
	 * @param tableName
	 * @return
	 */
	public TableVO queryTableInfo(String tableSchema,String tableName);
}
