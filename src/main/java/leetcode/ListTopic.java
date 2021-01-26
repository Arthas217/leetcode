package leetcode;

import leetcode.model.ListNode;

import javax.print.DocFlavor;

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
}
