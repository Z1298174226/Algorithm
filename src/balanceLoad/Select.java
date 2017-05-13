package balanceLoad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class Select implements Callable<String>{
	
	private volatile Integer pos;
	private volatile String server;
		
	public Select(){
		pos = 0;
		server = null;		
	}
	
	@Override
	public String call(){
		Map<String,Integer> serverMap = new HashMap<String,Integer>();
		serverMap.putAll(IpMap.serverWeightMap);
		Set<String> keySet = new HashSet<String>();
		keySet = serverMap.keySet();
		List<String> keyList = new ArrayList<String>();
		keyList.addAll(keySet);
		//synchronized(pos){
			if(pos >= keyList.size())
				pos = 0;
			server = keyList.get(pos);
			pos++;
			
		//}
		System.out.println(server);
		return server;
	}

}
