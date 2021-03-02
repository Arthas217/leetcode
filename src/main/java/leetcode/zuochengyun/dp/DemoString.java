package leetcode.zuochengyun.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/25 09:17
 */
public class DemoString {
    /**
     * 打印字符串所有的子串（子串字符是连续）
     */
    public void print字符串子串(String str) {
        int len = str.length();
        // i位置开始的子串
        for (int i = 0; i < len; i++) {
            // j位置结束的子串
            for (int j = i; j < len; j++) {
                System.out.println(str.substring(i, j + 1));
            }
        }
    }

    /**
     * 子串不连续，要求相对次序不能乱
     */
    public List<String> print字符串子序列(String str) {
        char[] chars = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process1(chars, 0, ans, "");
        return ans;
    }

    /**
     * 暴力递归
     * index来到chars的位置，它到达终止位置时，把沿途经过的路径path加到结果ans中
     */
    private void process1(char[] chars, int index, List<String> ans, String path) {
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        String yes = path + chars[index];
        process1(chars, index + 1, ans, yes);
        String no = path;
        process1(chars, index + 1, ans, no);
    }


    public List<String> print字符串全排列(String str) {
        List<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] chars = str.toCharArray();
        process2(chars, 0, res);
        return res;
    }

    public void process2(char[] chars, int i, List<String> ans) {
        if (i == chars.length) {
            ans.add(String.valueOf(chars));
            return;
        }
        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            process2(chars, i + 1, ans);
            swap(chars, i, j);//交换完位置走其他决策时，需要恢复上一次状态
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp;
        temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        DemoString demo = new DemoString();
//        demo.print字符串子串("abcd");
//        System.out.println(demo.print字符串子序列("abc"));
        System.out.println(demo.print字符串全排列("abc"));

    }
}
