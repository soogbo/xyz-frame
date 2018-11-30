package xyz.java.main.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import xyz.frame.utils.SysLog;

/**
 * 反射获取注解demo
 * 
 * @author shisp
 * @date 2018-8-07 09:07:35
 */
@SysLog(table = "test")
public class ReflectAnnotation {

    private String filed;

    @SysLog(table = "method")
    private void method() {

    }

    public static void main(String[] args) {
        // 此处要用反射将字段中的注解解析出来
        Class<ReflectAnnotation> clz = ReflectAnnotation.class;
        // 判断类上是否有次注解
        boolean clzHasAnno = clz.isAnnotationPresent(SysLog.class);
        if (clzHasAnno) {
            // 获取类上的注解
            SysLog annotation = clz.getAnnotation(SysLog.class);
            // 输出注解上的属性
            String table = annotation.table();
            String version = annotation.version();
            System.out.println(clz.getName() + " table = " + table + ", version = " + version);
        }
        // 解析字段上是否有注解
        // ps：getDeclaredFields会返回类所有声明的字段，包括private、protected、public，但是不包括父类的
        // getFields:则会返回包括父类的所有的public字段，和getMethods()一样
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            boolean fieldHasAnno = field.isAnnotationPresent(SysLog.class);
            if (fieldHasAnno) {
                SysLog fieldAnno = field.getAnnotation(SysLog.class);
                // 输出注解属性
                String table = fieldAnno.table();
                String version = fieldAnno.version();
                System.out.println(clz.getName() + " table = " + table + ", version = " + version);
            }
        }
        // 解析方法上的注解
        Method[] methods = clz.getDeclaredMethods();
        for (Method method : methods) {
            boolean methodHasAnno = method.isAnnotationPresent(SysLog.class);
            if (methodHasAnno) {
                // 得到注解
                SysLog methodAnno = method.getAnnotation(SysLog.class);
                // 输出注解属性
                String table = methodAnno.table();
                String version = methodAnno.version();
                System.out.println(clz.getName() + " table = " + table + ", version = " + version);
            }
        }
    }
}