package leetcode;

import leetcode.model.ListNode;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 链表
 * @Date 2021/1/26 18:10
 */
public class ListTopic {

    /**
     * @return leetcode.model.ListNode
     * @Author 会游泳的蚂蚁
     * @Description 两数相加 **
     * 两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * @Date 2021/1/26 18:12
     * @Param [l1, l2]
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 前导0节点
        ListNode init = new ListNode(0);
        // 当前位置
        ListNode cur = init;
        // 进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 考虑链表长度不同情况
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int sum = v1 + v2 + carry;
            carry = sum / 10;
            sum = sum % 10;
            //从最低位存新节点值
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //如果最高位进位为1，用carry填补一个新节点
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return init.next;
    }


    /**
     * @return
     * @Author 会游泳的蚂蚁
     * @Description 递归反转整个链表
     * 递归函数：以node为起点的链表反转，并返回反转之后的头结点。
     * @Date 2021/1/28 10:38
     * @Param
     */
    ListNode reverse(ListNode node) {
        // 递归函数 base case(而非边界的含义）
        if (node.next == null) {
            return node;
        }
        ListNode temp = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return temp;
    }

    /**
     * @return
     * @Author 会游泳的蚂蚁
     * @Description 递归反转链表前 N 个节点, N<=链表⻓度
     * 递归函数：反转以node为起点的N个节点，返回新的头结点
     * @Date 2021/1/28 10:49
     * @Param
     */
    ListNode back = null;

    ListNode reverseN(ListNode node, int n) {
        if (n == 1) {
            back = node.next;
            return node;
        }
        ListNode temp = reverseN(node.next, n - 1);
        node.next.next = node;
        node.next = back;
        return temp;
    }

    /**
     * @return
     * @Author 会游泳的蚂蚁
     * @Description 递归反转从位置m到n的链表。请使用一趟扫描完成反转, 1≤m≤n≤链表长度
     * @Date 2021/1/28 10:37
     * @Param
     */
    ListNode reverseBetween(ListNode node, int m, int n) {
        if (m == 1) {
            return reverseN(node, n);
        }
        node.next = reverseBetween(node.next, m - 1, n - 1);
        return node;
    }

    //递归操作链表并不高效。和迭代解法相比，虽然时间复杂度都是 O(N)，但是迭代解法的空间复杂度是 O(1)，而递归解法需要堆栈，空间复杂度是 O(N)。

    /**
     * @return
     * @Author 会游泳的蚂蚁
     * @Description //迭代反转整个链表
     * @Date 2021/1/28 11:15
     * @Param
     */
    ListNode reverse2(ListNode node) {
        ListNode pre = null;
        ListNode next;
        ListNode cur = node;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
