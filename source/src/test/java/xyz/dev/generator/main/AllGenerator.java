package xyz.dev.generator.main;

/**
 * 代码生成汇总处理器
 * @author LS
 */
public class AllGenerator extends Generator {
	
	private EntityGenerator vo = new EntityGenerator();
//	private MapperGenerator dao = new MapperGenerator();
	
	@Override
	public boolean generate() {
		vo.setEntity(entity);
		vo.setTableSchema(tableSchema);
		vo.setTablename(tablename);		
		vo.setOrgName(orgName);
		vo.setModuleName(moduleName);
		vo.setProjectName(projectName);
		vo.setTargetDir(targetDir);
		vo.setTemplateDir(templateDir);
		
		boolean voResult = vo.generate();
		
//		dao.setEntity(entity);
//		dao.setTableSchema(tableSchema);
//		dao.setTablename(tablename);
//		dao.setOrgName(orgName);
//		dao.setModuleName(moduleName);
//		dao.setProjectName(projectName);
//		dao.setTargetDir(targetDir);
//		dao.setTemplateDir(templateDir);
//		
//		boolean daoResult = dao.generate();
				
//		return voResult&daoResult&daoImplResult;
		return voResult;
	}

	@Override
	public String getTemplateName() {
		return null;
	}

}
