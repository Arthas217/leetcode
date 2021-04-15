package leetcode;

import org.testng.annotations.Test;

import java.util.Arrays;

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

    @Test
    public void testMergeSort() {
        int arr[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        sortTopic.mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void testShellSort() {
        int[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        sortTopic.shellSort(array);
        System.out.println(Arrays.toString(array));
    }
}