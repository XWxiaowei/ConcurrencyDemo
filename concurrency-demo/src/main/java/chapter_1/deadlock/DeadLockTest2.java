package chapter_1.deadlock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("哲学家-pool-%d").build();
//        ExecutorService executorService = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(1024), nameThreadFactory, new ThreadPoolExecutor.AbortPolicy());
/**
 * 哲学家就餐模拟
 *
 * @author xiang.wei
 * @date 2019/10/31 17:12
 */
public class DeadLockTest2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int sum = 5;
        Chopsticks[] chopsticks = new Chopsticks[sum];
        for (int i = 0; i < sum; i++) {
            chopsticks[i] = new Chopsticks();
        }
        for (int i = 0; i < sum; i++) {
            executorService.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % sum]));
        }
    }
    /**
     * 筷子
     */
    static class Chopsticks {

    }
    /**
     * 哲学家
     */
    static class Philosopher implements Runnable {
        private Chopsticks left;
        private Chopsticks right;

        public Philosopher(Chopsticks left, Chopsticks right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            try {
                //思考一段时间
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (left) {
                try {
                    //拿到左边的筷子之后等待一段时间
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (right) {
                    try {
                        System.out.println("********开始吃饭");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
