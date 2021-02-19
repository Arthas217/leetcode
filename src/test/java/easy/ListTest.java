package easy;

import leetcode.model.InitList;
import leetcode.model.ListNode;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/19 15:28
 */
public class ListTest {

    InitList initList;
    ListEasy list;

    @BeforeMethod
    public void setUp() {
        initList = new InitList();
        list = new ListEasy();
    }

    @Test
    public void testHasCycle() {
        boolean hasCycle = list.hasCycle(initList.init141());
        Assert.assertEquals(hasCycle, true);
    }

    @Test
    public void testmergeTwoLists() {
        ListNode l1 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l2 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(4);
        l1.next = l12;
        l2.next = l22;
        l22.next = l23;
        ListNode mergeTwoLists = list.mergeTwoLists(l1, l2);

    }
}