package xyz.dev.generator.main;
import java.lang.instrument.Instrumentation;
 
/**
 * 测试对象占用内存大小
 * 
 * @author shisp
 * @date 2018-8-08 14:14:40
 */
public class ObjectSize {
    private static volatile Instrumentation instru;
 
    public static void premain(String args, Instrumentation inst) {
        instru = inst;
    }
 
    public static Long getSizeOf(Object object) {
        if (instru == null) {
            throw new IllegalStateException("Instrumentation is null");
        }
        return instru.getObjectSize(object);
    }
}
