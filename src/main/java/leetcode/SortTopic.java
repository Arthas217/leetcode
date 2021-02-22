package leetcode;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/20 20:18
 */
public class SortTopic {

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
}
