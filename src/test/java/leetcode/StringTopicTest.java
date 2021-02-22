package leetcode;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 字符串测试
 * @Date 2021/1/26 11:43
 */
public class StringTopicTest {

    StringTopic stringTopic = new StringTopic();

    @Test(enabled = true)
    public void testAddStrings() {
        String s1 = "111";
        String s2 = "9999";
        String addStrings = stringTopic.addStrings(s1, s2);
        Assert.assertEquals(addStrings, "10110");
    }

    @Test
    public void testIsPalindrome() {
        int value = 12321;
        int value2 = 11;
        boolean result = stringTopic.isPalindrome(value);
        boolean result2 = stringTopic.isPalindrome(value2);
        Assert.assertEquals(result, true);
        Assert.assertEquals(result2, true);
    }

    @Test
    public void testIsPalindrome2() {
        String st = "A man, a plan, a canal: Panama";
        String st2 = "race a car";
        boolean result = stringTopic.isPalindrome2(st);
        boolean result2 = stringTopic.isPalindrome2(st2);
        Assert.assertEquals(result, true);
        Assert.assertEquals(result2, false);
    }

    @Test
    public void testLengthOfLongestSubstring() {
        String str = "abcabcbb";
        int l = stringTopic.lengthOfLongestSubstring(str);
        Assert.assertEquals(l, 3);
    }


    @Test
    public void testLongestPalindrome() {
//        String result = stringTopic.longestPalindrome("ababd");
        String result = stringTopic.longestPalindrome("abba");
        System.out.println(result);
    }

    @Test
    public void testStrMatch() {
        boolean match = stringTopic.StrMatch("abcda", "adabc");
        Assert.assertEquals(match, true);
    }

    @Test
    public void testIp() {
        //15个字节
        int value1 = stringTopic.ipv4ToInt("255.255.255.255");
        //7个字节
        int value2 = stringTopic.ipv4ToInt("1.1.1.1");
        //int只用4个字节
        System.out.println(value1);
        System.out.println(value2);
    }


    @Test
    public void testmultiply(){
        String num1 = "2";
        String num2 = "3";
        String multiply = stringTopic.multiply(num1, num2);
        System.out.println(multiply);
    }

}