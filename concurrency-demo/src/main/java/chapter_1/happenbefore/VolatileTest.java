package chapter_1.happenbefore;

/**
 * @author xiang.wei
 * @date 2019/10/29 11:11
 */
public class VolatileTest {
    private volatile  boolean flag = false;
    private   int a = 0;

    public void writer() {
        a = 43;
        flag = true;
    }
    public void read() {
        if (flag) {
            System.out.println("读取到的a值是="+a);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileTest volatileTest = new VolatileTest();
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                volatileTest.writer();
            }
        });
        threadA.start();
        Thread threadB = new Thread(new Runnable() {
            public void run() {
                volatileTest.read();
            }
        });
        threadB.start();

    }
}
