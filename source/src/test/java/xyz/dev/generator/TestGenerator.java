package xyz.dev.generator;

import xyz.dev.generator.main.EntityGenerator;
import xyz.dev.generator.main.Generator;
import xyz.dev.generator.main.MapperGenerator;

/**
 * 测试类
 * @author LS
 */
public class TestGenerator{
    
    // 数据库名
    public static final String tableSchema = "xyz_frame";

    public static void autoGenSource(String tablename, String moduleName) {
        String entityName = "";
        if (tablename.contains("_") && !tablename.startsWith("_") && !tablename.endsWith("_")) {
            String[] split = tablename.split("_");
            for (String string : split) {
                char upperCase = Character.toUpperCase(string.charAt(0));
                string = upperCase + string.substring(1);
                entityName += string;
            }
        }
        genSource(entityName, tableSchema, tablename, moduleName);
    }
    
	/**
	 * 生成entity类、mapper接口
	 * @param entity 实体类名
	 * @param tableSchema 数据库名
	 * @param tablename 表名，表必须已经在数据库中建好
	 * @param moduleName 模块名
	 */
	public static void genSource(String entity,String tableSchema,String tablename, String moduleName){
		boolean result = true;
		
		String projectBasePath = "C:/xyz-frame/";
		Generator gen = new EntityGenerator();
		gen.setEntity(entity);		
		gen.setTableSchema(tableSchema);
		gen.setTablename(tablename);
		gen.setOrgName("xyz");
		gen.setProjectName("frame");
		gen.setModuleName(moduleName);		
		gen.setTargetDir(projectBasePath+"source/xyz-frame/src/main/java/");		
		boolean entityResult = gen.generate();		
		result = result && entityResult;
		
		gen = new MapperGenerator();
		gen.setEntity(entity);		
		gen.setTableSchema(tableSchema.toUpperCase());
		gen.setTablename(tablename.toUpperCase());
		gen.setOrgName("xyz");
		gen.setProjectName("frame");
		gen.setModuleName(moduleName);		
		gen.setTargetDir(projectBasePath+"source/xyz-frame/src/main/java/");		
		boolean mapperResult = gen.generate();		
		result = result && mapperResult;
		
		if(result){
			System.out.println("生成成功");
		}else{
			System.out.println("生成失败!!");
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//调用后生成:
		//D:/xyz-frame-autocreate-po-mapper/source/xyz-frame/src/main/java/xyz/frame/pojo/po/User.java
		//D:/xyz-frame-autocreate-po-mapper/source/xyz-frame/src/main/java/xyz/frame/mapper/UserMapper.java

		/*String tableSchema = "collection";
		String moduleName = "report.workload";

		Map<String,String> config = new HashMap<>();
		config.put("CollectorDailyWorkloadReport","d_collector_daily_workload_report");
		config.put("CollectorMonthlyWorkloadReport","d_collector_monthly_workload_report");

		for(String k:config.keySet()){
			genSource(k,tableSchema,config.get(k),moduleName);
		}*/
	    
	    autoGenSource("rbac_group", "rbac");
	    autoGenSource("rbac_menu", "rbac");
	    autoGenSource("rbac_permission", "rbac");
	    autoGenSource("rbac_role", "rbac");
	    autoGenSource("rbac_role_permission", "rbac");
	    autoGenSource("rbac_user", "rbac");
	    autoGenSource("rbac_user_role", "rbac");
		
	}

}
