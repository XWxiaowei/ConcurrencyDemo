package chapter_1.happenbefore;

import chapter_1.Account;

/**
 * 保护有关联关系的多个资源
 *
 * @author xiang.wei
 * @date 2019/10/10 19:36
 */
public class Transfer {
    private Integer balance;

    /**
     * 问题，this 这把锁只能保护资源this.balance
     * 不能保护资源 target.balance
     * 就像不能用自家的锁保护别人家的资产。
     * 处理方式，使用一个共享锁，不通过都串行了。
     * @param target
     * @param amt
     */
    synchronized void transfer(Account target, int amt) {
        if (balance > amt) {
            this.balance = balance - amt;
            target.setBalance(target.getBalance() + amt);
        }
    }


    void transfer2(Account target, int amt) {
        synchronized (this) {
            if (balance > amt) {
                this.balance -= amt;
                synchronized (target) {
                    target.setBalance(target.getBalance() + amt);
                }
            }
        }
    }


}
