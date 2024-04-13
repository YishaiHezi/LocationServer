//package com.example.demo.asyncProccessing;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//
//
//// todo: maybe delete this? this is an implementation for a thread pool.
//
//
//@Configuration
//@EnableAsync
//public class AsyncConfig {
//
//
//    /**
//     * The number of threads that will always be in the thread pool, even if there is no work.
//     */
//    private static final int CORE_POOL_SIZE = 5;
//
//
//    /**
//     * The maximum number of threads that can be in the thread pool.
//     */
//    private static final int MAX_POOL_SIZE = 10;
//
//
//    /**
//     * Represents the capacity of the task queue that holds tasks waiting to be executed when all threads are busy.
//     * Meaning that up to QUEUE_CAPACITY tasks can be queued in the task queue before the thread pool starts rejecting additional tasks.
//     */
//    private static final int QUEUE_CAPACITY = 20;
//
//
//    @Bean(name = "customThreadPool")
//    public Executor customThreadPool() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(CORE_POOL_SIZE);
//        executor.setMaxPoolSize(MAX_POOL_SIZE);
//        executor.setQueueCapacity(QUEUE_CAPACITY);
//        executor.setThreadNamePrefix("CustomThread-");
//        executor.initialize();
//        return executor;
//    }
//
//
//
//
//
//}
