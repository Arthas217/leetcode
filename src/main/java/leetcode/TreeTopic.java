package leetcode;

import leetcode.model.ListNode;
import leetcode.model.TreeNode;

import java.util.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 树
 * @Date 2021/1/26 21:12
 */
public class TreeTopic {

    /**
     * 144. 二叉树的前序遍历-非递归
     */
    public void preOrder(TreeNode root) {
        // LinkedList是一个双向链表
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        // 终止条件节点为null && 栈为空
        while (pNode != null || !stack.isEmpty()) {
            // 1 当前节点非空，打印值，元素进栈，访问左孩子
            // 2 如果左孩子非空，继续执行 1
            // 3 如果左孩子为空，元素出栈、访问右孩子 继续执行 1
            if (pNode != null) {
                System.out.print(pNode.val + "  ");
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                pNode = node.right;
            }
        }
    }

    /**
     * 94-中序遍历-非递归
     */
    public void inOrder(TreeNode root) {
        // 利用栈
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        // 循环结束条件是节点为空 && 栈中无元素
        while (pNode != null || !stack.isEmpty()) {
            // 1 当前节点非空，元素进栈，访问左孩子
            // 2 如果左孩子非空，继续执行 1
            // 3 如果左孩子为空，元素出栈、打印值、访问右孩子 继续执行 1
            if (pNode != null) {
                // 压入栈，如果左孩子非空，继续执行
                stack.push(pNode);
                pNode = pNode.left;
            } else { // 如果左孩子为空，元素出栈、访问右孩子
                TreeNode node = stack.pop();
                System.out.print(node.val + "  ");
                pNode = node.right;
            }
        }
    }

