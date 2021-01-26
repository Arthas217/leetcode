package leetcode;

import leetcode.model.TreeNode;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/1/26 21:36
 */
public class TreeConstruct {

    public TreeNode init1(){
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        return t1;
    }
}
