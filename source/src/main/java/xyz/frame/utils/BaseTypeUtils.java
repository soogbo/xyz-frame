package xyz.frame.utils;

public class BaseTypeUtils {
	public static boolean isBaseType(Class<?> cls){
		boolean flag = false;
		if(     byte.class.equals(cls)||Byte.class.equals(cls)
				||short.class.equals(cls)||Short.class.equals(cls)
				||int.class.equals(cls)||Integer.class.equals(cls)				
				||long.class.equals(cls)||Long.class.equals(cls)
				||float.class.equals(cls)||Float.class.equals(cls)
				||double.class.equals(cls)||Double.class.equals(cls)
				||char.class.equals(cls)||Character.class.equals(cls)
				||boolean.class.equals(cls)||Boolean.class.equals(cls)				
				){
			flag = true;			
		}
		return flag;
	}
	public static Object string2BaseType(String str,Class<?> cls){
		Object result = null;
		if(byte.class.equals(cls)){
			result = string2Byte(str);
		}else if(Byte.class.equals(cls)){
			result = string2ByteObj(str);
		}else if(short.class.equals(cls)){
			result = string2Short(str);
		}else if(Short.class.equals(cls)){
			result = string2ShortObj(str);
		}else if(int.class.equals(cls)){
			result = string2Int(str);
		}else if(Integer.class.equals(cls)){
			result = string2Integer(str);
		}else if(long.class.equals(cls)){
			result = string2Long(str);
		}else if(Long.class.equals(cls)){
			result = string2LongObj(str);
		}else if(float.class.equals(cls)){
			result = string2Float(str);
		}else if(Float.class.equals(cls)){
			result = string2FloatObj(str);
		}else if(double.class.equals(cls)){
			result = string2Double(str);
		}else if(Double.class.equals(cls)){
			result = string2DoubleObj(str);
		}else if(char.class.equals(cls)){
			result = string2Char(str);
		}else if(Character.class.equals(cls)){
			result = string2Character(str);
		}else if(boolean.class.equals(cls)){
			result = string2Boolean(str);
		}else if(Boolean.class.equals(cls)){
			result = string2BooleanObj(str);
		}
		return result;
	}
	public static Byte string2ByteObj(String str){
		Byte result = null;
		if(str!=null&&!"".equals(str)){
			result = Byte.valueOf(str);
		}
		return result;
	}
	public static byte string2Byte(String str){
		byte result = 0;
		if(str!=null&&!"".equals(str)){			
			result = Byte.parseByte(str);
		}
		return result;
	}		
	public static Short string2ShortObj(String str){
		Short result = null;
		if(str!=null&&!"".equals(str)){
			result = Short.valueOf(str);
		}
		return result;
	}
	public static short string2Short(String str){
		short result = 0;
		if(str!=null&&!"".equals(str)){			
			result = Short.parseShort(str);
		}
		return result;
	}	
	public static Integer string2Integer(String str){
		Integer result = null;
		if(str!=null&&!"".equals(str)){
			result = Integer.valueOf(str);
		}
		return result;
	}
	public static int string2Int(String str){
		int result = 0;
		if(str!=null&&!"".equals(str)){			
			result = Integer.parseInt(str);
		}
		return result;
	}
	public static Long string2LongObj(String str){
		Long result = null;
		if(str!=null&&!"".equals(str)){
			result = Long.valueOf(str);
		}
		return result;
	}
	public static long string2Long(String str){
		long result = 0;
		if(str!=null&&!"".equals(str)){			
			result = Long.parseLong(str);
		}
		return result;
	}
	public static Float string2FloatObj(String str){
		Float result = null;
		if(str!=null&&!"".equals(str)){
			result = Float.valueOf(str);
		}
		return result;
	}
	public static float string2Float(String str){
		float result = 0;
		if(str!=null&&!"".equals(str)){			
			result = Float.parseFloat(str);
		}
		return result;
	}		
	public static Double string2DoubleObj(String str){
		Double result = null;
		if(str!=null&&!"".equals(str)){
			result = Double.valueOf(str);
		}
		return result;
	}
	public static double string2Double(String str){
		double result = 0;
		if(str!=null&&!"".equals(str)){			
			result = Double.parseDouble(str);
		}
		return result;
	}
	public static Character string2Character(String str){
		Character result = null;
		if(str!=null&&!"".equals(str)){			
			result = Character.valueOf(str.charAt(0));
		}
		return result;
	}
	public static char string2Char(String str){
		char result = '\0';
		if(str!=null&&!"".equals(str)){			
			result = str.charAt(0);
		}
		return result;
	}	
	public static boolean string2Boolean(String str){
		boolean result = false;
		if(str!=null&&!"".equals(str)){			
			result = Boolean.parseBoolean(str);
		}
		return result;
	}
	public static Boolean string2BooleanObj(String str){
		Boolean result = false;
		if(str!=null&&!"".equals(str)){			
			result = Boolean.valueOf(str);
		}
		return result;
	}	
}
