package leetcode.zuochengyun.字节跳动;

import leetcode.TreeTopic;
import leetcode.model.TreeNode;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/3/2 10:42
 */
public class Demo1 {

    /**
     * arr数组存的是BST后续遍历结果，求此树头结点
     */
    public TreeNode ArrayToBST(int[] arr) {
//        return arrayBSTHelp(arr, 0, arr.length - 1);
        return arrayBSTHelp2(arr, 0, arr.length - 1);
    }

    /**
     * 递归 O(N*N)
     */
    private TreeNode arrayBSTHelp(int[] arr, int l, int r) {
        if (l > r) {
            return null;
        }
        // root节点
        TreeNode root = new TreeNode(arr[r]);
        // 终止位置（无子树）
        if (l == r) {
            return root;
        }
//        // 1.< 2.> 3.<> 三种情况
//        int M = l - 1;
//        for (int i = l; i < r; i++) {
//            if (arr[i] < arr[r]) {
//                // [l,r)范围上找比root节点值小的节点位置
//                M = i;
//            }
//        }
//        root.left = arrayBSTHelp(arr, l, M);
//        root.right = arrayBSTHelp(arr, M + 1, r - 1);
//        return root;

        int M = -1;
        for (int i = l; i < r; i++) {
            if (arr[i] < arr[r]) {
                M = i;
            }
        }
        if (M == -1) {
            root.right = arrayBSTHelp(arr, l, r - 1);
        } else if (M == r - 1) {
            root.left = arrayBSTHelp(arr, l, r - 1);
        } else {
            root.left = arrayBSTHelp(arr, l, M);
            root.right = arrayBSTHelp(arr, M + 1, r - 1);
        }
        return root;
    }

    /**
     * 优化 O（logN * N)
     */
    private TreeNode arrayBSTHelp2(int[] arr, int l, int r) {
        if (l > r) {
            return null;
        }
        TreeNode root = new TreeNode(arr[r]);
        if (l == r) {
            return root;
        }
        int M = l - 1;
        // M赋值用二分方式
        int left = l;
        int right = r - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (arr[mid] < arr[r]) {
                M = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        root.left = arrayBSTHelp(arr, l, M);
        root.right = arrayBSTHelp(arr, M + 1, r - 1);
        return root;
    }


    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 6, 8, 7, 5};
        Demo1 demo1 = new Demo1();
        TreeTopic treeTopic = new TreeTopic();
        TreeNode treeNode = demo1.ArrayToBST(arr);
        System.out.println(treeTopic.levelOrder(treeNode));
    }
}
