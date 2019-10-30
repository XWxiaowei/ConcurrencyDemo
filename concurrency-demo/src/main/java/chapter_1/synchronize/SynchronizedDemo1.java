package chapter_1.synchronize;

/**
 * getValue()方法和addOne()方法都是锁定实例对象this的。
 * 所以线程安全
 * @author xiang.wei
 * @date 2019/10/10 18:57
 */
public class SynchronizedDemo1 {
    int value = 0;

    synchronized int getValue() {
        return value;
    }

    synchronized void addOne() {
        value += 1;
    }

    public static void main(String[] args) {

    }
}
