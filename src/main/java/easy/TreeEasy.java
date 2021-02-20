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
        if (Math.abs(l - r) > 1) {
            result = false;
        }
        return 1 + Math.max(l, r);
    }


    /**
     * 543. 二叉树的直径 （两节点的最长路径）
     * 是任意两个结点路径长度中的最大值，这条路径可能穿过也可能不穿过根结点
     */
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = depth(root.left);
        int r = depth(root.right);
        max = Math.max(max, l + r);
        return 1 + Math.max(l, r);
    }

    /**
     * 226. 翻转二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode left = root.left;
        // 此操作会改变 left 指针，因此先保存到变量left
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }


    /**
     * 617. 合并二叉树 时间复杂度：O(N) 空间复杂度O(N)
     * 合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为NULL的节点将直接作为新二叉树的节点。
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }


    /**
     * 572. 另一个树的子树
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return isSubtreeHelp(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSubtreeHelp(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (t.val != s.val) {
            return false;
        }
        return isSubtreeHelp(s.left, t.left) && isSubtreeHelp(s.right, t.right);
    }


    /**
     * 101. 对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return symmetricHelp(root.left, root.right);
    }

    public boolean symmetricHelp(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return symmetricHelp(left.left, right.right) && symmetricHelp(left.right, right.left);
    }

    /*
     * 687. 最长同值路径
     * 相同节点值的最大路径长度
     * 1. 路径长度：指节点值相同的节点组成的路径中边的个数
     * 2. 可以经过根节点，也可以不经过
     */
    private int path = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return path;
    }

    //递归函数，返回以该节点为根节点，向下走的最长同值路径
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);
        int r = dfs(root.right);
        // 如果该节点的值等于其左子树的值，则最长同值路径要加上左子树的最长同值路径，
        int lpath = root.left != null && root.left.val == root.val ? l + 1 : 0;
        int rpath = root.right != null && root.right.val == root.val ? r + 1 : 0;
        path = Math.max(path, lpath + rpath);
        return Math.max(lpath, rpath);
    }


}
