package chapter_1.deadlock;

/**
 * @author xiang.wei
 * @date 2019/10/31 16:30
 */
public class DeadLock extends Thread {
    private Object tool = null;
    static Object chopsticks1 = new Object();
    static Object chopsticks2 = new Object();

    public DeadLock(Object chopsticks) {
        this.tool = chopsticks;
        if (chopsticks == chopsticks1) {
            this.setName("哲学家A");
        }
        if (chopsticks == chopsticks2) {
            this.setName("哲学家B");
        }
    }

    @Override
    public void run() {
        //哲学家获取到他左边的锁
        if (tool == chopsticks1) {
            synchronized (chopsticks1) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (chopsticks2) {
                    System.out.println("************哲学家A开始吃饭");
                }
            }
        }
        if (tool == chopsticks2) {
            synchronized (chopsticks2) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (chopsticks1) {
                System.out.println("************哲学家B开始吃饭");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock deadLockA = new DeadLock(chopsticks1);
        DeadLock deadLockB = new DeadLock(chopsticks2);
        deadLockA.start();
        deadLockB.start();
        Thread.sleep(20000);

    }
}
