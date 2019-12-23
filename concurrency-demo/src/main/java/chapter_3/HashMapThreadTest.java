package chapter_3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程操作死循环复现
 *
 * @author xiang.wei
 * @date 2019/12/21 15:40
 */
public class HashMapThreadTest {
    /**
     * 创建一个HashMap类变量，指定数组空间为2
     */
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        //控制并发原子操作
        final AtomicInteger at = new AtomicInteger(0);
        //并发计数器
        final CountDownLatch countDownLatch = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (at.get() < 10000) {
                        //向map中添加元素
                        map.put(at.get(), at.get());
                        at.incrementAndGet();
                    }
                    countDownLatch.countDown();
                }
            }).start();
            //阻塞当前主线程，等待4个线程全部执行完毕
            countDownLatch.await();
            for (Integer key : map.keySet()) {
                System.out.println("******key:"+key+",value:"+map.get(key));
            }

        }

    }
}
