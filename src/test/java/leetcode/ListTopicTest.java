package leetcode;

import edu.emory.mathcs.backport.java.util.Arrays;
import edu.emory.mathcs.backport.java.util.Collections;
import leetcode.model.ListNode;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import static org.testng.Assert.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/1/26 18:33
 */
public class ListTopicTest {

    ListTopic listTopic;
    ListNode listNode;
    ListNode l1;
    ListNode l2;

    @BeforeMethod
    public void setUp() {
        listTopic = new ListTopic();
        listNode = new ListNode();
        l1 = listNode.constructList(Lists.newArrayList(2, 4, 3));
        l2 = listNode.constructList(Lists.newArrayList(5, 6, 4));
    }

    @Test
    public void testPrint() {
        listNode.print(l1);
        listNode.traversePrint(l1);
    }

    @Test
    public void testAddTwoNumbers() {
        ListNode node = listTopic.addTwoNumbers(l1, l2);
        listNode.printList(node);
    }

    @Test
    public void testReverse() {
        ListNode reverse = listTopic.reverse(l1);
        listNode.printList(reverse);
        System.out.println();
        ListNode reverse2 = listTopic.reverse2(reverse);
        listNode.printList(reverse2);
    }

    @Test
    public void testReverseN() {
        ListNode reverseN = listTopic.reverseN(l1, 2);
        listNode.printList(reverseN);
    }

    @Test
    public void testReverseBetween() {
        ListNode reverseBetween = listTopic.reverseBetween(l1, 2, 3);
        listNode.printList(reverseBetween);
    }

    @Test
    public void test() {
        ListNode node = this.listNode.constructList(Lists.newArrayList(1, 2, 2, 1));
        boolean palindrome = listTopic.isPalindrome(node);
        Assert.assertEquals(palindrome, true);

        ListNode node2 = this.listNode.constructList(Lists.newArrayList(1, 2, 3, 2, 1));
        boolean palindrome2 = listTopic.isPalindrome2(node2);
        Assert.assertEquals(palindrome2, true);
        listNode.printList(node2);
    }

}