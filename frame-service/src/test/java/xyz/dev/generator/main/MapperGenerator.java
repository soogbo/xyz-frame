package xyz.dev.generator.main;

import java.io.File;

/**
 * DAO生成器
 * @author LS
 */
public class MapperGenerator extends Generator {

	private String templateName = "mapper.ftl";
	
	@Override
	public boolean generate() {
		if(targetDir == null || entity == null || projectName == null){
			return false;
		}
		try{			
			String content = super.doGenerate();
			String targetFile = targetDir;											
			targetFile += "mapper/"+entity+"Mapper.java";					
		    writeStringToFile(new File(targetFile), content,getEncoding());
			System.out.println("生成"+targetFile+"...成功");
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getTemplateName() {
		return templateName;
	}


}
