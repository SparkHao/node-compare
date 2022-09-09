package com.example.node_compare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: Spark
 * @CreateTime: 2022-08-09
 * @Description: TODO
 */
@Configuration
@EnableAsync
public class ThreadPool {

    @Bean("syncExecutorPool")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心池大小
        taskExecutor.setCorePoolSize(200);
        // 最大线程数
        taskExecutor.setMaxPoolSize(999);
        // 队列程度
        taskExecutor.setQueueCapacity(8888);
        // 线程空闲时间
        taskExecutor.setKeepAliveSeconds(60);
        // 线程前缀名称
        taskExecutor.setThreadNamePrefix("syncExecutor--");
        // 该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的 Bean，
        // 这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 任务的等待时间 如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        taskExecutor.setAwaitTerminationSeconds(2);
        // 线程不够用时由调用的线程处理该任务
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
