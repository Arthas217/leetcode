package leetcode;

import java.util.Stack;

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


    /**
     * @return
     * @Author 会游泳的蚂蚁
     * @Description 判定括号合法性
     * @Date 2021/2/19 10:51
     * @Param
     */
    boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else { // 字符 c 是右括号
                if (!stack.empty() && stack.peek().equals(revertChar(c))) {
                    stack.pop();
                } else {
                    // 和最近的左括号不匹配
                    return false;
                }
            }
        }
        return stack.empty();
    }

    private Character revertChar(char c) {
        if (c == '}') {
            return '{';
        }
        if (c == ')') {
            return '(';
        }
        return '[';
    }

}
