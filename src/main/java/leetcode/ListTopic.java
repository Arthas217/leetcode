package leetcode;

import leetcode.model.ListNode;

import java.util.List;

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
     * https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/shou-ba-shou-shua-lian-biao-ti-mu-xun-lian-di-gui-si-wei/di-gui-fan-zhuan-lian-biao-de-yi-bu-fen
     */
    ListNode reverseList(ListNode node) {
        // 递归函数 base case(而非边界的含义）
        if (node == null || node.next == null) {
            return node;
        }
        ListNode temp = reverseList(node.next);
        node.next.next = node;
        node.next = null;
        return temp;
    }

    /**
     * 递归反转链表前 N 个节点, N<=链表⻓度
     * 递归函数：反转以node为起点的N个节点，返回新的头结点
     */
    ListNode back = null; // 后驱节点

    ListNode reverseN(ListNode node, int n) {
        if (n == 1) {
            // 记录第n+1节点
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
     * 234. 回文链表
     * 判断是否为回文单链表(快慢双指针）
     * 算法的时间 O (N) 空间复杂度都是 O(1)
     * https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/shou-ba-shou-shua-lian-biao-ti-mu-xun-lian-di-gui-si-wei/pan-duan-hui-wen-lian-biao
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


    /**
     * 19. 删除链表的倒数第 N 个结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 24. 两两交换链表中的节点
     */
    public ListNode swapPairs(ListNode head) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        while (pre.next != null && pre.next.next != null) {
            ListNode l1 = pre.next;
            ListNode l2 = pre.next.next;
            ListNode next = l2.next;
            l1.next = next;
            l2.next = l1;
            pre.next = l2;
            pre = l1;
        }
        return node.next;
    }


    /**
     * 25. K个一组翻转链表(迭代）
     * 步骤：
     * 1 找到head开始的第k的节点
     * 2 head到tail前一个结点间翻转，并返回新节点
     * 3 再以tail为头结点递归
     * 4 递归结束后，将head.next指向翻转的新节点
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            // 剩余数量小于k的话，则不需要反转。
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        ListNode newHead = reverseHt(head, tail);// 翻转前k个元素
        // 递归反转后续链表并连接起来
        head.next = reverseKGroup(tail, k);
        return newHead;
    }

    /**
     * 反转区间 [head, tail) ，注意是左闭右开
     */
    private ListNode reverseHt(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != tail) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /**
     * 725. 分隔链表
     * 将链表分隔为 k 个连续的部分。
     * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1
     * 排在前面的部分的长度应该大于或等于后面的长度。
     * root = [1, 2, 3], k = 5
     * 输出: [[1],[2],[3],[],[]]
     * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
     * 输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        //计算链表长度
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            N++;
            cur = cur.next;
        }
        int mod = N % k;
        int size = N / k;
        cur = root;
        // 存储每部分的起始节点
        ListNode[] ret = new ListNode[k];
        for (int i = 0; i < k & cur != null; i++) {
            ret[i] = cur;
            // 计算每部分长度
            int curSize = size + (mod-- > 0 ? 1 : 0);
            for (int j = 0; j < curSize - 1; j++) {
                // 移动curSize-1步
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return ret;
    }


    /**
     * 328. 奇偶链表（节点编号的奇偶性）
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    /**
     * 143. 重排链表
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        // 找到原链表的中点
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        // 右半端反转
        l2 = reverseList(l2);
        // 将原链表的两端合并,两链表长度相差不超过1，因此直接合并即可
        mergeList(l1, l2);
    }

    /**
     * 求链表中点
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode ln1;
        ListNode ln2;
        while (l1 != null && l2 != null) {
            ln1 = l1.next;
            ln2 = l2.next;
            ln1.next = l2;
            l1 = ln1;
            ln2.next = l1;
            l2 = ln2;
        }
    }



}
