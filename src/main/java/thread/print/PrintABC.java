package thread.print;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://m.zhipin.com/mpa/html/get/column?contentId=0c3f78b8802ccca7qxB709o~&identity=0&userId=26386822
 * 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
 * 2021/2/23 22:39
 */
public class PrintABC {
    // 打印次数
    private int printCount;
    // 三个线程状态
    private int state;

    public PrintABC(int num) {
        this.printCount = num;
    }

    // 使用Lock
    private Lock lock = new ReentrantLock();

    public void printLetter4Lock(String name, int init) {
        for (int i = 0; i < printCount; ) {
            lock.lock();
            try {
                if (state % 3 == init) {
                    state++;
                    System.out.println(name);
                    i++;// 注意执行完ABC之后，才去执行第二次for循环
                }
            } finally {
                lock.unlock();
            }
        }
    }

    //使用 wait/notify 和"同步锁"(synchronized 关键字)捆绑使用
    //同步块中类级别的锁
    private final static Object LOCK = new Object();

    public void printLetter4WaitNotify(String name, int init) {
        for (int i = 0; i < printCount; i++) {
            synchronized (LOCK) {
                while (state % 3 != init) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                state++;
                System.out.println(name);
                LOCK.notifyAll();
            }
        }
    }


    // 使用 Lock/Condition 是需要与"互斥锁"/"共享锁"捆绑使用
    private static Lock lock2 = new ReentrantLock();
    private static Condition c1 = lock2.newCondition();
    private static Condition c2 = lock2.newCondition();
    private static Condition c3 = lock2.newCondition();

    public void printLetter4LockCondition(String name, int init, Condition current, Condition next) {
        for (int i = 0; i < printCount; ) {
            lock2.lock();
            try {
                while (state % 3 != init) {
                    current.await();
                }
                state++;
                i++;
                System.out.println(name);
                next.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock2.unlock();
            }
        }
    }

    private static Semaphore semaphoreA = new Semaphore(1); // 只有 A 初始信号量为 1,第一次获取到的只能是 A
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);

    public void printLetter4Semaphore(String name, Semaphore current, Semaphore next) {
        for (int i = 0; i < printCount; i++) {
            try {
                current.acquire();
                System.out.println(name);
                next.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    // 静态方法unpark()和park()可以分别实现阻塞当前线程和唤醒指定线程
    private static Thread a,b,c;

    public static void main(String[] args) {
        PrintABC print = new PrintABC(3);
        new Thread(() -> {
//            print.printLetter4Lock("B", 1);
//            print.printLetter4WaitNotify("B", 1);
//            print.printLetter4LockCondition("B", 1, c2, c3);
//            print.printLetter4Semaphore("B", semaphoreB, semaphoreC);
        }).start();

        new Thread(() -> {
//            print.printLetter4Lock("A", 0);
//            print.printLetter4WaitNotify("A", 0);
//            print.printLetter4LockCondition("A", 0, c1, c2);
//            print.printLetter4Semaphore("A", semaphoreA, semaphoreB);
        }).start();

        new Thread(() -> {
//            print.printLetter4Lock("C", 2);
//            print.printLetter4WaitNotify("C", 2);
//            print.printLetter4LockCondition("C", 2, c3, c1);
//            print.printLetter4Semaphore("C", semaphoreC, semaphoreA);
        }).start();

        //第五种方式
        a = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName());
                LockSupport.unpark(b);
                LockSupport.park(a);
            }
        },"A");

        b = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName());
                LockSupport.unpark(c);
            }
        },"B");

        c = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName());
                LockSupport.unpark(a);
            }
        },"C");

        a.start();
        b.start();
        c.start();
    }
}
