package leetcode;

import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/20 20:37
 */
public class ArrayTopicTest {
    ArrayTopic arrayTopic;

    @BeforeMethod
    public void setUp() {
        arrayTopic = new ArrayTopic();
    }

    @Test
    public void testFindKthLargest() {
        int[] arr = {3, 2, 1, 5, 6, 4};
        int kthLargest = arrayTopic.findKthLargest(arr, 2);
        Assert.assertEquals(kthLargest, 5);
    }

    @Test
    public void testminSubArrayLen2() {
        int[] arr = {2, 3, 1, 2, 4, 3};
        int min = arrayTopic.minSubArrayLen2(7, arr);
        System.out.println(min);
    }
}