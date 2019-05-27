package xyz.frame.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5工具类
 *
 * @author shisp
 * @date 2019-05-21
 */
public class Md5Utils {

    private Md5Utils(){
    }

    /**
     * md5加密
     *
     * @param source
     * @return 加密后的值
     */
    public static String encryptMd5(String source){
        return DigestUtils.md5Hex(source);
    }

    /**
     * md5校验
     *
     * @param source
     * @param md5Target
     * @return 校验结果
     */
    public static boolean verify(String source, String md5Target){
        return md5Target.equalsIgnoreCase(encryptMd5(source));
    }

}
