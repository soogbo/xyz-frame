package xyz.frame.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GracefulShutdownConfig
 * 
 * @author shisp
 * @date 2018-7-30 16:14:32
 */
@Configuration
public class GracefulShutdownConfig {

    @Bean
    public GracefulShutdownHook shutdownHook(@Autowired GracefulShutdownHook gracefulShutdownHook) {
        /*
         * 关闭挂钩:ShutdownHook
         * 
         * 钩子程序执行时机:当程序正常退出,系统调用System.exit方法或虚拟机被关闭时才会执行添加的shutdownHook线程。
         * 其中shutdownHook是一个已初始化但尚未启动的线程，
         * 当jvm关闭的时候，它会以某种未指定的顺序启动所有已注册的关闭挂钩(通过方法addShutdownHook添加的钩子)，并让它们同时运行。
         * 当系统执行完这些钩子后，jvm才会关闭。 所以可通过这些钩子在jvm关闭的时候进行内存清理、资源回收等工作。
         * 注意，关闭序列期间会继续运行守护线程，如果通过调用 exit方法来发起关闭序列，那么也会继续运行非守护线程。 一旦开始了关闭序列，则只能通过调用 halt
         * 方法来停止这个序列，此方法可强行终止虚拟机。
         */
        Runtime.getRuntime().addShutdownHook(gracefulShutdownHook);
        
        return gracefulShutdownHook;
    }

}
