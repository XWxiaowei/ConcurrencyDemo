package chapter_3.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiang.wei
 * @date 2019/12/23 16:39
 */
public class ExtentedHashMapTest {
    public static void main(String[] args) {
        Map<Integer, String> extentedHashMap = new HashMap<>(2, 1);
        extentedHashMap.put(3, "测试3");
        extentedHashMap.put(7, "测试7");
        extentedHashMap.put(5, "测试5");
        for (Integer key : extentedHashMap.keySet()) {
            System.out.println("key=" + key + "value=" + extentedHashMap.get(key));
        }
    }
}
