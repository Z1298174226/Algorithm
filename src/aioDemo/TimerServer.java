package aioDemo;

import java.io.IOException;

/**
 * Created by qtfs on 2017/11/1.
 */
public class TimerServer {

    public static void main(String[] args) throws IOException {
        int port = 8083;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        AsynchronizedServerTimer timeServer = new AsynchronizedServerTimer(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }

}
