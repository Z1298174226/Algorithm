package balanceLoad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddServer implements Runnable {
	private static List<String> list = new ArrayList<String>();
	private Random rand = new Random();
	static{
		list.add("192.168.1.113");
		list.add("192.168.1.114");
		list.add("192.168.1.115");
		list.add("192.168.1.116");
		list.add("192.168.1.117");
	}
	@Override
	public void run(){
		IpMap.serverWeightMap.putIfAbsent(list.get(rand.nextInt(5)),rand.nextInt(6));
	}

}
