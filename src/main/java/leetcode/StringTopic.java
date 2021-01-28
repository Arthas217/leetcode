package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 字符串
 * @Date 2021/1/26 11:12
 */
public class StringTopic {

    /**
     * @return java.lang.String
     * @Author 会游泳的蚂蚁
     * @Description 415. 字符串相加，非负整数字符串str1和str2计算它们的和。
     * @Date 2021/1/25 20:51
     * @Param carry 保存进制值
     * 时间复杂度O(max(str1.len,str2.len)) ,Java解法使用了StringBuffer，它的空间复杂度为 O(n)
     */
    public String addStrings(String str1, String str2) {
        StringBuffer sb = new StringBuffer("");
        int i = str1.length() - 1;
        int j = str2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = str1.charAt(i) - '0';
            int n2 = str2.charAt(i) - '0';
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            sb.append(tmp % 10);
            i--;
            j--;
        }
        //处理最高位
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }


    /**
     * @return boolean
     * @Author 会游泳的蚂蚁
     * @Description 9.回文数 判断一个整数是否是回文数
     * @Date 2021/1/26 15:59
     * @Param x整数   不能使用额外空间,不能将整数转换为字符串进行判断
     */
    public boolean isPalindrome(int x) {
        // 将整数分成左右两部分，右边那部分需要转置，然后判断这两部分是否相等。
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int right = 0;
        while (x > right) {
            right = right * 10 + x % 10;
            x /= 10;
        }
        return x == right || x == right / 10;
    }

    /**
     * @return boolean
     * @Author 会游泳的蚂蚁
     * @Description 125. 验证回文串(双指针）
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * @Date 2021/1/26 16:30
     * @Param 字符串s
     */
    public boolean isPalindrome2(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            //字符是否是数字或字母
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * @return int
     * @Author 会游泳的蚂蚁
     * @Description 无重复字符的最长子串的长度 **
     * @Date 2021/1/26 17:28
     * @Param [s]
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        //滑动窗口机制
        //此map中，key=字符，value=字符index+1时开始不重复，start移动所需
        Map<Character, Integer> startMap = new HashMap<>();
        int start = 0;
        int result = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (startMap.containsKey(c)) {
                start = Math.max(start, startMap.get(c));
            }
            startMap.put(c, i + 1);
            result = Math.max(result, i - start + 1);
        }
        return result;
    }

    /**
     * @return java.lang.String
     * @Author 会游泳的蚂蚁
     * @Description 最长回文子串（双指针）**
     * 寻找回文串的问题核心思想是：从中间开始向两边扩散来判断回文串
     * https://juejin.cn/post/6844903901884317709
     * 时间复杂度 O(N^2)，空间复杂度 O(1)。
     * @Date 2021/1/28 14:02
     * @Param [s]
     */
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            //如果大于保存的最大回文长度，则计算并更新最大回文的位置
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }

        }
        return s.substring(start, end + 1);
    }

    // 回文串的长度可能是奇数也可能是偶数
    private int expandAroundCenter(String s, int l, int r) {
        // 防止索引越界
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

}
