package xyz.frame.utils;

import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import xyz.frame.pojo.common.ConfigKey;

/**
 * 读取configure.properties配置文件
 */
@Service
public class ConfigureProperties {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(ConfigureProperties.class);

	/**
	 * 配置项
	 */
	private static Properties properties = new Properties();

	/**
	 * 模式匹配 ${}
	 */
	private static final Pattern PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");

	// 初始化
	static {
		try {
			properties.load(new InputStreamReader(
					ConfigureProperties.class.getClassLoader().getResourceAsStream(ConfigKey.CONFIG_FILE), "UTF-8"));
		} catch (Exception e) {
			logger.error("读取配置文件" + ConfigKey.CONFIG_FILE + "失败！");
		}
	}

	/**
	 * 获取属性
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return getProperty(key, null);
	}

	/**
	 * 获取属性,如果没有则返回默认值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String key, String defaultValue) {

		String value = getPatternProperty(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		} else {
			return value;
		}
	}

	/**
	 * 获取配置文件，支持模式匹配 ${xxx}
	 * 
	 * @param key
	 * @return
	 */
	private static String getPatternProperty(String key) {
		String value = properties.getProperty(key);

		if (value == null || StringUtils.isBlank(value)) {
			return value;
		}

		Matcher matcher = PATTERN.matcher(value);
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			String matcherKey = matcher.group(1);
			String matchervalue = properties.getProperty(matcherKey);
			if (matchervalue != null) {
				matcher.appendReplacement(buffer, matchervalue);
			}
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}

}
