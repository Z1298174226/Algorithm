package balanceLoad;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Test {
	public static void main(String[] args){
//		HashSelect hash = new HashSelect();
//		WeightSelect weight = new WeightSelect();
//		Select task = new Select();
//		HashSelect hashtask = new HashSelect();
		WeightSelect weightTask = new WeightSelect();
		AddServer addTask = new AddServer();
		ExecutorService executorService  = Executors.newFixedThreadPool(20);
	    for(int i = 0;i < 100; i++){
	    //	executorService.submit(task);
	    //	executorService.submit(hashtask);
	    	try{
	    	executorService.submit(addTask);
	    	Future<String>  future = executorService.submit(weightTask);
	    	System.out.println(future.get());	    
	    	}catch(InterruptedException ex){
	    		
	    	}catch(ExecutionException ex){
	    		
	    	}
	    }
	    
		

//		System.out.println(hash.hashSelect());

//		System.out.println(weight.weightSelect());
	}
	

}
