package aioDemo;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * Created by qtfs on 2017/11/1.
 */
public class AsynchronizedServerTimer implements Runnable{
    public AsynchronousServerSocketChannel asynchronousServerSocketChannel;
    public CountDownLatch countDownLatch;

    public AsynchronizedServerTimer(int port) {
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("The Timer Server Starts at : " + port);
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        countDownLatch = new CountDownLatch(1);
        try{
            countDownLatch.await();
            doAccept();
        }catch(InterruptedException ex) {

        }
    }

    private void doAccept() {
        asynchronousServerSocketChannel.accept(this, new AsynchronousServerHandler());
    }
}
