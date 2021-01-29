package leetcode;

import com.beust.jcommander.internal.Lists;
import org.testng.annotations.Test;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/1/29 11:18
 */
public class ConsistentHashTest {

    @Test
    public void testSortedMap(){
        SortedMap<Integer, String> map = new TreeMap<>();
        map.put(2, "two");
        map.put(1, "one");
        map.put(3, "three");
        map.put(6, "six");
        map.put(5, "five");
        SortedMap<Integer, String> tailMap = map.tailMap(3);
        System.out.println(tailMap);
        System.out.println(tailMap.firstKey());
    }

    @Test
    public void testConsistentHash() {
        ConsistentHash<String> consistentHash = new ConsistentHash(3, Lists.newArrayList("192", "168", "101"));
        String str = consistentHash.get("123");
        System.out.println("返回节点为：" + str);
    }
}