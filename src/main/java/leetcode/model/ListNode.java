package leetcode.model;

import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/1/26 18:11
 */
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode() {
    }

    public ListNode(int value) {
        this.val = value;
    }


    public ListNode constructList(List<Integer> list) {
        ListNode init = new ListNode();
        ListNode cur = init;
        for (int value : list) {
            cur.next = new ListNode(value);
            cur = cur.next;
        }
        cur.next = null;
        return init.next;
    }

    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "\t");
            node = node.next;
        }
    }

}
