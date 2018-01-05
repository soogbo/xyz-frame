package xyz.dev.generator.util;

/**
 * 工具类
 * @author LS
 * @version 1.0,2014-10-25
 * @since JDK1.6
 */
public class GeneratorUtil {
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String firstWordLarge(String str){		
		if(str==null){
			return "";
		}
		if(str.length()<1){
			return str;
		}
		if(str.length()==1){
			return str.toUpperCase();
		}
		return str.substring(0, 1).toUpperCase()+str.substring(1).toLowerCase();
	}
}
