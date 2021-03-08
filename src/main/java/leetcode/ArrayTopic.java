package leetcode;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/20 20:04
 */
public class ArrayTopic {


    /**
     * 35. 搜索插入位置（目标值不存在数组中，返回它将会被按顺序插入的位置）
     */
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid;
        while (l < r) {
            mid = (l + r) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        //l == r
        return nums[l] < target ? l + 1 : l;
    }

    public int searchInsert2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int searchInsert3(int[] arr, int left, int right) {
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            // 如果中间元素的值和下标不相等，并且它前面一个元素和它的下标不相等，只需要查找左半边
            if (arr[mid] == mid + 2) {
                right = mid - 1;
            } else if (arr[mid] == mid + 1) {
                // 如果中间元素的值和下标不相等，并且它前面一个元素和它的下标相等，中间的数字下标就是不存在的数字插入的位置
                left = mid + 1;
            }
        }
        // 如果中间元素的值和下标相等，只需要查找右半边；
        return left + 1;
    }

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

