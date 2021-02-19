package easy;

import leetcode.model.ListNode;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/19 15:26
 */
public class List {

    /**
     * 141 判断链表是否存在环
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
