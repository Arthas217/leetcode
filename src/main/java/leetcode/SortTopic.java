package leetcode;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/20 20:18
 */
public class SortTopic {

    /**
     * 二分查找
     */
    public int search(int[] nums, int target) {
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
        return -1;
    }

    /**
     * 快排 o(n*logn)  最坏时间复杂度o(n*n)
     */
    public void quickSort(int[] arr) {
        helpSort(arr, 0, arr.length - 1);
    }

    private void helpSort(int[] arr, int left, int right) {
        //一趟排序通过交换元素构建分界点p
        int p = partition(arr, left, right);
        if (p < 0) {
            return;
        }
        //左右递归
        helpSort(arr, left, p - 1);
        helpSort(arr, p + 1, right);
    }

    public int partition(int[] arr, int left, int right) {
        if (left > right) {
            return -1;
        }
        //哨兵
        int sentinel = arr[left];
        int i = left, j = right;
        int t;
        while (i != j) {
            while (arr[j] >= sentinel && i < j) {
                j--;
            }
            while (arr[i] <= sentinel && i < j) {
                i++;
            }
            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        // i和j重合，最终将基准数归位
        arr[left] = arr[i];
        arr[i] = sentinel;
        return i;
    }


    // 归并排序 时间复杂度o(n*logn) 空间复杂度o(n)
    // 参考：https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653200029&idx=1&sn=51ecebafb9ff77baf3de71bdc4f67b78&chksm=8c99ec47bbee6551b0377b97e26670c4895d0c934051e4aa927e62bf9b64996b6e1f7459edfe&scene=21#wechat_redirect
    public void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 递归处理把问题都拆分为一个元素，或者是二个元素的子数组
            // 折半成两个小集合，分别进行递归
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            // 对各个拆分完的子数组进行归并成一个大集合
            merge(arr, left, mid, right);
        }// 类似后序遍历位置
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] tempArray = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        // 新数组位置
        int p = 0;
        // 比较两个小集合的元素，把较小的数先移到新数组中(依次放入大集合)
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] < arr[p2]) {
                tempArray[p++] = arr[p1++];
            } else {
                tempArray[p++] = arr[p2++];
            }
        }
        // 右边到边界，把左边剩余的数移入数组
        // 左侧小集合还有剩余，依次放入大集合尾部
        while (p1 <= mid) {
            tempArray[p++] = arr[p1++];
        }
        // 左边到边界，把右边剩余的数移入数组
        while (p2 <= right) {
            tempArray[p++] = arr[p2++];
        }
        // 把大集合tempArray的元素复制回原数组
        for (int i = 0; i < tempArray.length; i++) {
            // 注意arr数组下标以left为基准
            arr[left + i] = tempArray[i];
        }
    }


    // 希尔排序（对原始数组进行预处理，逐步分组进行粗调--希尔排序的增量--常用逐步折半的增量方法，再进行直接插入排序的思想）
    // https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653199674&idx=1&sn=9ab7bb7e465104c67a3d8590ebd0fe6c&chksm=8c99efe0bbee66f69c07e5f423d7751c9667fa82beb6dcaef4c0e96dac9545d2277c8179c765&scene=21#wechat_redirect
    public void shellSort(int[] arr) {
        int d = arr.length;
        while (d > 1) {
            //折半
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < arr.length; i = i + d) {
                    int insertVal = arr[i];
                    int j = i - d;
                    for (; j >= 0 && arr[j] > insertVal; j = j - d) {
                        arr[j + d] = arr[j];
                    }
                    arr[j + d] = insertVal;
                }
            }
        }
    }

}
