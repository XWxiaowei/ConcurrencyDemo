package chapter_1;

/**
 * 存款取款和修改密码
 *
 * @author xiang.wei
 * @date 2019/10/10 19:21
 */
public class Account {
    //取款保护锁
    private final Object balLock = new Object();

    //密码保护锁
    private final Object pwdLock = new Object();

    private Integer balance = 100;

    private String password = null;

    /**
     * 取款
     */
    void withdrow(Integer amt) {
        synchronized (balLock) {
            if (balance > amt) {
                balance -= amt;
            }
        }
    }

    /**
     * 查看余额
     * @return
     */
    int getSBalance() {
        synchronized (balLock) {
            return balance;
        }
    }

    /**
     * 更改密码
     * @param newPwd
     */
    void updatePwd(String newPwd) {
        synchronized (pwdLock) {
            password = newPwd;
        }
    }

    /**
     * 查看密码
     * @return
     */
    String getNewPwd() {
        synchronized (pwdLock) {
            return password;
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
