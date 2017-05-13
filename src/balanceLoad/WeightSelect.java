package balanceLoad;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class WeightSelect implements Callable<String> {
	private String server;
	private Map<String,Integer> serverMap = new HashMap<String,Integer>();
	private AtomicInteger pos;
	
	public WeightSelect(){
	    pos = new AtomicInteger();
		server = null;
	}
	
	public String call(){
		serverMap.putAll(IpMap.serverWeightMap);
		Set<String> keySet = new HashSet<String>();
		keySet = serverMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		
		List<String> list = new ArrayList<String>();
		while(iterator.hasNext()){
			String next = iterator.next();
			int weight = serverMap.get(next);
			for(int i = 0; i < weight; i++){
				list.add(next);
			}
		}
//		synchronized(pos){
//			if(pos > list.size())
//				pos = 0;
//			server = list.get(pos);
//			pos++;
//		}
		   if(pos.get() >= list.size()){
			   pos.set(0); 
		   }
		   server = list.get(pos.get());
		   pos.incrementAndGet();
		   System.out.println(list.size());
		return server;
	}

}