    // 145. 二叉树的后序遍历 双栈方法(最容易理解）
    public List<Integer> postorder(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        // 栈1 存前序 中左右。
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode t = stack1.pop();
            stack2.push(t);
            //栈2存顺序将其转化成中右左。压栈优先压入左子树。
            if (t.left != null) {
                stack1.push(t.left);
            }
            if (t.right != null) {
                stack1.push(t.right);
            }
        }
        // 倒序打印结果  左右中。
        while (!stack2.isEmpty()) {
            ret.add(stack2.pop().val);
        }
        return ret;
    }


    // 102. 二叉树的层序遍历 BFS 迭代实现  时间复杂度：O(n)  空间复杂度：O(n)
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode treeNode = queue.poll();
                temp.add(treeNode.val);
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
            result.add(temp);
        }
        return result;
    }

    // 102. 二叉树的层序遍历-DFS递归
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        //用来存放最终结果
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelOrderDFS(root, res, 1);
        return res;
    }

    private void levelOrderDFS(TreeNode root, List<List<Integer>> res, int index) {
        // 插入一个空list放到res中
        if (index > res.size()) {
            res.add(new ArrayList<>());
        }
        // 将当前节点的值加入到res中,index代表当前层
        res.get(index - 1).add(root.val);
        // 递归的处理左子树，右子树，同时将层数index+1
        if (root.left != null) {
            levelOrderDFS(root.left, res, index + 1);
        }
        if (root.right != null) {
            levelOrderDFS(root.right, res, index + 1);
        }
    }


    /**
     * 深度遍历-递归
     */
    List<Integer> depResult = new ArrayList<>();

    public List<Integer> depthOrder(TreeNode root) {
        if (root == null) {
            return depResult;
        }
        depResult.add(root.val);
        depthOrder(root.left);
        depthOrder(root.right);
        return depResult;
    }

    /**
     * 深度遍历-非递归
     */
    public List<Integer> depthOrder1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            // 注意入栈顺序和前序顺序
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * 437. 路径总和 III
     * 路径方向必须是向下的（只能从父节点到子节点）
     * 找出路径和等于sum的路径总数
     * 注意count不能作为dfs函数参数。因为不是以根节点为起始点。
     */
    private int count = 0;

    public int pathSum(TreeNode root, int sum) {
        // 双重DFS: 先序递归遍历每个节点;
        if (root == null) {
            return count;
        }
        pathSumDFS(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return count;
    }

    // 以每个节点作为起始节点DFS寻找满足条件的路径.
    private void pathSumDFS(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        sum -= root.val;
        if (sum == 0) {
            count++;
        }
        pathSumDFS(root.left, sum);
        pathSumDFS(root.right, sum);
    }


    /**
     * 111. 二叉树的最小深度
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量
     * 叶子节点的深度为1
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
        if (root.left == null && root.right == null) {
            return 1;
        }
        //2.如果左孩子和右孩子其中一个为空，返回比较大的那个孩子的深度
        int l = minDepth(root.left);
        int r = minDepth(root.right);
        if (l == 0 || r == 0) {
            return l + r + 1;
        }
        //3.左右孩子都不为空，返回最小深度+1
        return Math.min(l, r) + 1;

    }

    /**
     * 404. 左叶子之和
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 判断节点是否是左叶子节点
        if (isLeaf(root.left)) {
            // 递归找其他的左孩子
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        // 非叶子节点 左子树和右子树都要递归处理。
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private boolean isLeaf(TreeNode root) {
        if (root == null) {
            return false;
        }
        return root.left == null && root.right == null;
    }


    /**
     * 二叉树根节点到叶子节点的所有路径和
     * https://www.nowcoder.com/questionTerminal/185a87cd29eb42049132aed873273e83
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

    /**
     * 198. 打家劫舍（非负整数数组金额，相邻的房屋报警，偷窃到的最高金额）
     */
    public int rob1(int[] nums) {
        int len = nums.length;
        if (nums == null || len == 0) {
            return 0;
        }
//        return rob1Help(nums, len - 1);
        return robDp1(nums);
    }

    /**
     * 记忆化搜索+递归（LeetCode时间超出限制）
     */
    private int rob1Help(int[] nums, int index) {
        Map<Integer, Integer> cache = new HashMap<>();
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        int res = 0;
        if (index == 0) {
            res = nums[0];
        } else if (index == 1) {
            res = Math.max(nums[0], nums[1]);
        } else {
            res = Math.max(rob1Help(nums, index - 1), rob1Help(nums, index - 2) + nums[index]);
        }
        cache.put(index, res);
        return res;
    }

    public int robDp1(int[] nums) {
        int len = nums.length;
        if (nums == null || len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[len - 1];
        }
        // 递推函数dp[i]含义： 从0到i位置获得的最大金额
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }

    /**
     * 213. 打家劫舍 II (存放金额的非负整数数组, 围成一圈,相邻的房屋报警，偷窃到的最高金额)
     * https://mp.weixin.qq.com/s/kKPx4HpH3RArbRcxAVHbeQ
     */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // 包含首元素，不包含尾元素
        int result1 = robRange(nums, 0, nums.length - 1 - 1);
        // 包含尾元素，不包含首元素
        int result2 = robRange(nums, 1, nums.length - 1);
        return Math.max(result1, result2);
    }

    /**
     * 类似方法robDp1逻辑
     */
    private int robRange(int[] nums, int start, int end) {
        if (end == start) {
            return nums[start];
        }
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[end];
    }


    /**
     * 337. 打家劫舍 III  (一棵二叉树,只有一个入口根节点，相邻的房屋报警，偷窃到的最高金额)  间隔遍历
     */

    public int rob3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return rob3Help(root);
    }

    Map<TreeNode, Integer> robMap = new HashMap<>();

    /**
     * //（递归+ 哈希表）
     * https://leetcode-cn.com/problems/house-robber-iii/solution/shu-xing-dp-ru-men-wen-ti-by-liweiwei1419/
     */
    private int rob3Help(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (robMap.containsKey(root)) {
            return robMap.get(root);
        }
        //抢劫当前root
        int robValue = root.val;
        if (root.left != null) {
            robValue += rob3(root.left.left) + rob3(root.left.right);
        }
        if (root.right != null) {
            robValue += rob3(root.right.left) + rob3(root.right.right);
        }
        //不抢劫当前root
        int noRobValue = rob3(root.left) + rob3(root.right);
        robMap.put(root, Math.max(robValue, noRobValue));
        return robMap.get(root);
    }

    /**
     * https://leetcode-cn.com/problems/house-robber-iii/solution/337-da-jia-jie-she-iiidong-tai-gui-hua-x-8t1e/
     */
    public int robDp3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 有两个变量共同决定一个状态：1、代表不同子树的 root 节点、2、是否打劫了 root。
        // dp[node][j] ：node表示一个结点，以node为根结点的树，并且规定node是否偷取能够获得的最大价值。
        // j = 0 表示 node 结点不偷取；j = 1 表示 node 结点偷取。
        // 在根结点的时候，返回两个状态的较大者。
        // 但对象不能作为数组索引, 用res一维数组存放两个状态。root为根结点的子树能够偷取的最大价值
        int[] res = processRob(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * 所谓树形DP就是在树上进行递归公式的推导。
     */
    private int[] processRob(TreeNode root) {
        // 终止条件
        if (root == null) {
            return new int[2];
        }
        // 后序遍历是因为通过递归函数的返回值来做下一步计算。
        // root左右子树状态（包括不取、取）
        int[] left = processRob(root.left);
        int[] right = processRob(root.right);
        int[] dp = new int[2];
        // 如果不偷当前节点，那么左右孩子就可以偷，至于到底偷不偷一定是选一个最大的
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 如果偷当前节点，则那么左右孩子就不能偷
        dp[1] = root.val + left[0] + right[0];
        return dp;
    }


    /**
     * 671. 二叉树中第二小的节点（递归）如果有子节点时，父节点的值等于两个子节点中较小的一个。
     */
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null || root.right == null) {
            return -1;
        }
        int left = root.left.val;
        int right = root.right.val;
        //若根节点和左节点值相同，则递归找左子树的第二小的节点
        if (root.val == root.left.val) {
            left = findSecondMinimumValue(root.left);
        }
        //若根节点和右节点值相同，则递归找右子树的第二小的节点
        if (root.val == root.right.val) {
            right = findSecondMinimumValue(root.right);
        }
        //root，left,right值一样， 第二小的值不存在的话，输出 -1
        if (root.val == left && root.val == right) {
            return -1;
        }
        int lr_min = Math.min(left, right);
        return root.val < lr_min ? lr_min : Math.max(left, right);
    }


    /**
     * 513. 找树左下角的值 (在树的最后一行找到最左边的值) **
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 注意是root，不是临时节点
            root = queue.poll();
            // 必须是先右后左
            if (root.right != null) {
                queue.add(root.right);
            }
            if (root.left != null) {
                queue.add(root.left);
            }
        }
        return root.val;
    }


    /**
     * 230. 二叉搜索树BST中第K小的元素
     */
    public int kthSmallest(TreeNode root, int k) {
        if (k < 1 || root == null) {
            return -1;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        List<Integer> arrayList = new ArrayList<>();
        while (temp != null || !stack.isEmpty()) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.left;
            } else {
                TreeNode treeNode = stack.pop();
                arrayList.add(treeNode.val);
                // 注意temp
                temp = treeNode.right;
            }
            if (arrayList.size() == k) {
                return arrayList.get(k - 1);
            }
        }
        return -1;
    }


    int cnt = 0;
    int ksmallValue = 0;

    public int kthSmallest2(TreeNode root, int k) {
        inOrder(root, k);
        return ksmallValue;
    }

    private void inOrder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inOrder(root.left, k);
        cnt++;
        if (cnt == k) {
            ksmallValue = root.val;
            return;
        }
        inOrder(root.right, k);
    }


    /**
     * 538. 把二叉搜索树转换为累加树
     * 遍历顺序：右中左
     * 左 = 中 + val
     * 中 = 右 + val
     * 右 = val
     */
    public TreeNode convertBST(TreeNode root) {
        traver(root);
        return root;
    }

    int sum = 0;

    private void traver(TreeNode root) {
        if (root == null) {
            return;
        }
        traver(root.right);
        sum += root.val;
        root.val = sum;
        traver(root.left);
    }


    /**
     * 235. 二叉搜索树的最近公共祖先
     */
    public TreeNode bstLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1. p和q都在root的左子树
        if (p.val < root.val && q.val < root.val) {
            return bstLowestCommonAncestor(root.left, p, q);
        }
        // 2. p和q都在root右子树
        if (p.val > root.val && q.val > root.val) {
            return bstLowestCommonAncestor(root.right, p, q);
        }
        // 3. 其他情况（p和q分别在左右子树中，p和q值可能和根节点相等）
        return root;
    }


    /**
     * 236. 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归函数 想到是深度优先遍历-> 后序遍历（递归返回值再之后处理公共祖先）
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }


    /**
     * 108. 将有序数组转换为二叉搜索树(每个节点的左右两个子树的高度差的绝对值不超过1)
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        // 思路:  有序 --> 二叉搜索且高度不差1   -->  平衡二叉树  --> 取数组中点即为根节点  --> 边界情况: 包括左边界，不包括右边界
        // >>为有符号右移，右移以后最高位保持原来的最高位。
        // >>>这个右移的话最高位补 0。
        return helpArrayToBST(nums, 0, nums.length);
    }

    private TreeNode helpArrayToBST(int[] nums, int left, int right) {
        if (left == right) {
            return null;
        }
        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helpArrayToBST(nums, left, mid);
        root.right = helpArrayToBST(nums, mid + 1, right);
        return root;
    }


    /**
     * 109. 有序链表转换二叉搜索树  (给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树)
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        // 只有一个节点
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        // 快慢指针及pre指针
        ListNode preMid = preMid(head);
        ListNode mid = preMid.next;
        preMid.next = null;// 断开连接
        // BST的根节点
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }

    private ListNode preMid(ListNode head) {
        ListNode slow = head, fast = head.next;
        ListNode pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }


    /**
     * 653. 两数之和IV  给定一个二叉搜索树和一个目标结果，如果BST中存在两个元素且它们的和等于给定的目标结果，则返回true。
     * 中序遍历得到有序数组之后，再利用双指针对数组进行查找。
     */
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> array = new ArrayList<>();
        midOrder(root, array);
        int l = 0, r = array.size() - 1;
        while (l < r) {
            int sum = array.get(l) + array.get(r);
            if (sum == k) {
                return true;
            }
            if (sum < k) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }

    private void midOrder(TreeNode root, List<Integer> array) {
        if (root == null) {
            return;
        }
        midOrder(root.left, array);
        array.add(root.val);
        midOrder(root.right, array);
    }


    /**
     * 530. 二叉搜索树的(节点为非负值)绝对差（任意两节点的差的绝对值）最小值
     */
    public int getMinimumDifference(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return -1;
        }
        //中序遍历中临近的两个节点之差的绝对值，取最小值。
        midOrder(root);
        return minDiff;
    }

    int minDiff = Integer.MAX_VALUE;
    TreeNode preNore = null;

    private void midOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        midOrder(root.left);
        if (preNore != null) {
            minDiff = Math.min(minDiff, root.val - preNore.val);
        }
        preNore = root;
        midOrder(root.right);
    }


}
