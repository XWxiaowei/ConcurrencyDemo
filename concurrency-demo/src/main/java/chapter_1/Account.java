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
   public void withdrow(Integer amt) {
        synchronized (balLock) {
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
        synchronized (balLock) {
            System.out.println("******读取到的余额是="+balance);
            return balance;
        }
    }

    /**
     * 更改密码
     * @param newPwd
     */
    public   void updatePwd(String newPwd) {
        synchronized (pwdLock) {
            password = newPwd;
        }
    }

    /**
     * 查看密码
     * @return
     */
    public  String getNewPwd() {
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
