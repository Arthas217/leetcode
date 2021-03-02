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


    /**
     * 长度N的字符串str，长度M的字符串aim，（N>M)，
     * 求返回满足条件的str中连续子串的起始位置
     */
    public int containAim(String str, String aim) {
        if (str == null || aim == null || str.length() < aim.length()) {
            return -1;
        }
        char[] c1 = str.toCharArray();
        char[] c2 = aim.toCharArray();
        //枚举str子串，长度为aim=M的所有可能的起始位置  [0...N-M]
        for (int start = 0; start <= c1.length - c2.length; start++) {
            if (isCountTypeEqual(c1, start, c2)) {
                return start;
            }
        }
        return -1;
    }

    // 同源异构[种类、数目一样，顺序无关]字符串
    private boolean isCountTypeEqual(char[] c1, int start, char[] c2) {
        // 字符的ASCII码
        int[] count = new int[256];
        for (int i = 0; i < c2.length; i++) {
            count[c2[i]]++;
        }
        for (int i = 0; i < c2.length; i++) {
            if (count[c1[start + i]]-- == 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();

//        int[] arr = {2, 4, 3, 6, 8, 7, 5};
//        TreeTopic treeTopic = new TreeTopic();
//        TreeNode treeNode = demo1.ArrayToBST(arr);
//        System.out.println(treeTopic.levelOrder(treeNode));


        String aim = "abac";
        String str = "akcabbaabca";
        int index = demo1.containAim(str, aim);
        System.out.println(index);
    }
}
