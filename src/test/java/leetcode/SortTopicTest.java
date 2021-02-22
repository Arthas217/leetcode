package leetcode;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/22 10:18
 */
public class SortTopicTest {
    SortTopic sortTopic = new SortTopic();

    @Test
    public void testQuickSort() {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        sortTopic.quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
}