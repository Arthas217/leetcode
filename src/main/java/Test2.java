import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * https://leetcode-cn.com/circle/discuss/3tQ0aS/
 * @Date 2021/9/7 11:28
 */
public class Test2 {

    static class Location {
        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        // 保存所有指令
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= rows; i++) {
            String data = scanner.nextLine();
            list.add(data);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder = builder.append(list.get(i));
        }

        int i = 0;
        // 上左下右
        int[][] direction = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        Location location = new Location(0, 0);
        // 设初始向北，然后按照题目指令模拟，每模拟一次算一轮。
        // 总的模拟轮数不会超过4, 时间复杂度 O(n).
        int cnt = 0;
        for (; cnt < 4; cnt++) {
            for (int j = 0; j < builder.length(); j++) {
                if ("S".equals(builder.substring(j, j + 1))) {
                    location.setX(location.getX() + direction[i][0]);
                    location.setY(location.getY() + direction[i][1]);
                }
                if ("L".equals(builder.substring(j, j + 1))) {
                    if (i == 3) {
                        i = 0;
                    } else {
                        i++;
                    }
                }
                if ("R".equals(builder.substring(j, j + 1))) {
                    if (i == 0) {
                        i = 3;
                    } else {
                        i--;
                    }
                }
            }
            // 如果一轮过后的朝向是北，那么说明已经过了一个周期。然后看当前位置是否和原点有偏移。
            if (i == 0 && !(location.getY() == 0 && location.getX() == 0)) {
                System.out.println("yes");
                break;
            }
        }
        if (cnt == 4) {
            System.out.println("no");
        }
    }
}