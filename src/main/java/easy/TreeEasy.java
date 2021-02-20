package easy;

import leetcode.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/20 12:27
 */
public class TreeEasy {

    // 144. 二叉树的前序遍历-递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderHelp(list, root);
        return list;
    }

    private void preorderHelp(List<Integer> list, TreeNode root) {
        if (root != null) {
            list.add(root.val);
            if (root.left != null) {
                preorderHelp(list, root.left);
            }
            if (root.right != null) {
                preorderHelp(list, root.right);
            }
        }
    }

    //前序遍历-递归
    public void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + "  ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }


    // 94-中序遍历-递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderHelp(list, root);
        return list;
    }

    private void inorderHelp(List<Integer> list, TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                inorderHelp(list, root.left);
            }
            list.add(root.val);
            if (root.right != null) {
                inorderHelp(list, root.right);
            }
        }
    }

    // 中序遍历 递归
    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + "  ");
            inOrder(root.right);
        }
    }


    // 145. 二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderHelp(list, root);
        return list;
    }

    private void postorderHelp(List<Integer> list, TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                postorderHelp(list, root.left);
            }
            if (root.right != null) {
                postorderHelp(list, root.right);
            }
            list.add(root.val);
        }
    }

    // 后序遍历 递归
    public void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.val + "  ");
        }
    }


    /**
     * 110. 平衡二叉树
     */
    boolean result = true;
    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        if (Math.abs(l - r) > 1){
            result = false;
        }
        return 1+ Math.max(l, r);
    }


}
