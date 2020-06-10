package com.pcitc.hystrix.isolation;

import com.netflix.hystrix.*;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 信号量方式实现线程隔离(信号量的隔离只是起到一个开关作用。例如，服务X的信号量大小为10，那么同时之允许10个Tomcat线程来访问服务X，其他的请求将被拒绝，从而达到限流保护的作用。)
 *  * 任务执行线程都是main线程，所以实际只支持同步方式调用。
 *
 * @author : pcitc
 * @since : 2019年04月13日 15:42
 */

public class HystrixCommand4Semaphore extends HystrixCommand<String> {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private final String name;

    public HystrixCommand4Semaphore(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SemaphoreGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("SemaphoreKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("SemaphoreThreadPoolKey"))
                .andCommandPropertiesDefaults(
                        // 信号量隔离
                        HystrixCommandProperties.Setter().withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                                .withExecutionTimeoutInMilliseconds(3000)
                                // 配置信号量大小
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(3)
//                                // 配置降级并发量
//                                .withFallbackIsolationSemaphoreMaxConcurrentRequests(1)
                )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println(sdf.format(new Date())+","+Thread.currentThread()+" This is run in HystrixCommand , name :"+ this.name);
        return this.name;
    }

    @Override
    protected String getFallback() {
        System.out.println(sdf.format(new Date())+","+Thread.currentThread()+"Hi This is Fallback for name:"+this.name);
        return this.name;
    }


    public static class UnitTest{
        @Test
        public void testHystrixCommand4Semaphore(){
            for (int i = 0; i < 5; i++) {
                final int j = i;
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            new HystrixCommand4Semaphore("Thread "+j).execute();
                        }
                    }).start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
