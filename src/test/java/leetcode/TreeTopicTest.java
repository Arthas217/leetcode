package leetcode;

import leetcode.model.TreeNode;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/1/26 21:31
 */
public class TreeTopicTest {

    TreeTopic treeTopic;
    TreeConstruct construct;

    @BeforeMethod
    public void setUp() {
        treeTopic = new TreeTopic();
        construct = new TreeConstruct();
    }

    @Test
    public void testSumNumbers() {
        TreeNode root = construct.init1();
        int sum = treeTopic.sumNumbers(root);
        Assert.assertEquals(sum, 25);
    }

    @Test
    public void testrob() {
        TreeNode root = construct.init1();
        int rob = treeTopic.rob(root);
        Assert.assertEquals(rob, 5);
    }
}