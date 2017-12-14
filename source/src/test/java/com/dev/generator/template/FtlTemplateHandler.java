package com.dev.generator.template;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 模板引擎实现类
 * @author LS
 * @version 1.0,2014-10-25
 * @since JDK1.6
 */
public class FtlTemplateHandler implements TemplateHandler{
	private String directory;
	private String filePath;
	private String encoding;
	private Map<Object,Object> parameters;
	
	
	@SuppressWarnings("deprecation")
	public String getContent() {
		try{
			Configuration freemarkerCfg = new Configuration();
	        freemarkerCfg.setDirectoryForTemplateLoading(new File(directory));
	        freemarkerCfg.setEncoding(Locale.getDefault(),encoding);
	        Template template = freemarkerCfg.getTemplate(filePath);	        
            template.setEncoding(encoding);
            
            HashMap<Object,Object> root = new HashMap<Object,Object>();			
            root.put("parameters", parameters);
            
            StringWriter writer = new StringWriter();			
            template.process(root, writer);
            return writer.toString();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}


	public void setDirectory(String directory) {
		this.directory = directory;
	}


	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public void setParameters(Map<Object,Object> parameters) {
		this.parameters = parameters;
	}

}
