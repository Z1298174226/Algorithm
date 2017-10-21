import java.util.HashMap;
import java.util.UUID;

import static com.sun.xml.internal.org.jvnet.fastinfoset.EncodingAlgorithmIndexes.UUID;

/**
 * Created by qtfs on 2017/9/22.
 */
public class HashMapEndLoop {
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable(){
           @Override
            public void run() {
               for(int i = 0; i < 10000; i++) {
                   new Thread(new Runnable() {
                      @Override
                       public void run() {
                          map.put(java.util.UUID.randomUUID().toString(), "");
                      }
                   }, "ftf" + i).start();
               }
           }
        }, "ftf");
        t.start();
        t.join();
    }
}
