package thread.print;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/6/15 10:22
 */
public class Test implements Runnable {

    private static final int COUNT = 10000000;
    private static int i;

    /**
     * synchronized修饰的方法是普通成员方法，那么锁就是加给Test对象的
     */
    private synchronized void add() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < COUNT; j++) {
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //创建线程时使用了两个Test实例,也就是说这个锁是给这两个实例加的锁，并没有达到同步的效果

//        Thread t1 = new Thread(new Test());
//        Thread t2 = new Thread(new Test());
//
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(i);
//
//        i = 0;

        Test t = new Test();
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);

        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(i);


    }
}
