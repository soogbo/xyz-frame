package xyz.dev.generator.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import xyz.dev.generator.template.FtlTemplateHandler;
import xyz.dev.generator.template.TemplateHandler;

/**
 * 代码生成器基础类
 * 
 * @author LS
 * @version 1.0,2014-10-25
 * @since JDK1.6
 */
public abstract class Generator {
	public static Map<String, String> TYPEMAP = new HashMap<String, String>();
	static {
		TYPEMAP.put("varchar", "String");
		TYPEMAP.put("char", "String");
		TYPEMAP.put("tinytext", "String");
		TYPEMAP.put("text", "String");
		TYPEMAP.put("mediumtext", "String");
		TYPEMAP.put("longtext", "String");
		
		
		TYPEMAP.put("tinyint", "Integer");
		TYPEMAP.put("smallint", "Integer");
		TYPEMAP.put("mediumint", "Integer");
		TYPEMAP.put("int", "Integer");
		TYPEMAP.put("bigint", "Long");		
		TYPEMAP.put("float", "Float");
		TYPEMAP.put("double", "Double");		
		TYPEMAP.put("decimal", "Double");
		
		TYPEMAP.put("date", "java.util.Date");
		TYPEMAP.put("time", "java.util.Date");
		TYPEMAP.put("datetime", "java.util.Date");		
		TYPEMAP.put("timestamp", "java.util.Date");
		

	}
	private Map<Object, Object> parameters = new HashMap<Object, Object>();
	protected String templateDir = "template";	
	protected String orgName;
	protected String moduleName;

	protected String application = "baseService";
	protected String sharedProject = "sharedProject";
	protected String uiProject;

	protected String targetDir;

	protected String entity;
	protected String projectName;
	protected String tableSchema;
	protected String tablename;

	protected String configFile;

	protected String encoding = "UTF-8";

	public String getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(String targetDir) {
		this.targetDir = targetDir;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getSharedProject() {
		return sharedProject;
	}

	public void setSharedProject(String sharedProject) {
		this.sharedProject = sharedProject;
	}

	public String getUiProject() {
		return uiProject;
	}

	public void setUiProject(String uiProject) {
		this.uiProject = uiProject;
	}

	public abstract boolean generate();

	public abstract String getTemplateName();

	protected String doGenerate() throws Exception {		
		TemplateHandler templateHandler = new FtlTemplateHandler();
		String basePath = Generator.class.getResource("/").getFile();
		templateHandler.setDirectory(basePath+templateDir);
		templateHandler.setFilePath(getTemplateName());
		templateHandler.setEncoding(encoding);

		parameters.put("orgName", orgName);

		parameters.put("projectName", projectName);
		parameters.put("entity", entity);
		parameters.put("tablename", tablename);
		parameters.put("moduleName", moduleName);
		templateHandler.setParameters(parameters);

		String html = templateHandler.getContent();

		String targetFile = targetDir;
		File d = new File(targetFile);
		if (!d.isDirectory()) {
			forceMkdir(d);
		}

		targetFile = targetFile.replace("\\\\", "/");
		targetFile = targetFile.replace("\\", "/");
		targetFile = targetFile.replace("//", "/");
		if (targetFile.endsWith("/")) {
			targetFile = targetFile.substring(0, targetFile.length() - 1);
		}

		String orgPath = this.getOrgPath();
		String projectName = this.getProjectName();

		String wholePath = orgPath;
		wholePath += "/" + projectName;
		if (this.moduleName != null) {
			wholePath += "/" + this.getModulePath();
		}
		if (!targetFile.endsWith(wholePath)) {
			targetFile += "/" + wholePath;
		}

		// System.out.println(targetFile);
		targetDir = targetFile;

		return html;

	}

	protected void forceMkdir(File file) {
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	protected void writeStringToFile(File file, String content,
			String charsetName) {
		try {
			forceMkdir(file.getParentFile());
			OutputStream os = new FileOutputStream(file, false);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,
					charsetName));
			bw.write(content);
			bw.flush();
			bw.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected String getModulePath() {
		String packagePath = "";
		if (moduleName.contains(".")) {
			String[] split = moduleName.split("\\.");
			for (int i = 0; i < split.length; i++) {
				packagePath += split[i];
				if (i != (split.length - 1)) {
					packagePath += "/";
				}
			}
		} else {
			packagePath = moduleName;
		}
		return packagePath;
	}

	protected String getOrgPath() {
		String orgPath = "";
		if (orgName.contains(".")) {
			String[] split = orgName.split("\\.");
			for (int i = 0; i < split.length; i++) {
				orgPath += split[i];
				if (i != (split.length - 1)) {
					orgPath += "/";
				}
			}
		} else {
			orgPath = orgName;
		}
		return orgPath;
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public Map<Object, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<Object, Object> parameters) {
		this.parameters = parameters;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

}
