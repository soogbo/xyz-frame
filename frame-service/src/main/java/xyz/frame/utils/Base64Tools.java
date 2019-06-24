package xyz.frame.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.apache.commons.lang3.StringUtils;

public class Base64Tools {
    private Base64Tools(){}
    private static final String EMPTY = "";
    private static final String UTF_8 = "UTF-8";
    
    /**
     * java8-base64加密
     * @param text
     * @return
     */
    public static String encode(String text) {
        if (StringUtils.isBlank(text)) {
            return "";
        }
        final Base64.Encoder encoder = Base64.getEncoder();
        byte[] textByte =null;
        try {
            textByte = text.getBytes(UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoder.encodeToString(textByte);
    }
    
    /**
     * java8-base64解密
     * 
     * @param text
     * @return
     */
    public static String decode(String text) {
        if (StringUtils.isBlank(text)) {
            return EMPTY;
        }
        String result = EMPTY;
        try {
            final Base64.Decoder decoder = Base64.getDecoder();
            byte[] decodeByte = decoder.decode(text);
            result = new String(decodeByte, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 自实现base64加密
     * @param str
     * @return
     */
    public static String base64encode(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String base64encodechars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        StringBuilder out = new StringBuilder();
        
        int len = str.length();
        int i = 0;
        int c1;
        int c2;
        int c3;
        
        while (i < len) {
            c1 = str.charAt(i++) & 0xff;
            if (i == len) {
                out.append(base64encodechars.charAt(c1 >> 2));
                out.append(base64encodechars.charAt((c1 & 0x3) << 4));
                out.append("==");
                break;
            }
            
            c2 = str.charAt(i++);
            if (i == len) {
                out.append(base64encodechars.charAt(c1 >> 2));
                out.append(base64encodechars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xf0) >> 4)));
                out.append(base64encodechars.charAt((c2 & 0xf) << 2));
                out.append("=");
                break;
            }
            
            c3 = str.charAt(i++);
            out.append(base64encodechars.charAt(c1 >> 2));
            out.append(base64encodechars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xf0) >> 4)));
            out.append(base64encodechars.charAt(((c2 & 0xf) << 2) | ((c3 & 0xc0) >> 6)));
            out.append(base64encodechars.charAt(c3 & 0x3f));
        }
        return out.toString();
    }
    
    public static void main(String[] args) {
        String pre = "shisp";
        //自实现base64加密
        String base64encode = base64encode(pre);
        System.out.println("自实现base64加密:" + base64encode);
        //java8-base64加密
        String encode = encode(pre);
        System.out.println("java8-base64加密:" + base64encode);
        String decode = decode(encode);
        System.out.println("java8-base64解密:" + decode);
    }
}