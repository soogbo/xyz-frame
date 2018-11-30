package xyz.dev.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
	public static Connection getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://101.132.104.23:3306/information_schema?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true&&useSSL=false";
//			String url = "jdbc:mysql://101.132.104.23:3306/information_schema?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8";
			String user = "root";
			String password = "502503504";
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void close(Connection connection){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {		 
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement preparedStatement){
		if(preparedStatement!=null){
			try {
				preparedStatement.close();
			} catch (SQLException e) {		 
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet resultSet){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {		 
				e.printStackTrace();
			}
		}
	}	 
}
