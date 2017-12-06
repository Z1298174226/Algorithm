package aioDemo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by qtfs on 2017/11/1.
 */
public class AsynchronousServerHandler implements CompletionHandler<AsynchronousSocketChannel, AsynchronizedServerTimer>{
    @Override
    public void completed(AsynchronousSocketChannel result, AsynchronizedServerTimer attachment) {
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        result.read(byteBuffer, byteBuffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsynchronizedServerTimer attachment) {
        try{
            attachment.asynchronousServerSocketChannel.close();
        }catch(IOException ex) {
            attachment.countDownLatch.countDown();
            ex.printStackTrace();
        }
    }
}
