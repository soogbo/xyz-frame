package xyz.java.main.annotation;

/**
 * 测试自定义注解
 * 
 * @author shisp
 * @date 2018-4-11 11:05:10
 */
public class UseAnnotationDemo {

    public static void main(String[] args) {
        doJob();
    }
    
    @MongoLog(table = "logger_record_201804")
    private static void doJob() {
        System.out.println("使用注解日志记录，执行job进行中");
    }
}
