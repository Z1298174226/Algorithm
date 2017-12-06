package aioDemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by qtfs on 2017/11/1.
 */
public class ReadCompletionHandler implements CompletionHandler<java.lang.Integer, ByteBuffer> {
    private AsynchronousSocketChannel asynchronousSocketChannel ;
    public ReadCompletionHandler(AsynchronousSocketChannel asynchronousSocketChannel) {
        this.asynchronousSocketChannel = asynchronousSocketChannel;
    }

    @Override
    public void completed(java.lang.Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] bytes = new byte[attachment.remaining()];
        attachment.get(bytes);
        try{
            String req = new String(bytes, "UTF-8");
            System.out.println("The time server received : " + req);
            String currentTime = "Quiry Timer Order".equalsIgnoreCase(req) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD Quiry!";
            doWrite(currentTime);

        }catch(UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    private void doWrite(String currentTime) {
        if(currentTime != null && currentTime.trim().length() != 0) {
            byte[] bytes = currentTime.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if(writeBuffer.hasRemaining())
                        asynchronousSocketChannel.write(writeBuffer, writeBuffer, this);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try{
                        asynchronousSocketChannel.close();
                    }catch(IOException ex) {
                        ex.printStackTrace();
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
            ex.printStackTrace();
        }
    }
}
