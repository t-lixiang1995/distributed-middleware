package com.pcitc.hystrix.isolation.future;
/**
 * 假如你突然想做饭，但是没有厨具，也没有食材。网上购买厨具比较方便，食材去超市买更放心。
 * 实现分析：在快递员送厨具的期间，我们肯定不会闲着，可以去超市买食材。所以，在主线程里面另起一个子线程去网购厨具。
 * 调用join方法阻塞主线程是必须的，run方法不执行完，属性chuju就没有被赋值，还是null。换句话说，没有厨具，怎么做饭。Java现在的多线程机制，核心方法run是没有返回值的；如果要保存run方法里面的计算结果，必须等待run方法计算完，无论计算过程多么耗时。
 * @author pcitc
 *
 */
public class TestThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        // 第一步 网购厨具
        OnlineShopping thread = new OnlineShopping();
        thread.start();
        thread.join();  // 保证厨具送到
        // 第二步 去超市购买食材
        Thread.sleep(2000);  // 模拟购买食材时间
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");
        // 第三步 用厨具烹饪食材
        System.out.println("第三步：开始展现厨艺");
        cook(thread.chuju, shicai);
        
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }
    
    // 网购厨具线程
    static class OnlineShopping extends Thread {
        
        private Chuju chuju;

        @Override
        public void run() {
            System.out.println("第一步：下单");
            System.out.println("第一步：等待送货");
            try {
                Thread.sleep(5000);  // 模拟送货时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步：快递送到");
            chuju = new Chuju();
        }
        
    }

    //  用厨具烹饪食材
    static void cook(Chuju chuju, Shicai shicai) {}
    
    // 厨具类
    static class Chuju {}
    
    // 食材类
    static class Shicai {}
} 