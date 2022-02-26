package com.example.demoasync;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Log4j2
@Configuration
@EnableAsync
public class AsyncConfiguration extends AsyncConfigurerSupport {

    @Bean   // /actuator/shutdown 호출시 종료되기 위해서 필요
    @Override
    public Executor getAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setTaskDecorator(new SomeTaskDecorator());
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);

        executor.setAllowCoreThreadTimeOut(false);
        executor.setKeepAliveSeconds(0);

        executor.setThreadNamePrefix("DefaultAsync-");

        executor.setWaitForTasksToCompleteOnShutdown(true); // 다 끝날때까지 대기

        executor.initialize();

        return executor;
    }

    static class SomeTaskDecorator implements TaskDecorator {


        @Override
        public Runnable decorate(Runnable runnable) {
            return () -> {
                try {
                    log.info("Some Task Decorator Start");
                    runnable.run();
                } finally {
                    log.info("Some Task Decorator End");
                }
            };
        }
    }
}
