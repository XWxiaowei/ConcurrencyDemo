package chapter_1.notify;

/**
 * @author xiang.wei
 * @date 2019/11/14 19:48
 */
public class WaitAndNotifyTest1 {

    public static void main(String[] args) throws InterruptedException {
        MyThreadNotifyService myThreadNotifyService = new MyThreadNotifyService();
        MyThread myThread = new MyThread("线程A", myThreadNotifyService);
        myThread.start();
        myThreadNotifyService.signal();
    }

    static class MyThread extends Thread {
        MyThreadNotifyService myThreadNotifyService = null;

        public MyThread(String name, MyThreadNotifyService myThreadConditionService) {
            super(name);
            this.myThreadNotifyService = myThreadConditionService;
        }

        @Override
        public void run() {
            myThreadNotifyService.await();
        }
    }


    static class MyThreadNotifyService {

        public void await() {
            synchronized (this) {
                System.out.println("await的时间是=" + System.currentTimeMillis());
                //等待
                this.await();
                System.out.println("******这是await之后的代码，signal之后才会执行");
            }

        }

        public void signal() {
            synchronized (this) {
                System.out.println("signal的时间是=" + System.currentTimeMillis());
                this.notifyAll();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("**********这是signal之后的代码");
            }
        }

    }
}
