package tree;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class CompletelyTree {
    static final int[]  array1 =  {0,1,2,3,4,5,6,7,8,(Integer) null,(Integer) null,(Integer) null};
    
    public static boolean isCompletelyTree(int[] array){
    	Queue<Integer> q = new PriorityQueue<Integer>();
    	int index = 0;
    	q.add(array[index]);
    	while(q.poll()!=null){
    		int left = index<<1+1;
    		int right = index<<1+2;
    		q.add(array[left]);
    		q.add(array[right]);
    	}
    	while(!q.isEmpty()){
    	if(q.poll()!=null){
    		return false;
        	}
    	}
    	return true;
    }
    
    public static void main(String[] args){
    	System.out.println(isCompletelyTree(array1));
    }
}
