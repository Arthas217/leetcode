package leetcode.model;

import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:链表其实也可以有前序遍历和后序遍历
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

    /**
     * @Author
     * @Description 倒序打印单链表中的元素值(后序遍历）
     * @Date 2021/1/28 11:54
     * @Param [node]
     * @return void
     */
    public void traversePrint(ListNode node) {
        if (node == null) {
            return;
        }
        traversePrint(node.next);
        System.out.println(node.val + "\t");
    }

    /**
     * @Author 会游泳的蚂蚁
     * @Description 打印单链表中的元素值(先序遍历）
     * @Date 2021/1/28 11:58
     * @Param [node]
     * @return void
     */
    public void print(ListNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val + "\t");
        print(node.next);
    }


}
