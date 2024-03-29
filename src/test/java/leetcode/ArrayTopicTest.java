package leetcode;

import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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

    @Test
    public void testMatrix() {
        int[][] res = arrayTopic.generateMatrix(4);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + "\t");
            }
        }
    }

    @Test
    public void testFindSubsequences() {
        int[] nums = {4, 7, 6, 7};
        List<List<Integer>> list = arrayTopic.findSubsequences(nums);
        System.out.println(list);
    }

    @Test
    public void testgetNumberOfK(){
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};
//        int[] arr = {1, 1, 1, 1, 1, 1, 1, 1};
        int numberOfK = arrayTopic.getNumberOfK(arr, 3);
        System.out.println(numberOfK);
    }
}