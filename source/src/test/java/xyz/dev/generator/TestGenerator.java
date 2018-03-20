package xyz.dev.generator;

import xyz.dev.generator.main.EntityGenerator;
import xyz.dev.generator.main.Generator;
import xyz.dev.generator.main.MapperGenerator;

/**
 * 测试类
 * @author LS
 */
public class TestGenerator{

	/**
	 * 生成entity类、mapper接口
	 * @param entity 实体类名
	 * @param tableSchema 数据库名
	 * @param tablename 表名，表必须已经在数据库中建好
	 * @param moduleName 模块名
	 */
	public static void genSource(String entity,String tableSchema,String tablename, String moduleName){
		boolean result = true;
		
		String projectBasePath = "D:/xyz-frame-autocreate-po-mapper/";
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
		genSource("Category", "xyz_frame", "category", "mrshi");
		genSource("Customer", "xyz_frame", "customer", "mrshi");
		genSource("Goods", "xyz_frame", "goods", "mrshi");
		genSource("Order", "xyz_frame", "order", "mrshi");
		genSource("OrderGoods", "xyz_frame", "order_goods", "mrshi");
		genSource("Supplier", "xyz_frame", "supplier", "mrshi");
	}

}
