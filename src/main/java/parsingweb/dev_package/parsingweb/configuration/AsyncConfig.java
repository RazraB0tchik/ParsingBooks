//package parsingweb.dev_package.parsingweb.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//
//@Configuration
//@EnableAsync
//public class AsyncConfig extends AsyncConfigurerSupport { //конфигурация для управления пулом потоков
//    @Override
//    public Executor getAsyncExecutor(){
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(4);
//        executor.setMaxPoolSize(10);
//        executor.setQueueCapacity(50);
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//
//    }
//}
