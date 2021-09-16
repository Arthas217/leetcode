import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 55-50+30=35和55-(50+30)=-25
 * https://blog.csdn.net/yy_luckly/article/details/105450251
 * @Date 2021/9/7 14:01
 */
public class Test3 {

    /**
     * 表达式左、右部分
     */
    static class LeftAndRight {

        private int left;
        private int right;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }
    }

    private static List<LeftAndRight> result = new ArrayList<>();
    // 存储表达式中所有减号所在字符串的位置
    private static List<Integer> locate = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        List<String> expression = getExpression(str);
        getSmaller(expression);
    }

    private static List<String> getExpression(String s) {
        char[] chars = s.toCharArray();
        List<Integer> nums = new ArrayList<>();
        // 字符位置
        int index = 0;
        // 保存某一个数值
        int num = 0;
        // 表达式
        List<String> expression = new ArrayList<>();
        while (index < chars.length) {
            if (chars[index] == '+') {
                nums.add(num);
                expression.add(String.valueOf(num));
                expression.add(String.valueOf(chars[index]));
                num = 0;
            } else if (chars[index] == '-') {
                nums.add(num);
                expression.add(String.valueOf(num));
                locate.add(expression.size());
                expression.add(String.valueOf(chars[index]));
                num = 0;
            } else {
                // 计算数值
                int temp = Integer.valueOf(String.valueOf(chars[index]));
                num = num * 10 + temp;
            }
            index++;
            //字符串末尾
            if (index == chars.length) {
                expression.add(String.valueOf(num));
            }
        }
        return expression;
    }

    /**
     * 1+2-1-1-1
     * @param nums
     */
    private static void getSmaller(List<String> nums) {
        int smaller=0;
        // 只有一项数值
        if (nums.size() == 1) {
            smaller = Integer.valueOf(nums.get(0));
        } else {
            // 以减号为分界线 分别求两边的表达式
            // 左边表达式为：1+2 或者1+2-1或者1+2-1-1
            // 右边表达式为：1-1-1 或 1-1 或1
            for (int i = 0; i < locate.size(); i++) {
                LeftAndRight leftAndRight = new LeftAndRight();
                List<String> left = nums.subList(0, locate.get(i));
                List<String> right = nums.subList(locate.get(i) + 1, nums.size());
                // 减号左边表达式处理
                for (int j = 0; j < left.size(); j++) {
                    switch (left.get(j)) {
                        case "+": {
                            j++;
                            leftAndRight.setLeft(leftAndRight.getLeft() + Integer.valueOf(left.get(j)));
                            break;
                        }
                        case "-": {
                            j++;
                            leftAndRight.setLeft(leftAndRight.getLeft() - Integer.parseInt(left.get(j)));
                            break;
                        }
                        default:
                            leftAndRight.setLeft(Integer.valueOf(left.get(j)));
                            break;
                    }
                }
                // 减号左边表达式处理
                for (int j = 0; j < right.size(); j++) {
                    switch (right.get(j)) {
                        case "+":
                        case "-": {
                            // 无论加减 都求和
                            j++;
                            leftAndRight.setRight(leftAndRight.getRight() + Integer.valueOf(right.get(j)));
                            break;
                        }
                        default:
                            leftAndRight.setRight(Integer.valueOf(right.get(j)));
                            break;
                    }
                }
                result.add(leftAndRight);
                int res = leftAndRight.getLeft() - leftAndRight.getRight();
                if (i == 0) {
                    // 首次
                    smaller = res;
                }
                if (smaller > res) {
                    smaller = res;
                }
            }
            // 没有减号的表达式，即求和
            if (locate.size() == 0) {
                for (int j = 0; j < nums.size(); j++) {
                    smaller += Integer.valueOf(nums.get(j));
                    j++;
                }
            }
        }
        System.out.println(smaller);
    }
}
