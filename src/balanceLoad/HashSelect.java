package balanceLoad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class HashSelect implements Callable<String> {
	private Map<String,Integer> serverMap = new HashMap<String,Integer>();
	public HashSelect(){
		server = null;
	}
	private String server;
	
	@Override
	public String call(){
		serverMap.putAll(IpMap.serverWeightMap);
		Set<String> set = new HashSet<String>();
		set = serverMap.keySet();
		List<String> list = new ArrayList<String>();
		list.addAll(set);
		String remoteIP = "127.0.0.1";
	    int hashCode = remoteIP.hashCode();
	    int serverListSize = list.size();
	    int pos = hashCode % serverListSize;
	    System.out.println(server = list.get(pos));
	    return server;
	}

}
