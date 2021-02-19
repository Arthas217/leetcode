package leetcode;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/19 10:52
 */
public class OtherTopicTest {

    OtherTopic otherTopic;

    @BeforeMethod
    public void setUp() {
        otherTopic = new OtherTopic();
    }

    @Test
    public void testTrap() {
        int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int trap = otherTopic.trap(a);
        Assert.assertEquals(trap, 6);
    }

    @Test
    public void testIsValid() {
        boolean valid = otherTopic.isValid("[](){}");
        Assert.assertEquals(valid, true);
    }
}