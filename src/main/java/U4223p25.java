import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/9/7 11:56
 */
public class U4223p25 {

        private static List<LeftAndRight> result = new ArrayList<>();

        private static List<Integer> locate = new ArrayList<>();

        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            String s = sc.next();

            List<String> ss = new ArrayList<>();
            List<Integer> nums = new ArrayList<>();
            char[] chars = s.toCharArray();
            int len = 0;
            int num = 0;
            while(len < chars.length){
                if (chars[len] == '+'){
                    nums.add(num);
                    ss.add(String.valueOf(num));
                    ss.add(String.valueOf(chars[len]));
                    num =0;
                } else if (chars[len] == '-'){
                    nums.add(num);
                    ss.add(String.valueOf(num));
                    locate.add(ss.size());
                    ss.add(String.valueOf(chars[len]));
                    num=0;
                } else {
                    int temp = Integer.valueOf(String.valueOf(chars[len]));
                    num = num*10 + temp;
                }
                len++;
                if(len == chars.length){
                    ss.add(String.valueOf(num));
                }
            }
            getSmaller(ss);
        }

        private static void getSmaller(List<String> nums){
            int smaller = 0;
            if(nums.size() == 1){
                smaller = Integer.valueOf(nums.get(0));
            } else{
                for(int i = 0 ; i < locate.size(); i++){
                    LeftAndRight leftAndRight = new LeftAndRight();
                    List<String> left = nums.subList(0,locate.get(i));
                    List<String> right = nums.subList(locate.get(i)+1,nums.size());
                    for(int j = 0 ; j < left.size(); j++){
                        switch (left.get(j)){
                            case "+": {
                                j++;
                                leftAndRight.setLeft(leftAndRight.getLeft()+Integer.valueOf(left.get(j)));
                                break;
                            }
                            case "-":{
                                j++;
                                leftAndRight.setLeft(leftAndRight.getLeft()-Integer.parseInt(left.get(j)));
                                break;
                            }
                            default:
                                leftAndRight.setLeft(Integer.valueOf(left.get(j)));
                                break;
                        }
                    }
                    for(int j = 0 ; j < right.size(); j++){
                        switch (right.get(j)){
                            case "+":
                            case "-": {
                                j++;
                                leftAndRight.setRight(leftAndRight.getRight()+Integer.valueOf(right.get(j)));
                                break;
                            }
                            default:
                                leftAndRight.setRight(Integer.valueOf(right.get(j)));
                                break;
                        }
                    }
                    result.add(leftAndRight);
                    int res = leftAndRight.getLeft() - leftAndRight.getRight();
                    if (i==0){
                        smaller = res;
                    }
                    if(smaller > res){
                        smaller = res;
                    }
                }
                if(locate.size()==0){
                    for (int j = 0; j< nums.size();j++){
                        smaller += Integer.valueOf(nums.get(j));
                        j++;
                    }
                }
            }
            System.out.println(smaller);
        }


        static class LeftAndRight{

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

}
