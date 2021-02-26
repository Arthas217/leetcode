package zuochengyun;

/**
 * @Author 会游泳的蚂蚁
 * @Description: N个数（N>=2)，从M位置出发，经过K步，最终到达P位置
 * 如果M=1,只能往右走一步
 * 如果M =N ,只能往左走一步
 * 如果M为中间，可以往左或者往右走一步
 * @Date 2021/2/26 16:19
 */
public class DpTest {
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
     * 记忆化搜索（属于DP）
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

    public static void main(String[] args) {
        DpTest dpTest = new DpTest();
        int walk1 = dpTest.walk1(6, 3, 4, 5);
        int walk2 = dpTest.walk2(6, 3, 4, 5);
        System.out.println(walk1);
        System.out.println(walk2);
    }
}
