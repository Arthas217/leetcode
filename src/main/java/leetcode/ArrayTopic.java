package leetcode;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/20 20:04
 */
public class ArrayTopic {

    SortTopic sortTopic = new SortTopic();

    /**
     * 215. 数组中的第K个最大元素
     * 1) 快排一趟排序构建分界点p，p位置为倒数第k个下标时即可
     * 2) 建大顶堆，删除k-1次(todo)
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSort(int[] arr, int l, int r, int index) {
        int p = sortTopic.partition(arr, l, r);
        if (p == index) {
            return arr[p];
        } else {
            return p < index ? quickSort(arr, p + 1, r, index) : quickSort(arr, 1, p - 1, index);
        }
    }

}

