package chapter_1.deadlock;


import chapter_1.Account;
import chapter_1.BadAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author xiang.wei
 * @date 2019/10/11 9:42
 */
@RunWith(JUnit4.class)
public class BadAccountTest {


    @Test
    public void withdrow() throws Exception {
        final BadAccount account = new BadAccount();
        for (int i = 0; i < 1000; i++) {
            Thread threadA = new Thread(new Runnable() {
                public void run() {
                    account.withdrow(1);
                }
            }, "线程-1");

            Thread threadB = new Thread(new Runnable() {
                public void run() {
                    account.getSBalance();
                }
            }, "线程-2");
            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
        }
    }

}
