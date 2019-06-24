package xyz.frame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vdurmont.emoji.EmojiParser;

/**
 * emoji工具
 * 
 * @author shisp
 */
public class EmojiUtils {
	private static final Logger logger = LoggerFactory.getLogger(EmojiUtils.class);

	EmojiUtils() {
	}

	/**
	 * 移除字符串中所有emoji
	 * 
	 * @param str
	 * @return
	 */
	public static String removeAllEmojis(String str) {
		String result = str;
		if (null == str || str.length() == 0) {
			return result;
		}
		try {
			result = EmojiParser.removeAllEmojis(str);
		} catch (Exception e) {
			logger.warn("removeAllEmojis error e = {}", e);

		}
		return result;
	}

}
