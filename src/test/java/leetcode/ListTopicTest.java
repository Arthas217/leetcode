package leetcode;

import edu.emory.mathcs.backport.java.util.Arrays;
import edu.emory.mathcs.backport.java.util.Collections;
import leetcode.model.ListNode;
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

    @BeforeMethod
    public void setUp() {
        listTopic = new ListTopic();
        listNode = new ListNode();
    }

    @Test
    public void testAddTwoNumbers() {
        ListNode l1 = listNode.constructList(Lists.newArrayList(2, 4, 3));
        ListNode l2 = listNode.constructList(Lists.newArrayList(5, 6, 4));
        ListNode node = listTopic.addTwoNumbers(l1, l2);
        listNode.printList(node);
    }
}