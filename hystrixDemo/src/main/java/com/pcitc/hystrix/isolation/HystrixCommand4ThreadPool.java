package com.pcitc.hystrix.isolation;

import com.netflix.hystrix.*;
import org.junit.jupiter.api.Test;
import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Hystrix 线程池方式实现线程隔离(将Tomcat线程处理的任务转交给Hystrix内部的线程去执行，这样Tomcat线程就可以去做其他事情。当Hystrix的线程将任务执行完成后，将执行结果返回给Tomcat线程。)
 * 降级逻辑运行在main线程中
 *
 *   fallback怎么执行的
 *     作用: 兜底数据\降级, 从其他的地方给你一个结果  , 比如redis.
 *     当无法获得可用的核心线程时, Hystrix直接在主线程中调用fallback()方法,执行降级逻辑.
 *
 * @author : pcitc
 * @since : 2019年04月13日 14:42
 */

public class HystrixCommand4ThreadPool extends HystrixCommand<String> {

    private final String name;

    public HystrixCommand4ThreadPool(String name) {
        super(Setter
                // 线程组名称
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("ThreadPoolGroup"))
                // 命令名称
                .andCommandKey(HystrixCommandKey.Factory.asKey("ThreadPoolCommandKey"))
                // 线程池名称
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey"))
                .andCommandPropertiesDefaults(
                        // 请求超时时间
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(3000)
                )
                .andThreadPoolPropertiesDefaults(
                        // 定义Hystrix线程池中线程数量
                        HystrixThreadPoolProperties.Setter().withCoreSize(3)
                )
        );
        this.name = name;
    }

    /***
     * 降级策略
     * @return
     */
    @Override
    protected String getFallback() {
        System.out.println(Thread.currentThread()+"Hi This is Fallback for name:"+this.name);
        return this.name;
    }

    @Override
    protected String run() throws Exception {
        TimeUnit.MILLISECONDS.sleep(800);
        System.out.println(Thread.currentThread()+" This is run in HystrixCommand , name :"+ this.name);
        return name;
    }

    public static class UnitTest{
        @Test
        public void testHystrixCommand4ThreadPool(){
            System.out.println("Thread.currentThread():"+Thread.currentThread());
            for (int i = 0; i < 10; i++) {
                try {
//                    // queue() 异步调用
//                    Future<String> queue = new HystrixCommand4ThreadPool("Thread " + i).queue();
//                    System.out.println(i+" - 干点别的");  // 在执行Hystrix任务的时候, 同时做其他任务的调度
//                    System.out.println("终于得到结果了："+queue.get(1, TimeUnit.SECONDS)); // 得到了线程执行的结果   等待结果的返回

                    // execute() 同步调用
//                    System.out.println("result"+new HystrixCommand4ThreadPool("Thread " + i).execute());

                    Observable<String> observe = new HystrixCommand4ThreadPool("Thread " + i).observe();
                    System.out.println("哈哈哈，怎么了，还没完成吗？ i="+ i);
                    // 订阅Observalbe
                    observe.subscribe(res -> {
                        System.out.println("得到结果："+res);
                    });
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