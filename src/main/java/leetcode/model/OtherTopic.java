package leetcode.model;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 杂样
 * @Date 2021/2/19 10:28
 */
public class OtherTopic {

    /**
     * @return int
     * @Author 会游泳的蚂蚁
     * @Description 接雨水问题 O(N) 时间 O(1) 空间  双指针解法
     * https://labuladong.gitbook.io/algo/gao-pin-mian-shi-xi-lie/jie-yu-shui
     * @Date 2021/2/19 10:29
     * @Param [height]
     */
    int trap(int[] height) {
        int len = height.length;
        if (len == 0) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int right = len - 1;
        int l_max = height[0];
        int r_max = height[len - 1];
        while (left <= right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);
            // height[i] 能够装的水只和较低的之差有关
            if (l_max < r_max) {
                res += l_max - height[left];
                left++;
            } else {
                res += r_max - height[right];
                right--;
            }
        }
        return res;
    }
}
