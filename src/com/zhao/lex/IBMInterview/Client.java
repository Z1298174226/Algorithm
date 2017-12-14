package com.zhao.lex.IBMInterview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by qtfs on 2017/12/7.
 */
public class Client {
    public static Queue<Custom> queue = new LinkedList<Custom>();
    private static Queue<Custom> list = new LinkedList<Custom>();
    public Lock lock;
    public Condition empty;
    public Condition normalEmpty;
    public long clientStartTime;

    public Client() {
        this.clientStartTime = System.currentTimeMillis();
        lock = new ReentrantLock();
        empty = lock.newCondition();
        normalEmpty = lock.newCondition();
    }

    public long getClientStartTime() {
        return this.clientStartTime;
    }

    static {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try{
            String line = reader.readLine();
            String regex = "\\d{1,2} [\\u4E00-\\u9FFF|\\w]{2,3} \\d{2}:\\d{2} \\d{1,}$";
            Calendar calendar = Calendar.getInstance();
            while(line != null && line.matches(regex)) {
                String[] information = line.split(" ");
                Custom custom = new Custom();
                custom.setId(Long.parseLong(information[0]));
                if(information[1].equals("VIP"))
                    custom.setCustomType(CustomType.VIP);
                else
                    custom.setCustomType(CustomType.NORMAL);
                custom.setCustomServiceTime(Integer.parseInt(information[3]));
                String[] time = information[2].split(":");
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                custom.setCustomArriveTime(calendar.getTime());
                list.add(custom);
                line = reader.readLine();
            }
        }catch(IOException ex) {

        }finally {
            if(reader != null) {
               try{
                   reader.close();
               }catch(IOException ex) {

               }
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.execute(new QueueManager(client));
        executor.execute(new NormalWindow("普通窗口（1）", client));
        executor.execute(new NormalWindow("普通窗口（2）", client));
        executor.execute(new NormalWindow("普通窗口（3）", client));
        executor.execute(new NormalWindow("普通窗口（4）", client));
        executor.execute(new VIPWindow("VIP尊享窗口（1）", client));
        executor.execute(new VIPWindow("VIP尊享窗口（2）", client));
        executor.shutdown();
    }

    public Custom nextCustom() {
        if(list.size() == 0) return null;
        Custom cs = list.poll();
        return cs;
    }

}
