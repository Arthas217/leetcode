package leetcode;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 简单一致性hash实现
 * @Date 2021/1/29 10:57
 */
public class ConsistentHash<T> {

    private final MessageDigest messageDigest;
    private final int numberOfReplicas;
    private final SortedMap<BigInteger, T> circle = new TreeMap<>();

    // 自定义的哈希函数,在此处使用MD5哈希。
    public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            this.numberOfReplicas = numberOfReplicas;
            for (T node : nodes) {
                add(node);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // 添加虚拟节点到hash环(如node#1,node#2）
    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            BigInteger hash = new BigInteger(messageDigest.digest((node.toString() + i).getBytes()));
            System.out.println("add----node=" + node + "   hash=" + hash);
            circle.put(hash, node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            BigInteger hash = new BigInteger(messageDigest.digest((node.toString() + i).getBytes()));
            circle.remove(hash);
        }
    }

    // 查找对象key的节点
    public T get(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        BigInteger hash = new BigInteger(messageDigest.digest(key.getBytes()));
        System.out.println("key----" + key + "  hash=" + hash);
        if (!circle.containsKey(hash)) {
            SortedMap<BigInteger, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

}
