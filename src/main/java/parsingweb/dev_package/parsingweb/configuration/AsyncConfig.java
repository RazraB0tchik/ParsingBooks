package parsingweb.dev_package.parsingweb.configuration;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport { //конфигурация для управления пулом потоков
    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(5);
//        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsyncTaskThread::");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        System.out.println(executor.getCorePoolSize() + "active main throuble");
        System.out.println(executor.getMaxPoolSize() + " active Max throuble");
//        executor.setKeepAliveSeconds(60);
//        executor.setAllowCoreThreadTimeOut(true);
//        for (long i = 0; i < 1000; i++) {
//            executor.setScheduleAtFixedRate(new Runnable() {
//
//                @Override
//                public void run() {
//                }
//            }, 0, 1, TimeUnit.NANOSECONDS);
////        executor.setAwaitTerminationSeconds(10);
////        executor.setAllowCoreThreadTimeOut(false);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable ex, Method method, Object... params) {
                System.out.println("Exception: " + ex.getMessage());
                System.out.println("Method Name: " + method.getName());
                ex.printStackTrace();
            }
        };
    }


}
