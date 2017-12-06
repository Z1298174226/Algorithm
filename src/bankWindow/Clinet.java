package bankWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qtfs on 2017/12/6.
 */
public class Clinet {
    private static LinkedList<Custom> customList = new LinkedList<Custom>();
    private Long serviceStartTime;

    public Long getServiceStartTime() {
        return serviceStartTime;
    }

    public Clinet() {
        serviceStartTime = System.currentTimeMillis();
    }

    static {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("src\\data.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String line = reader.readLine();
            String regex = "\\d{1,2} [\\u4E00-\\u9FFF|\\w]{2,3} \\d{2}:\\d{2} \\d{1,}$";
            Calendar calendar = Calendar.getInstance();
            while(null != line && line.matches(regex)) {
                String [] customInfo = line.split(" ");
                Custom custom = new Custom();
                custom.setOrder(Long.parseLong(customInfo[0]));
                custom.setServiceTime(Integer.parseInt(customInfo[3]));
                if (customInfo[1].equals("VIP")) {
                    custom.setCustomType(CustomType.VIP);
                } else {
                    custom.setCustomType(CustomType.COMMON);
                }
                String [] time = customInfo[2].split(":");
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                custom.setArrivalDate(calendar.getTime());

                customList.offer(custom);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Clinet client = new Clinet();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new GeneralWindow(client, "第1普通窗口"));
        executorService.execute(new GeneralWindow(client, "第2普通窗口"));
        executorService.execute(new GeneralWindow(client, "第3普通窗口"));
        executorService.execute(new VIPWindow(client));

        executorService.shutdown();
    }

    /**
     * 获取下一个客户
     * @param customType 客户类型
     * @return VIP窗口或者普通窗口要服务的下一个客户
     */
    public synchronized Custom nextCustom(CustomType customType) {
        if (customType == CustomType.VIP) {
            Custom cs; int index = 0;
            while (index < customList.size() && (cs = customList.get(index++)) != null) {
                if (customType == cs.getCustomType()) {
                    customList.remove(cs);
                    return cs;
                }
            }
        }
        return customList.poll();
    }
}