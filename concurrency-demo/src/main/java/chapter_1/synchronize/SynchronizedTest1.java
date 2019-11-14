package chapter_1.synchronize;

/**
 * getValue()方法和addOne()方法都是锁定实例对象this的。
 * 所以线程安全
 *
 * @author xiang.wei
 * @date 2019/10/10 18:57
 */
public class SynchronizedTest1 {
    int value = 0;

    synchronized int getValue() {
        return value;
    }

    synchronized void addThousand() {
        System.out.println("*********当前线程" + Thread.currentThread().getName()+"执行开始");
        for (int i = 0; i < 100000; i++) {
            value += 1;
        }
        System.out.println("*********当前线程" + Thread.currentThread().getName()+"执行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        final SynchronizedTest1 synchronizedTest1 = new SynchronizedTest1();
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                synchronizedTest1.addThousand();
            }
        }, "线程A");
        Thread threadB = new Thread(new Runnable() {
            public void run() {
                synchronizedTest1.addThousand();
            }
        }, "线程B");
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println("**********计算得到的结果="+synchronizedTest1.getValue());
    }
}
