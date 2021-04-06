package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

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

    /**
     * https://mp.weixin.qq.com/s/fCf5QbPDtE6SSlZ1yh_q8Q
     */
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
        return right + 1;
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

    public int searchInsert4(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            // 分别处理如下三种情况
            // 目标值在数组所有元素之前
            // 目标值等于数组中某一个元素
            // 目标值插入数组中的位置
            if (nums[i] >= target) {
                // 一旦发现大于或者等于target的num[i]，那么i就是我们要的结果
                return i;
            }
        }
        // 目标值在数组所有元素之后的情况
        return nums.length; // 如果target是最大的，或者nums为空，则返回nums的长度
    }


    /**
     * 27. 移除元素 (数组nums和一个值val,删除所有数值等于val的元素，并返回移除后数组的新长度)
     * 数组的元素在内存地址中是连续的，不能单独删除数组中的某个元素，只能覆盖
     */
    public int removeElement(int[] nums, int val) {
        // 暴力版本
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < size; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;
                size--;
            }
        }
        return size;
    }

    /**
     * [0,1,2,3,3,0,4,2]   2
     */
    public int removeElement2(int[] nums, int val) {
        //双指针
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                // slow向右移动
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }


    /**
     * 209. 长度最小的子数组
     * n 个正整数的数组和一个正整数 target
     * 满足其和 ≥ target 的长度最小的 连续子数组
     * 不存在符合条件的子数组，返回 0
     */
    public int minSubArrayLen(int target, int[] nums) {
        // 暴力法
        int sum = 0;
        int subLen = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    subLen = j - i + 1;
                    res = res > subLen ? subLen : res;
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int sum = 0;
        // 滑动窗口的长度
        int subLength = 0;
        int s = 0;
        for (int e = 0; e < nums.length; e++) {
            sum += nums[e];
            //窗口的起始位置s需要移动一位
            while (sum >= target) {
                subLength = e - s + 1;
                res = res > subLength ? subLength : res;
                // 窗口左侧缩小一位
                sum -= nums[s++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 59. 螺旋矩阵 II
     * 正整数n，生成一个包含1到n2所有元素，且元素按顺时针顺序螺旋排列的 nxn 正方形矩阵 matrix
     */
    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return null;
        }
        int lx = 0;
        int ly = 0;
        int rx = n - 1;
        int ry = n - 1;
        int[][] res = new int[n][n];
        while (lx <= rx && ly <= ry) {
            printCircleValue(lx++, ly++, rx--, ry--, res);
        }
        return res;
    }

    int num = 1;

    private void printCircleValue(int lx, int ly, int rx, int ry, int[][] res) {
        if (lx == rx) {
            for (int index = ly; index <= ry; index++) {
                res[lx][index++] = num++;
            }
        } else if (ly == ry) {
            for (int index = lx; index <= rx; index++) {
                res[index++][ry] = num++;
            }
        } else {
            int tempx = lx;
            int tempy = ly;
            while (tempy != ry) {  // 打印一圈矩阵的上行
                res[lx][tempy++] = num++;
            }
            while (tempx != rx) { // 打印一圈矩阵的右列
                res[tempx++][ry] = num++;
            }
            while (tempy != ly) { // 打印一圈矩阵的下行
                res[rx][tempy--] = num++;
            }
            while (tempx != lx) { // 打印一圈矩阵的左列
                res[tempx--][ly] = num++;
            }
        }
    }

    /**
     * 215. 数组中的第K个最大元素
     * 1) 快排一趟排序构建分界点p，p位置为倒数第k个下标时即可
     * 2) 建堆
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     */
    public int findKthLargest(int[] nums, int k) {
//        return quickSort(nums, 0, nums.length - 1, nums.length - k);
//        return minHeapHelp(nums, k);
//        return maxHeapHelp(nums, k);
        return heapHelp(nums, k);
    }

    SortTopic sortTopic = new SortTopic();

    private int quickSort(int[] arr, int l, int r, int index) {
        // 这里省略快速排序的代码，引用之前的部分
        int p = sortTopic.partition(arr, l, r);
        if (p == index) {
            return arr[p];
        } else {
            return p < index ? quickSort(arr, p + 1, r, index) : quickSort(arr, 1, p - 1, index);
        }
    }


    /**
     * 建立小顶堆
     */
    private int minHeapHelp(int[] nums, int k) {
        // 若小于或等于k，则继续添加；
        // 若大于k，此时需要将堆顶元素出队(保证每次在添加一个元素后，当前优先队列中始终保持的是已经遍历过的数组中前k大的元素)。
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            // 建堆过程
            minHeap.offer(nums[i]);
            if (minHeap.size() > k) {
                // 删除调整过程
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    /**
     * 建立大顶堆，删除k-1次
     */
    private int maxHeapHelp(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            // 建堆过程
            maxHeap.offer(nums[i]);
        }
        for (int i = 1; i < k; i++) {
            //删除调整过程
            maxHeap.poll();
        }
        return maxHeap.peek();
    }

    /**
     * 数据结构版本实现大顶堆
     */
    private int heapHelp(int[] nums, int k) {
        int heapSize = nums.length;
        // 建立堆
        buildMaxHeap(nums, heapSize);
        // 删除k-1次，每次要调整堆
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            // 根元素和最后一个元素互换
            swap(nums, 0, i);
            --heapSize;
            heapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    private void buildMaxHeap(int[] arr, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            heapify(arr, i, heapSize);
        }
    }

    private void heapify(int[] arr, int index, int heapSize) {
        // 获取左右结点的数组下标
        int l = index * 2 + 1;
        int r = index * 2 + 2;

        //  表示根结点、左结点、右结点中最大的值的结点的下标
        int largest = index;

        // 存在左结点，且左结点的值大于根结点的值
        if (l < heapSize && arr[l] > arr[index]) {
            largest = l;
        }

        // 存在右结点，且右结点的值大于以上比较的较大值
        if (r < heapSize && arr[r] > arr[largest]) {
            largest = r;
        }

        if (index == largest) {
            return;
        }
        // 交换此时根节点和左右结点中最大的那个值，把根节点的值替换下去
        swap(arr, index, largest);
        // 由于替换后左右子树会被影响，所以要对受影响的子树再进行heapify
        heapify(arr, largest, heapSize);
    }

    // 交换元素位置
    private void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}

