package com.daikuan.collection.aspect;
import java.lang.reflect.Field;
 
 
/**
 * 属性（对象）值反射获取工具类
 * @author 周围
 * @date 2016-8-14
 */
public class ParamsReflect {
     
    public static final String FILE_FIELD = "nameB,";
 
    /**
     * 获取当前对象对应字段的属性（对象）
     * 声明，需要注意在NoSuchFieldException异常捕捉中捕获自己需要的属性字段进行拦截，告诉当查询这些属性名的时候，指定是查找的哪些对象，如果不告诉它，它是不知道的
     * @param obj    当前对象
     * @param field  需要获取的属性名，可以是当前对象中的属性名， 也可以是当前对象中的对象的属性名
     * @return    Object  当前对象指定属性值
     */
    public static Object getFieldValue(Object obj, String field) {
        Class<?> claz = obj.getClass();
        Field f = null;
        Object fieldValue = null;
        try {
            f = claz.getDeclaredField(field);
            f.setAccessible(true);
            fieldValue = f.get(obj);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            //此处异常捕获为：找不到属性名异常。
            //注意在此处我们要手工去帮它找到field应该对象到哪个对象里的值，因为我们不知道它们之间的关系，所以需要手工指定关系，找哪个对象去关联
            if(FILE_FIELD.indexOf(field) != -1) fieldValue = getCustomChildObj(obj, claz, B.class, field);
            else fieldValue = null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldValue;
    }
     
     
    /**
     * 获取自定义子属性对象，传入指定对象名，在当前对象中找到子对象，再通过field找到子属性
     * @param obj    父对象名称
     * @param claz   父对象class反射
     * @param customClass    自定义判断的子对象类型
     * @param field  属性名
     * @return Object
     */
    public static Object getCustomChildObj(Object obj, Class<?> claz, Class<?> customClass, String field) {
        Field[] fs = claz.getDeclaredFields();
        Field f = null;
        for (int i = 0; i < fs.length; i++) {
            f = fs[i];
            if(f.getType().equals(customClass)) {
                return getChildObjectParam(obj, f, field);
                //return claz.getDeclaredField(f.getName());
            }
        }
        return null;
    }
     
     
    /**
     * 通过找到的子对象，获取到当前的属性，传入所需的属性名，得到属性值
     * @param o  父对象
     * @param f  父对象下的子对象的Field对象
     * @param field  所需要获取的属性名
     * @return    Object
     */
    public static Object getChildObjectParam(Object o, Field f, String field)  {
        f.setAccessible(true);
        Object obj = null;
        Class<?> childClass = null;
        Field childF = null;
        Object fieldValue = null;
        try {
            obj = f.get(o);
            childClass = obj.getClass();
            childF = childClass.getDeclaredField(field);
            childF.setAccessible(true);
            fieldValue = childF.get(obj);
             
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return fieldValue;
    }
}