package chapter_1.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiang.wei
 * @date 2019/11/5 16:52
 */
public class FairLock {
    final Lock lock = new ReentrantLock(true);

}
