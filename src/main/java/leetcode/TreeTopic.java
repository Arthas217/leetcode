package leetcode;

import leetcode.model.TreeNode;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 树
 * @Date 2021/1/26 21:12
 */
public class TreeTopic {

    /**
     * @return int
     * @Author 会游泳的蚂蚁
     * @Description 二叉树根节点到叶子节点的所有路径和
     * https://www.nowcoder.com/questionTerminal/185a87cd29eb42049132aed873273e83
     * @Date 2021/1/26 21:20
     * @Param root
     */
    public int sumNumbers(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return sum;
        }
        return sumNumbersHelp(root, sum);
    }

    /**
     * 先序遍历的思想(根左右)+数字求和(每一层都比上层和*10 + 当前根节点的值)
     */
    private int sumNumbersHelp(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return sumNumbersHelp(root.left, sum) + sumNumbersHelp(root.right, sum);
    }
}
