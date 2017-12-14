package com.dev.generator.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.dev.generator.dao.GeneratorDAOFactory;
import com.dev.generator.util.SqlKeyword;
import com.dev.generator.vo.ColumnVO;
import com.dev.generator.vo.PropertyVO;
import com.dev.generator.vo.TableVO;

/**
 * entity生成器
 * @author LS
 */
public class EntityGenerator extends Generator {
	
	private String templateName = "entity.ftl";
	
	@Override
	public boolean generate() {
		if(targetDir == null || entity == null || projectName == null){
			return false;
		}
		try{
			List<PropertyVO> list = new ArrayList<PropertyVO>();		
					
			TableVO tableVO = GeneratorDAOFactory.instance().getColumnDAO().queryTableInfo(getTableSchema(),getTablename());
			List<ColumnVO> listColumn = GeneratorDAOFactory.instance().getColumnDAO().queryColmun(getTableSchema(),getTablename());
			PropertyVO pv = null;
			String type = "";			
			
			getParameters().put("primaryKey", false);    
			getParameters().put("identity", false);
		    for(int i=0;i<listColumn.size();i++){
		    	pv = new PropertyVO();
		    	pv.setColumnName(listColumn.get(i).getColumnName());
		    	pv.setConvertColumnName(SqlKeyword.process(listColumn.get(i).getColumnName()));
		    	pv.setProperty(getProperty(pv.getColumnName()));
		    	if(listColumn.get(i).getComments()!=null){
		    		pv.setComment(listColumn.get(i).getComments());	
		    	}else{
		    		pv.setComment("");
		    	}		    			         
		    	type = Generator.TYPEMAP.get(listColumn.get(i).getDataType());
		    	if(type==null){
		    		throw new RuntimeException("请配置jdbcType:"+listColumn.get(i).getDataType()+"与javaType的映谢关系");
		    	}
		    	pv.setType(type);
		    	if(listColumn.get(i).getColumnKey().indexOf("PRI")>=0){
		    		getParameters().put("primaryKey", true);
		    		pv.setPrimaryKey(true);
		    	} 
		    	if(listColumn.get(i).getExtra().indexOf("auto_increment")>=0){
		    		getParameters().put("identity", true);
		    		pv.setIdentity(true);
		    	} 
		    	list.add(pv);
		    }
										    
		    getParameters().put("tableVO", tableVO);
			getParameters().put("columnList", list);
			
			String content = super.doGenerate();
			String targetFile = targetDir;		
			targetFile += "pojo/po/"+entity+".java";
			writeStringToFile(new File(targetFile), content,getEncoding());
			System.out.println("生成"+targetFile+"...成功");
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}	
	
	private String getProperty(String columnName){
		String property = null;
		String[] strArr = columnName.toLowerCase().split("_");
		for(String str:strArr){
			if(property==null){
				property = str;	
			}else{
				if(str.length()==1){
					property = property+str.toUpperCase();	
				}else{
					property = property+str.substring(0,1).toUpperCase()+str.substring(1);
				}				
			}			
		}
		return property;
	}	

	@Override
	public String getTemplateName() {
		return templateName;
	}

}
