package leetcode;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 图
 * @Date 2021/4/6 09:26
 */
public class GrapTopic {

    private static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private int rows;
    private int cols;
    // 被访问过陆地
    private boolean[][] visited;

    /**
     * 200. 岛屿数量
     * 从一个是「陆地」的格子开始进行一次「深度优先遍历」或者「广度优先遍历」，把与之相连的所有的格子都标记（也可以说「被访问过」）上，视为发现了一个「岛屿」
     * https://leetcode-cn.com/problems/number-of-islands/solution/dfs-bfs-bing-cha-ji-python-dai-ma-java-dai-ma-by-l/
     */
    public int numIslands(char[][] grid) {
        // 深度优先遍历
        return DeepMethod(grid);
    }

    private int DeepMethod(char[][] grid) {
        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果是岛屿中的一个点，并且没有被访问过，就进行深度优先遍历
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 从坐标为 (i, j) 的点开始进行深度优先遍历
     */
    private void dfs(int i, int j, char[][] grid) {
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            // 上左下右
            int newX = i + DIRECTIONS[k][0];
            int newY = j + DIRECTIONS[k][1];
            // 如果不越界、还是陆地、没有被访问过
            if (inArea(newX, newY) && grid[newX][newY] == '1' && !visited[newX][newY]) {
                dfs(newX, newY, grid);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }


    /**
     * 695. 岛屿的最大面积 v1.1
     */
    public int maxAreaOfIsland(int[][] grid) {
        return DeepHelp(grid);
    }

    private int DeepHelp(int[][] grid) {
        int res = 0;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int len = 0;
                res = Math.max(res, dfs(grid, i, j, len));
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int len) {
        if (grid[i][j] == 1 && !visited[i][j]) {
            len++;
            visited[i][j] = true;
            for (int k = 0; k < 4; k++) {
                // 上左下右
                int newX = i + DIRECTIONS[k][0];
                int newY = j + DIRECTIONS[k][1];
                if (inArea(newX, newY) && grid[newX][newY] == 1 && !visited[newX][newY]) {
                    // 保存最大值，因为dfs回溯的时候值会恢复的（len恢复为刚开始进来时的值
                    len = Math.max(len, dfs(grid, newX, newY, len));
                }
            }
        }
        return len;
    }


    /**
     * 695. 岛屿的最大面积  v1.2 （深度优先方式）
     */
    public int maxAreaOfIsland2(int[][] grid) {
        return DeepMethod(grid);
    }

    private int DeepMethod(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                ans = Math.max(ans, dfs(grid, i, j));
            }
        }
        return ans;
    }

    public int dfs(int[][] grid, int cur_i, int cur_j) {
        // 注意最后一个条件不能放在最前面，报错
        if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
            return 0;
        }
        grid[cur_i][cur_j] = 0;
        int[] di = {0, 0, 1, -1};
        int[] dj = {1, -1, 0, 0};
        int ans = 1;
        for (int index = 0; index != 4; ++index) {
            int next_i = cur_i + di[index], next_j = cur_j + dj[index];
            ans += dfs(grid, next_i, next_j);
        }
        return ans;
    }
}
