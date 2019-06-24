package xyz.frame.json;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class JacksonDateSerializer extends DateSerializer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(Date value, JsonGenerator gen,
			SerializerProvider provider) throws IOException {	
		String yearmmddhhmmss = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yearmmddhhmmss);
		gen.writeString(simpleDateFormat.format(value));
	}

}
