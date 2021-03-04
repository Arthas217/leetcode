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
//        TreeNode root = construct.init2();
//        int rob = treeTopic.rob3(root);
//        int robDp = treeTopic.robDp3(root);
//        System.out.println(rob);
//        System.out.println(robDp);


        int[] arr1 = {2, 1, 1, 2}; //4
        int[] arr2 = {2, 7, 9, 3, 1}; //12
        // 3176
        int[] arr3 = {104, 209, 137, 52, 158, 67, 213, 86, 141, 110, 151, 127, 238, 147, 169, 138, 240, 185, 246, 225, 147, 203, 83, 83, 131, 227, 54, 78, 165, 180, 214, 151, 111, 161, 233, 147, 124, 143};

        int rob1 = treeTopic.rob1(arr1);
        int rob2 = treeTopic.rob1(arr2);
        int rob3 = treeTopic.rob1(arr3);
        System.out.println(rob1);
        System.out.println(rob2);
        System.out.println(rob3);
    }
}