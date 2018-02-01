package xyz.java.main.test;

import xyz.frame.util.idno.IdNo;
import xyz.frame.util.idno.IdentityNo;

/**
 * 身份证号码解析 性别 年龄 出生日期
 * 
 * @author shisp
 * @date 2018-2-01 17:19:44
 */
public class IdCardTest {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        
        IdentityNo idno = new IdentityNo("530381198711030471");
        // 性别
        if (idno.isMale()) {
            System.out.println("性别:"+IdNo.GENDER_MALE_STRING);
        } else {
            System.out.println(IdNo.GENDER_FEMALE_STRING);
        }
        // 年龄
        System.out.println("年龄:"+idno.getAge());
        // 出生日期
        System.out.println("出生日期:"+idno.getBirthDate().toLocaleString());
    }

}
