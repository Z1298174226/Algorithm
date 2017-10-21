/**
 * Created by qtfs on 2017/9/27.
 */
public class SynchronizedStaticDemo {
    public static int count = 0;
    public static synchronized void func() {
        count++;
    }
    public synchronized void funcOther() {
        count++;
    }
    public static void main(String[] args) {
        for(int i = 0; i < 1000; i++) {
            Thread thread = new Thread() {
              @Override
                public void run() {
                  SynchronizedStaticDemo test = new SynchronizedStaticDemo();
                  //test.funcOther();
                  func();
                  func();
              }
            };
            thread.start();
        }
        System.out.println(count);
    }

}
