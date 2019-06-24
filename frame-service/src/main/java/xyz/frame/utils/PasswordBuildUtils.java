package xyz.frame.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

import xyz.frame.pojo.bo.PasewordBuildBo;


/**
 * 密码生成工具
 * @author shisp
 * @date 2018-3-05 18:29:13
 */
public class PasswordBuildUtils {

	public static String getPassword(PasewordBuildBo bo){
	    String date = bo.getDate();		
	    String domain = bo.getDomain();
	    String idcard = bo.getIdcard();
	    String name = bo.getName();
	    String tagnum = bo.getTagnum();
		
	    String password = null;
		String paselen = "";
            for (int i = 0; i < name.length(); i++) {
                paselen += Integer.toString(name.charAt(i),16);
            }
            for (int i = 0; i < domain.length(); i++) {
                paselen += Integer.toString(domain.charAt(i),16);
            }
            System.out.println(paselen);
            paselen = paselen.replaceAll("a", "10").replaceAll("b", "11").replaceAll("c", "12").replaceAll("d", "13").replaceAll("e", "14").replaceAll("f", "15");
            
            paselen += date.replaceAll("-", "");
            
            System.out.println(paselen);
            
            String regEx="[^0-9]";  
            Pattern p = Pattern.compile(regEx);  
            String md5Hex = DigestUtils.md5Hex(tagnum);
            Matcher m = p.matcher(md5Hex);  
            String trim = m.replaceAll("").trim();
            System.out.println( trim);
            paselen += trim;
            
            if (idcard!=null || idcard!="") {
                paselen += idcard;
            }
            
            Integer tmpSix = 0;
            int x = 0;
            int by = paselen.length()/6;
            for (int i = 0; i < by; i++) {
                
                String paselen2 = paselen.substring(x, x+6);
                x += 6;
                System.out.println(paselen2);
                tmpSix += Integer.parseInt(paselen2);
            }
            System.out.println(tmpSix);
            String end1 = "" + tmpSix/by;
            if (end1.length()<6) {
                end1 += tmpSix.toString().substring(0, 6-end1.length());
            }
            password = end1.substring(0, 6);
		return password;
	}
	
	public static void main(String[] args) {
	    /*Scanner scan = new Scanner(System.in);
        System.out.println("请输入注册环境：");
        String domain = scan.nextLine();*/
        PasewordBuildBo bo = new PasewordBuildBo();
        bo.setName("zhangsan");
        bo.setIdcard(null);
        bo.setDate("2018-12-01");
        bo.setTagnum("002195");
        bo.setDomain("baidu");
        System.out.println(getPassword(bo));
    }
	
}
