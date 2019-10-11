package chapter_1;

/**
 * 用balance做锁对象不可以，因为解锁时balance就已经发生了变化，
 * 锁的本质是在对象的头上加上线程id，所以加锁就无效了
 * @author xiang.wei
 * @date 2019/10/11 11:05
 */
public class BadAccount {
    private Integer balance = 1000;

    private String password = null;

    /**
     * 取款
     */
    public void withdrow(Integer amt) {
        synchronized (balance) {
            if (balance > amt) {
                balance -= amt;
                System.out.println("*******扣除后的余额是="+balance);
            }
        }
    }

    /**
     * 查看余额
     * @return
     */
    public int getSBalance() {
        synchronized (balance) {
            System.out.println("******读取到的余额是="+balance);
            return balance;
        }
    }
}
