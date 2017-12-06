package aioDemo;

/**
 * Created by qtfs on 2017/11/1.
 */
public class TimerClient {
    public static void main(String[] args) {
        int port = 8083;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }

        }
        new Thread(new AsynchronousClientTimer(port, "127.0.0.1"),
                "AIO-AsyncTimeClientHandler-001").start();

    }
}
