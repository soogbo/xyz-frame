package xyz.frame.json;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;

public class JacksonDateDeserializer extends DateDeserializer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String yearmmdd = "yyyy-MM-dd";
	private String yearmmddhh = "yyyy-MM-dd HH";
	private String yearmmddhhmm = "yyyy-MM-dd HH:mm";
	private String yearmmddhhmmss = "yyyy-MM-dd HH:mm:ss";
	
	@Override
	protected Date _parseDate(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String dateStr = jp.getValueAsString();
		if (dateStr == null || "".equals(dateStr)) {
			return null;
		}
		String dateFormat = yearmmddhhmmss;
		if (dateStr.length() == yearmmdd.length()) {
			dateFormat = yearmmdd;
		} else if (dateStr.length() == yearmmddhh.length()) {
			dateFormat = yearmmddhh;
		} else if (dateStr.length() == yearmmddhhmm.length()) {
			dateFormat = yearmmddhhmm;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {			 
			throw new FrameworkJsonException("日期格式转换异常:" + dateStr + "->"
					+ dateFormat, e);
		}
		return date;
	}
}
