import leetcode.model.TreeNode;

import java.math.BigInteger;
import java.util.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/9/14 10:04
 */
public class HuaWei {
    public static void main(String[] args) {

        // 计算面积 https://blog.csdn.net/yc_W_2017/article/details/107578294
        putong();
        gaojingdu();

        // 数组二叉树 给定一个数组存储的二叉树，试求从根节点到最小的叶子节点的路径，路径由节点的值组成。 https://zhuanlan.zhihu.com/p/344847063
        // 从数组构建一棵二叉树https://zhuanlan.zhihu.com/p/360824190
        // 二叉树所有路径https://leetcode-cn.com/problems/binary-tree-paths/
        pingcoubanben();
    }


    private static void pingcoubanben() {
        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split(" ");
        smallPath(findPath(construtTree(bianhuan(split))));
    }


    private static void putong() {
        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int E = Integer.parseInt(str[1]);
        int areaValue = 0;
        int initX = 0, initY = 0;
        // 每条指令x offset y
        for (int i = 0; i < N; i++) {
            String[] value = scanner.nextLine().split(" ");
            int x = Integer.parseInt(value[0]);
            int y = Integer.parseInt(value[1]);
            areaValue += (x - initX) * Math.abs(initY);
            initX = x;
            initY += y;
        }
        if (initX < E) {
            areaValue += (E - initX) * Math.abs(initY);
        }
        System.out.println(areaValue);
    }

    private static void gaojingdu() {
        Scanner scanner = new Scanner(System.in);
        String[] str = scanner.nextLine().split(" ");
        BigInteger N = new BigInteger(str[0]);
        BigInteger E = new BigInteger(str[1]);
        BigInteger areaValue = new BigInteger("0");
        BigInteger initX = new BigInteger("0");
        BigInteger initY = new BigInteger("0");
        // 每条指令x offset y
        for (int i = 0; i < N.longValue(); i++) {
            String[] value = scanner.nextLine().split(" ");
            BigInteger x = new BigInteger(value[0]);
            BigInteger y = new BigInteger(value[1]);
            BigInteger multiply = (x.subtract(initX)).multiply(initY.abs());
            areaValue = areaValue.add(multiply);
            initX = x;
            initY = initY.add(y);
        }
        if (initX.longValue() < E.longValue()) {
            BigInteger multiply = E.subtract(initX).multiply(initY.abs());
            areaValue = areaValue.add(multiply);
        }
        System.out.println(areaValue);
    }

    private static Integer[] bianhuan(String[] str) {
        Integer[] arr = new Integer[str.length];
        int i = 0;
        for (String s : str) {
            arr[i] = Integer.parseInt(s);
            i++;
        }
        return arr;
    }

    private static void smallPath(List<String> res) {
        int min = Integer.MAX_VALUE;
        String[] result = null;
        for (int i = 0; i < res.size(); i++) {
            String s = res.get(i);
            String[] s1 = s.split(" ");
            int value = Integer.parseInt(s1[s1.length - 1]);
            if (value < min && value != -1) {
                min = value;
                result = s1;
            }
        }
        Integer[] bianhuan = bianhuan(result);
        for (int a : bianhuan) {
            System.out.print(a + " ");

        }
    }

    private static List<String> findPath(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<String> pathQueue = new LinkedList<>();
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        pathQueue.add(root.val + "");
        nodeQueue.add(root);
        TreeNode node;
        String path;
        while (!nodeQueue.isEmpty()) {
            node = nodeQueue.poll();
            path = pathQueue.poll() + "";
            if (node.left == null && node.right == null) {
                res.add(path);
            }
            if (node.left != null) {
                nodeQueue.add(node.left);
                pathQueue.add(path + " " + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                pathQueue.add(path + " " + node.right.val);
            }
        }
        return res;
    }

    private static TreeNode construtTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }
        int len = arr.length;
        int index = 0;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode tNode;
        while (index < len) {
            index++;
            if (index >= len) {
                return root;
            }
            tNode = queue.poll();
            Integer leftChild = arr[index];
            if (leftChild != null) {
                tNode.left = new TreeNode(leftChild);
                queue.offer(tNode.left);
            }
            index++;
            if (index >= len) {
                return root;
            }
            Integer rightChild = arr[index];
            if (rightChild != null) {
                tNode.right = new TreeNode(rightChild);
                queue.offer(tNode.right);
            }
        }
        return root;
    }

}
