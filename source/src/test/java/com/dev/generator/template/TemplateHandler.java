package com.dev.generator.template;

import java.util.Map;

/**
 * 模板引擎接口
 * @author LS
 * @version 1.0,2014-10-25
 * @since JDK1.6
 */
public interface TemplateHandler {
	public String getContent();
	public void setParameters(Map<Object,Object> map);
	public void setDirectory(String dirName);
	public void setFilePath(String filePath);
	public void setEncoding(String encode);
}
