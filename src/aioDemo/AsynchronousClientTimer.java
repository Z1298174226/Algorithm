package aioDemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by qtfs on 2017/11/1.
 */
public class AsynchronousClientTimer implements CompletionHandler<Void, AsynchronousClientTimer>, Runnable {
    private CountDownLatch countDownLatch;
    private AsynchronousSocketChannel asynchronousSocketChannel;
    private int port;
    private String host;

    public AsynchronousClientTimer(int port, String host) {
        this.host = host;
        this.port = port;
        try {
            asynchronousSocketChannel = AsynchronousSocketChannel.open();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            countDownLatch = new CountDownLatch(1);
            asynchronousSocketChannel.connect(new InetSocketAddress(host, port), this, this);
            countDownLatch.await();
        }catch(InterruptedException ex) {
            ex.printStackTrace();
        }
        try{
            asynchronousSocketChannel.close();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void completed(Void result, AsynchronousClientTimer attachment) {
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = "Quiry Time Order".getBytes();
        writeBuffer.put(bytes);
        writeBuffer.flip();
        asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {

            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if(writeBuffer.hasRemaining()) {
                    asynchronousSocketChannel.write(writeBuffer, writeBuffer,  this);
                }else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    asynchronousSocketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            byte[] bytes = new byte[attachment.remaining()];
                            attachment.get(bytes);
                            String time;
                            try{
                                time = new String(bytes, "UTF-8");
                                System.out.println("Now is : " + time);
                                countDownLatch.countDown();
                            }catch(UnsupportedEncodingException ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            try{
                                asynchronousSocketChannel.close();
                            }catch(IOException ex) {

                            }
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try{
                    asynchronousSocketChannel.close();
                }catch(IOException ex) {

                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, AsynchronousClientTimer attachment) {
        try{
            asynchronousSocketChannel.close();
            countDownLatch.countDown();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
