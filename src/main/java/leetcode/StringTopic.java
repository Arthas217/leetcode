package leetcode;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/1/26 11:12
 */
public class StringTopic {

    /**
     * @return java.lang.String
     * @Author 会游泳的蚂蚁
     * @Description 415. 字符串相加，非负整数字符串str1和str2计算它们的和。
     * @Date 2021/1/25 20:51
     * @Param  carry 保存进制值
     * 时间复杂度O(max(str1.len,str2.len))
     * Java解法使用了StringBuffer，它的空间复杂度为 O(n)
     *
     */
    public static String addStrings(String str1, String str2) {
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
}
