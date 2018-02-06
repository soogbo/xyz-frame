package xyz.frame.utils;

import java.io.IOException;
import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class Base64Tools {
    public static String decode(String data) {
        try {        	
            data = new String(new BASE64Decoder().decodeBuffer(data));
            return data;
        } catch (IOException e) {
        }
        return null;
    }

    public static String decode(String data, String param) {
        try {
            data = new String(new BASE64Decoder().decodeBuffer(data));
            String[] strings = data.split("&");
            for (String string : strings) {
                String[] strings_ = string.split("=");
                if (param.equals(strings_[0])) {
                    if (strings_.length == 2) {
                        return strings_[1];
                    } else {
                        return "";
                    }
                }
            }
        } catch (IOException e) {
        }
        return null;
    }
}