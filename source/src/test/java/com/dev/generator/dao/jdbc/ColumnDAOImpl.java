package com.dev.generator.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dev.generator.dao.IColumnDAO;
import com.dev.generator.util.DbUtil;
import com.dev.generator.vo.ColumnVO;
import com.dev.generator.vo.TableVO;

 

/**
 * 字段DAO实现类
 * @author LS
 */
public class ColumnDAOImpl implements IColumnDAO {

	public List<String> queryKeyColumn(String tableSchema,String tableName){
		List<String> resultList = new ArrayList<String>();
		String sql  = "SELECT C.COLUMN_NAME,COLUMN_KEY,EXTRA COLUMNNAME FROM information_schema..COLUMNS C WHERE UPPER(TABLE_SCHEMA)=? and UPPER(TABLE_NAME) =?";		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = DbUtil.getConn();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tableSchema.toUpperCase());
			preparedStatement.setString(2, tableName.toUpperCase());
			resultSet = preparedStatement.executeQuery();
			String columnName = null;
			while(resultSet.next()){
				columnName = resultSet.getString(1);
				if(resultSet.getString(2).indexOf("PRI")>=0){
					resultList.add(columnName);
				}				
			}
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}finally{
			DbUtil.close(resultSet);
			DbUtil.close(preparedStatement);
			DbUtil.close(connection);
		}
 		return resultList;
	}

	public List<ColumnVO> queryColmun(String tableSchema,String tableName){
		String sql = " SELECT C.TABLE_NAME,C.COLUMN_NAME,C.COLUMN_COMMENT,C.DATA_TYPE,COLUMN_KEY,EXTRA FROM information_schema.COLUMNS C WHERE UPPER(TABLE_SCHEMA)=? and UPPER(TABLE_NAME)=?";
		List<ColumnVO> resultList = new ArrayList<ColumnVO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = DbUtil.getConn();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tableSchema.toUpperCase());
			preparedStatement.setString(2, tableName.toUpperCase());
			resultSet = preparedStatement.executeQuery();
			ColumnVO columnVO = null;
			int idx = 1;
			while(resultSet.next()){
				columnVO = new ColumnVO();
				idx = 1;
				columnVO.setTableName(resultSet.getString(idx++));
				columnVO.setColumnName(resultSet.getString(idx++));
				columnVO.setComments(resultSet.getString(idx++));
				columnVO.setDataType(resultSet.getString(idx++));
				columnVO.setColumnKey(resultSet.getString(idx++));
				columnVO.setExtra(resultSet.getString(idx++));
				resultList.add(columnVO);
			}
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}finally{
			DbUtil.close(resultSet);
			DbUtil.close(preparedStatement);
			DbUtil.close(connection);
		}
 		return resultList;
	}

	@Override
	public TableVO queryTableInfo(String tableSchema, String tableName) {
		String sql = " SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.`TABLES` t WHERE UPPER(TABLE_SCHEMA)=? and UPPER(TABLE_NAME)=?";
		TableVO result = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			connection = DbUtil.getConn();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tableSchema.toUpperCase());
			preparedStatement.setString(2, tableName.toUpperCase());
			resultSet = preparedStatement.executeQuery();						
			while(resultSet.next()){
				result = new TableVO();
				result.setTableName(resultSet.getString(1));
				result.setComment(resultSet.getString(2));
				break;
			}
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}finally{
			DbUtil.close(resultSet);
			DbUtil.close(preparedStatement);
			DbUtil.close(connection);
		}
 		return result;
	}
	
	

	
}
