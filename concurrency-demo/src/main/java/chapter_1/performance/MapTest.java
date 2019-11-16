package chapter_1.performance;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiang.wei
 * @date 2019/11/16 17:29
 */
public class MapTest {

    private Map<Integer, String> map = new ConcurrentHashMap<>();

    private long starttime;

    private AtomicInteger count = new AtomicInteger(t_count);

    private final static int t_count = 5000;

    private final static int rw_count = 10000;

    Runnable readrun = new Runnable() {
        @Override
        public void run() {
            int i = rw_count;
            while (i > 0) {
                map.get(i);
                i--;
            }
            System.out.println("read-mapsize=" + map.size());
            if (count.decrementAndGet() == 0)
                System.out.println("time=" + (System.currentTimeMillis() - starttime + "ms"));
        }
    };

    Runnable writerun = new Runnable() {

        public void run() {
            int i = rw_count;
            while (i > 0) {
                map.put(i, i + "");
                i--;
            }
            System.out.println("write-mapsize=" + map.size());
            if (count.decrementAndGet() == 0)
                System.out.println("time=" + (System.currentTimeMillis() - starttime + "ms"));
        }
    };

    public void run() {
        starttime = System.currentTimeMillis();
        for (int i = 0; i < t_count / 2; i++) {
            new Thread(writerun).start();
            new Thread(readrun).start();
        }
    }

}
