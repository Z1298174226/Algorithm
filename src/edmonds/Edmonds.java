package edmonds;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;


public class Edmonds {
   private boolean[] used;
   private int[] girl;
   private int[][] path;
   private boolean[][] another;
   private Queue<Integer>[] queue ;
   @SuppressWarnings("unchecked")
public Edmonds(int Num){
	   queue = (Queue<Integer>[]) new Queue[Num];
	   another = new boolean[Num][Num];
	   used = new boolean[Num];
	   Arrays.fill(used, false);
	   girl = new int[Num];
	   Arrays.fill(girl, -1);
	   path = new int[Num][Num];
	   for(int i = 0;i < Num;i++){
		   Arrays.fill(path[i], 0);
		   Arrays.fill(another[i], false);
	   }
   }
   public Edmonds(In in){
	   this(in.readInt());
	   for(int i = 0 ; i < used.length; i++){
		   for(int j = 0; j < used.length; j++){
			   path[i][j] = in.readInt();
		   }
	   }
   }
   
   public boolean find(int x){
	   for(int j = 0;j < used.length;j++){
		   if(path[x][j] == 1 && !another[x][j]){
			   if(!used[j]){
				   used[j] = true;
				   girl[j] = x;
				   another[x][j] = true;
				   return true;
			   }
			   else{
				 if(find(girl[j]))
					 return true;
			   }
		   }
	   }
	   return false;
   }
   
 
     
   public static void main(String[] args){
	   In in = new In(args[0]);
	   int num = 0;
	   Edmonds edmonds = new Edmonds(in);
	   for(int i = 0;i < edmonds.used.length;i++){
		   if(edmonds.find(i))
			   num++;
	   }
	   System.out.println("The perfect matching is :  " + num);
   }
	
}
