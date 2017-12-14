package com.dev.generator.dao;

import com.dev.generator.dao.jdbc.ColumnDAOImpl;

/**
 * DAO工作类
 * @author LS
 */
public class GeneratorDAOFactory {
	private static GeneratorDAOFactory generatorDAOFactory = null;

	/**
	 * 构造函数私有化，实现单例模式
	 */
	private GeneratorDAOFactory() {
	}
	
	public static GeneratorDAOFactory instance() {
		if (generatorDAOFactory == null) {
			generatorDAOFactory = new GeneratorDAOFactory();
		}
		return generatorDAOFactory;
	}
	
	public IColumnDAO getColumnDAO(){
		return new ColumnDAOImpl();
	}
}
