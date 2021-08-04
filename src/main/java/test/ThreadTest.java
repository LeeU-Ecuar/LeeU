package test;

/**
 * //有3个线程， 线程1， 线程2和线程3， 每个线程严格执行自己的动作，
 * //流程如下：
 * //1. 线程1 执行动作1 后，创建线程2 和线程3， 创建线程2 和线程3后， block 线程1，直到 线程2 完成动作2和线程3 完成动作3  , 动作2 和动作3 都完成后, 才能激活线程1.
 * //2. 线程2 启动后，执行动作2，执行完动作2后， block 线程2，直到线程1 完成动作4，然后线程2自己结束
 * //3. 线程3 启动后，执行动作3，执行完动作3后， block 线程3，直到线程1 完成动作4，然后线程3自己结束。
 * //4. 等动作2 和动作3 都完成后， 唤醒线程1， 唤醒线程1后， 执行动作4
 * //5. 线程1 执行完动作4后， 等待线程2 和线程3 结束, 线程2和线程3 结束后,自己也退出。
 *
 *
 * 整个流程
 * //动作1(线程1)  -- 动作2/动作3(线程2/线程3 是并行) -- 动作4 (线程1)
 *
 * 动作1/动作2/动作3 -- 分别用print 答应一段语句即可
 */
public class ThreadTest {

    public static void main(String[] args) {
        new ThreadTest().test();
    }

    public void test(){
        //启动线程1
        new Thread1(new AsyncFlag()).start();
    }


    public class Thread1 extends Thread {
        AsyncFlag flag;
        public Thread1(AsyncFlag flag) {
            this.flag = flag;
        }
        public synchronized void run() {
            System.out.println("动作1");
            flag.setFlag1(true);
            //启动线程1、2
            new Thread2(flag).start();
            new Thread3(flag).start();
            //动作2、3是否结束
            while(true){
                if(flag.isFlag2()&&flag.isFlag3()){
                    System.out.println("动作4");
                    flag.setFlag4(true);
                    break;
                }
            }
            //线程2、3是否结束
            while (true){
                if(flag.isFlag2()&&flag.isFlag3()){
                    flag.setT1(true);
                    break;
                }
            }
        }
    }
    public class Thread2 extends Thread {
        AsyncFlag flag;
        public Thread2(AsyncFlag flag) {
            this.flag = flag;
        }
        public synchronized void run() {
            try {
                Thread.sleep(100);//测试线程2延迟100毫秒
            } catch (InterruptedException e) {}
            System.out.println("动作2");
            flag.setFlag2(true);
            //动作4是否结束
            while (true){
                if(flag.isFlag4()){
                    flag.setT2(true);
                    break;
                }
            }
        }
    }
    public class Thread3 extends Thread {
        AsyncFlag flag;
        public Thread3(AsyncFlag flag) {
            this.flag = flag;
        }
        public synchronized void run() {
            System.out.println("动作3");
            flag.setFlag3(true);
            //动作4是否结束
            while (true){
                if(flag.isFlag4()){
                    flag.setT3(true);
                    break;
                }
            }
        }
    }

    // 异步线程状态记录类，访问成员变量的方法需要synchronized修饰
    class AsyncFlag{
        //线程是否执行完成
        boolean t1 = false;
        boolean t2 = false;
        boolean t3 = false;
        //动作是否执行完成
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;

        public synchronized void setT1(boolean t1) {
            this.t1 = t1;
        }

        public synchronized void setT2(boolean t2) {
            this.t2 = t2;
        }

        public synchronized void setT3(boolean t3) {
            this.t3 = t3;
        }

        public synchronized void setFlag1(boolean flag1) {
            this.flag1 = flag1;
        }

        public synchronized void setFlag2(boolean flag2) {
            this.flag2 = flag2;
        }

        public synchronized void setFlag3(boolean flag3) {
            this.flag3 = flag3;
        }

        public synchronized void setFlag4(boolean flag4) {
            this.flag4 = flag4;
        }

        public synchronized boolean isT1() {
            return t1;
        }

        public synchronized boolean isT2() {
            return t2;
        }

        public synchronized boolean isT3() {
            return t3;
        }

        public synchronized boolean isFlag1() {
            return flag1;
        }

        public synchronized boolean isFlag2() {
            return flag2;
        }

        public synchronized boolean isFlag3() {
            return flag3;
        }

        public synchronized boolean isFlag4() {
            return flag4;
        }
    }


}
