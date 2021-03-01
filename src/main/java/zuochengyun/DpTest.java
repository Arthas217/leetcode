package zuochengyun;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/2/26 16:19
 */
public class DpTest {


    /**
     * N个数（N>=2)，从M位置出发，经过K步，最终到达P位置
     * 如果M=1,只能往右走一步
     * 如果M =N ,只能往左走一步
     * 如果M为中间，可以往左或者往右走一步
     */


    /**
     * 暴力递归
     */
    public int walk1(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        return walk(N, M, K, P);
    }

    private int walk(int n, int cur, int rest, int p) {
        if (rest == 0) {
            return cur == p ? 1 : 0;
        }
        if (cur == 1) {
            return walk(n, 2, rest - 1, p);
        }
        if (cur == n) {
            return walk(n, n - 1, rest - 1, p);
        }
        return walk(n, cur - 1, rest - 1, p) + walk(n, cur + 1, rest - 1, p);
    }

    /**
     * 记忆化搜索
     */
    public int walk2(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        // cur[1,N] rest[0,K]
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        return walkDp(N, M, K, P, dp);
    }

    private int walkDp(int N, int cur, int rest, int P, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        if (rest == 0) {
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }
        if (cur == 1) {
            dp[cur][rest] = walkDp(N, 2, rest - 1, P, dp);
            return dp[cur][rest];
        }
        if (cur == N) {
            dp[cur][rest] = walkDp(N, N - 1, rest - 1, P, dp);
            return dp[cur][rest];
        }
        return walkDp(N, cur - 1, rest - 1, P, dp) + walkDp(N, cur + 1, rest - 1, P, dp);
    }

    public int walk3(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int reset = 0; reset <= K; reset++) {
            for (int cur = N; cur >= 1; cur--) {
                if (reset == 0 && cur == P) {
                    dp[cur][reset] = 1;
                } else {
                    dp[cur][reset] = 0;
                }
                if (cur == 1 && reset > 0) {
                    dp[cur][reset] = dp[2][reset - 1];
                }
                if (cur == N && reset > 0) {
                    dp[cur][reset] = dp[N - 1][reset - 1];
                }
                if (cur > 1 && cur < N && reset > 0) {
                    dp[cur][reset] = dp[cur + 1][reset - 1] + dp[cur - 1][reset - 1];
                }
            }
        }
        return dp[M][K];
    }


    /**
     * 给定两个长度都是N的数组w,v，给定整数bag载重的袋子，求返回装下的最大价值
     */
    public int beibao(int[] w, int[] v, int bag) {
//        return maxValue(w, v, 0, bag);
        return dpWay(w, v, bag);
    }


    /**
     * 暴力 取或者不取
     */
    private int maxValue(int[] weight, int[] value, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == weight.length) {
            return 0;
        }
        // index位置未取
        int v1 = maxValue(weight, value, index + 1, rest);
        int v2 = Integer.MIN_VALUE;
        if (rest > weight[index]) {
            v2 = value[index] + maxValue(weight, value, index + 1, rest - weight[index]);
        }
        return Math.max(v1, v2);
    }

    private int dpWay(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= w[index]) {
                    dp[index][rest] = Math.max(dp[index][rest], v[index] + dp[index + 1][rest - w[index]]);
                }
            }
        }
        return dp[0][bag];
    }


    /**
     * "111" -> "AAA" "AK" "KA"
     *
     * @return
     */
    public int num4Zimu(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
//        return numHelp(str.toCharArray(), 0);
        return numDp(str);
    }

    /**
     * 暴力
     */
    private int numHelp(char[] str, int i) {
        if (i == str.length) {
            // 0到i-1转化的个数是有效
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        if (str[i] == '1') {
            // i+1递归
            int res = numHelp(str, i + 1);
            if (i + 1 < str.length) {
                // i+2递归
                res += numHelp(str, i + 2);
            }
            return res;
        }
        if (str[i] == '2') {
            // i+1递归
            int res = numHelp(str, i + 1);
            if (i + 1 < str.length && str[i + 1] >= '0' && str[i + 1] <= '6') {
                // i+2递归
                res += numHelp(str, i + 2);
            }
            return res;
        }
        // str[i] == '3'
        return numHelp(str, i + 1);
    }

    /**
     * DP
     */
    public int numDp(String str) {
        char[] chars = str.toCharArray();
        int N = chars.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (chars[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < N) {
                    dp[i] += dp[i + 2];
                }
            }
            if (chars[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < N && chars[i + 1] >= '0' && chars[i + 1] <= '6') {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }


    /**
     * 整形数组，数值不同，A和B 轮流拿最左或者最右值，返回获胜分数
     */
    public int winner(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return dpWinner(arr);
//        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    private int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));
    }

    private int s(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(f(arr, L + 1, R), f(arr, L, R - 1));
    }

    private int dpWinner(int[] arr) {
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        // f的对角线
        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
        }
        // s对角线 都是0；
        for (int i = 1; i < N; i++) {
            int row = 0;
            int col = i;
            while (row < N && col < N) {
                f[row][col] = Math.max(arr[row] + s[row + 1][col], arr[col] + s[row][col - 1]);
                s[row][col] = Math.min(f[row + 1][col], f[row][col - 1]);
                row++;
                col++;
            }

        }
        return Math.max(f[0][N - 1], s[0][N - 1]);
    }


    /**
     * 面值数组，都是整数，可以使用任意张，求和aim有多少种方法
     */
    public int money(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
//        return moneyHelp(arr, 0, aim);
//        return moneyMem(arr, aim);
        return moneyDp(arr, aim);
    }


    /**
     * 暴力递归
     */
    private int moneyHelp(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int res = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            res += moneyHelp(arr, index + 1, rest - zhang * arr[index]);
        }
        return res;
    }

    /**
     * 记忆化搜索
     */
    public int moneyMem(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return dpHelp(arr, 0, aim, dp);
    }

    private int dpHelp(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (index == arr.length) {
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        int res = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            res += dpHelp(arr, index + 1, rest - (zhang * arr[index]), dp);
        }
        dp[index][rest] = res;
        return dp[index][rest];
    }

    /**
     * 经典DP
     */
    private int moneyDp(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];

        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 表格中的每一项有for循环枚举行为
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    dp[index][rest] += dp[index + 1][rest - (zhang * arr[index])];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        DpTest dpTest = new DpTest();
//        int walk1 = dpTest.walk1(6, 3, 4, 5);
//        int walk2 = dpTest.walk2(6, 3, 4, 5);
//        int walk3 = dpTest.walk3(6, 3, 4, 5);
//        System.out.println(walk1);
//        System.out.println(walk2);
//        System.out.println(walk3);

//        int[] weight = {3, 2, 4, 7};
//        int[] value = {5, 6, 3, 19};
//        int bag = 11;
//        int maxValue = dpTest.beibao(weight, value, bag);
//        System.out.println(maxValue);

//        int num = dpTest.num4Zimu("111");
//        System.out.println(num);
//        int num2 = dpTest.numDp("111");
//        System.out.println(num2);

//        int[] arr = {4, 7, 9, 5};
//        int winner = dpTest.winner(arr);
//        System.out.println(winner);


        int[] arr = {5, 10, 50, 100};
        int money = dpTest.money(arr, 1000);
        System.out.println(money);
    }

}
