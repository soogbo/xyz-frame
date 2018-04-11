package xyz.java.main.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 解析自定义注解类demo
 * 
 * @author shisp
 * @date 2018-4-11 11:09:05
 */
public class ParseAnn {

    public static void main(String[] args) {
        testDemo1();
        testDemo2();
        testDemo3();
    }

    static void testDemo1() {
        try {
            // 使用类加载器加载类
            Class<?> c = Class.forName("xyz.java.main.annotation.UseAnnotationDemo");
            // 找到类上面的注解
            boolean isExist = c.isAnnotationPresent(MongoLog.class);
            // 上面的这个方法是用这个类来判断这个类是否存在Description这样的一个注解
            if (isExist) {
                // 拿到注解实例，解析类上面的注解
                MongoLog m = (MongoLog) c.getAnnotation(MongoLog.class);
                System.out.println("testDemo1:version:" + m.version() + ":table:" + m.table());
            } else {
                System.out.println("testDemo1:UseAnnotationDemo没有注解@MongoLog！！！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void testDemo2() {
        try {
            Class<?> c = Class.forName("xyz.java.main.annotation.UseAnnotationDemo");
            // 获取所有的方法
            // Method[] ms = c.getMethods();
            Method[] ms = c.getDeclaredMethods();
            // 1.遍历所有的方法
            for (Method m : ms) {
                boolean isExist1 = m.isAnnotationPresent(MongoLog.class);
                if (isExist1) {
                    // 拿到注解实例，解析类上面的注解
                    MongoLog a = (MongoLog) m.getAnnotation(MongoLog.class);
                    System.out.println("testDemo2:UseAnnotationDemo." + m.getName() + ":version:" + a.version() + ":table:" + a.table());
                } else {
                    System.out.println("testDemo2:UseAnnotationDemo." + m.getName() + "没有注解@MongoLog！！！");
                }
            }

            // 2.遍历每个方法的注解
            for (Method m : ms) {
                // 拿到方法上的所有的注解
                Annotation[] as = m.getAnnotations();
                for (Annotation a : as) {
                    // 用二元操作符判断a是否是Description的实例
                    if (a instanceof MongoLog) {
                        MongoLog d = (MongoLog) a;
                        System.out.println("testDemo2:UseAnnotationDemo." + m.getName() + ":version:" + d.version() + ":table:" + d.table());
                    } else {
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void testDemo3() {
        System.out.println("1.spring中需要日志管理的方法添加注解@MongoLog！");
        System.out.println("2.spring中通过自定义切点@Aspect拦截有@MongoLog注解的方法，进行日志管理！");

        // @Aspect
        // @Component
        // public class LogAopAction {
        // //本地异常日志记录对象
        // private static final Logger logger = LoggerFactory.getLogger(LogAopAction.class);
        // @Inject
        // private LogMapper logMapper;
        // //Controller层切点
        // @Pointcut("@annotation(com.annotation.SystemLog)")
        // public void controllerAspect() {
        // }
    }
}