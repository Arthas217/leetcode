package leetcode;

import leetcode.model.ListNode;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 链表
 * @Date 2021/1/26 18:10
 */
public class ListTopic {

    /**
     * 两数相加 **
     * 两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
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
     * 递归反转整个链表
     * 递归函数：以node为起点的链表反转，并返回反转之后的头结点。
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
     * 递归反转链表前 N 个节点, N<=链表⻓度
     * 递归函数：反转以node为起点的N个节点，返回新的头结点
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
     * 递归反转从位置m到n的链表。请使用一趟扫描完成反转, 1≤m≤n≤链表长度
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
     * 迭代反转整个链表
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


    /**
     * 判断是否为回文单链表  链表递归后续遍历思想
     * 算法的时间和空间复杂度都是 O(N)
     */
    ListNode left;

    public boolean isPalindrome(ListNode node) {
        left = node;
        return palindromeHelp(node);
    }

    private boolean palindromeHelp(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean result = palindromeHelp(right.next);
        // 后序遍历代码
        result = result && (right.val == left.val);
        left = left.next;
        return result;
    }

    /**
     * 判断是否为回文单链表(快慢双指针）
     * 算法的时间 O (N) 空间复杂度都是 O(1)
     */
    public boolean isPalindrome2(ListNode node) {
        ListNode slow = node;
        ListNode fast = node;
        // p、q、pre的使用都是为了保持输入链表的原始结构(也可以去掉）
        ListNode pre = null;
        ListNode p, q;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 链表⻓度为奇数
        if (fast != null) {
            p = slow;
            slow = slow.next;
        } else { //偶数
            p = pre;
        }
        //slow开始反转后面的链表
        ListNode left = node;
        ListNode right = reverse2(slow);
        q = right;
        while (right != null) {
            if (right.val != left.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        p.next = reverse2(q);
        return true;
    }


    /**
     * 160. 相交链表（找到两个单链表相交的起始节点）
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        return l1;
    }


}
