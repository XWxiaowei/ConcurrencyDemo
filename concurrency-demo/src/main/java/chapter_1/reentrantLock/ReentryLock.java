package chapter_1.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiang.wei
 * @date 2019/11/5 16:16
 */
public class ReentryLock {
    final Lock lock = new ReentrantLock();
    int value;

    public int getValue() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void setValue() {
        lock.lock();
        try {
            //在此处如果锁不能重入，则会发生阻塞。如果可以重入则可以加锁成功。
            value = getValue() + 100;
        } finally {
            lock.unlock();
        }

    }

}
