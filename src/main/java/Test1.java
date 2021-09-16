import java.util.*;

import static java.lang.Character.isDigit;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://www.codeleading.com/article/10813291803/
 * @Date 2021/9/7 11:09
 */
public class Test1 {

    /**
     * 枚举手机键位对应的x,y坐标值
     * @param x 0-9数值
     * @return
     */
    private static int[][] MapPhoneCall(int x) {
        Map<Integer, int[][]> phoneCall = new HashMap<>();
        phoneCall.put(0, new int[][]{{0, -2}});
        phoneCall.put(1, new int[][]{{-1, 1}});
        phoneCall.put(2, new int[][]{{0, 1}});
        phoneCall.put(3, new int[][]{{1, 1}});
        phoneCall.put(4, new int[][]{{-1, 0}});
        phoneCall.put(5, new int[][]{{0, 0}});
        phoneCall.put(6, new int[][]{{1, 0}});
        phoneCall.put(7, new int[][]{{-1, -1}});
        phoneCall.put(8, new int[][]{{0, -1}});
        phoneCall.put(9, new int[][]{{1, -1}});
        return phoneCall.get(x);
    }

    public static void main(String[] args) {
        List<Integer> numTemp = new ArrayList();
        // key是索引，value电话按键对应的（x,y）
        Map<Integer, int[][]> mapTemp = new HashMap<>();
        int sum = 0;
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        // 电话号码位数
        int digit = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (isDigit(chars[i])) {
                digit++;
                String c1 = String.valueOf(chars[i]);
                int res = Integer.valueOf(c1);
                numTemp.add(res);
            }
        }
        if (3 <= digit && digit <= 20) {
            // 初始化电话键位5为中心位置
            mapTemp.put(0, MapPhoneCall(5));
            for (int j = 1; j <= numTemp.size(); j++) {
                // 电话号码映射到map中
                mapTemp.put(j, MapPhoneCall(numTemp.get(j - 1)));
            }
        } else {
            System.out.print("请输入3至20位数字");
        }
        // 计算距离值
        for (int x = 0; x < digit; x++) {
            int temp;
            // 二维数组 int[][]{{0, -2}}
            temp = distancesum(mapTemp.get(x)[0], mapTemp.get(x + 1)[0]);
            sum += temp;
        }
        System.out.println(sum);
    }

    private static int distancesum(int[] x, int[] y) {
        int sum = 0;
        // i和j代表是数组中第一个位置、第二个位置
        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 2; j++) {
                sum = (Math.abs(x[i] - y[i]) + Math.abs(x[j] - y[j]));
            }
        }

        return sum;
    }
}
