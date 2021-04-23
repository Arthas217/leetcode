package leetcode;

import leetcode.model.TreeNode;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/1/26 21:36
 */
public class TreeConstruct {

    public TreeNode init1() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        return t1;
    }

    public TreeNode init2() {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        t3.right = t5;
        return t1;
    }

    public TreeNode init() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(1);
        t1.right = t2;
        t1.left = t4;
        t2.left = t3;
        return t1;
    }

    public TreeNode init0(){
        TreeNode  t1 = new TreeNode(12);
        TreeNode  t2 = new TreeNode(5);
        TreeNode  t3 = new TreeNode(18);
        TreeNode  t4 = new TreeNode(2);
        TreeNode  t5 = new TreeNode(9);
        TreeNode  t6 = new TreeNode(15);
        TreeNode  t7 = new TreeNode(19);
        TreeNode  t8 = new TreeNode(17);
        TreeNode  t9 = new TreeNode(16);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        t6.right = t8;
        t8.left = t9;
        return t1;
    }


}
