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
        String s1 = "1111";
        String s2 = "9999";
        String addStrings = stringTopic.addStrings(s1, s2);
        Assert.assertEquals(addStrings, "11110");
    }

    @Test
    public void testIsPalindrome() {
        int value = 12321;
        int value2 =11;
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
    public void testLengthOfLongestSubstring(){
        String str = "abcabcbb";
        int l = stringTopic.lengthOfLongestSubstring(str);
        Assert.assertEquals(l,3);
    }
}