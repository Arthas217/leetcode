package easy;

import leetcode.model.ListNode;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/19 15:26
 */
public class ListEasy {

    /**
     * 141 判断链表是否存在环
     */
    public boolean hasCycle(ListNode head) {
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


    /**
     * 21. 合并两个有序链表
     * 时间复杂度：O(n+m)
     * 空间复杂度：O(1)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode init = new ListNode(-1);
        ListNode temp = init;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        // 合并后l1和l2最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        temp.next = l1 == null ? l2 : l1;
        return init.next;
    }


    /**
     * 83. 删除排序链表中的重复元素
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
