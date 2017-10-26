package floyd;

import edu.princeton.cs.algs4.In;

public class FloydDemo {
	private static int[] matrix;
	private static int[][] initMatrix;
	public FloydDemo(int Num,In in){
		matrix = new int[Num];
		initMatrix = new int[Num][Num];
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++){
				initMatrix[i][j] = in.readInt();
			}
		}
	}
	public void floyd(int[][] path,int[][] dist){
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++){
				dist[i][j] = initMatrix[i][j];
		        path[i][j] = j;
			}
		}
			
		for(int k = 0; k < matrix.length; k++){
			for(int i = 0; i < matrix.length; i++){
				for(int j = 0; j < matrix.length; j++){
					if(dist[i][j] > dist[i][k] + dist[k][j]){
						dist[i][j] = dist[i][k] + dist[k][j];
						path[i][j] = k;
					}
					    
				}
			}
		}		
		 System.out.println("floyd: \n");
		    for (int i = 0; i < matrix.length; i++) {
		        for (int j = 0; j < matrix.length; j++)
		            System.out.printf("%2d  ", dist[i][j]);
		        System.out.printf("\n");
		    }
				}
	public static void main(String[] args){
		In in = new In(args[0]);
		int Num = in.readInt();
		int[][] path = new int[Num][Num];
		int[][] dist = new int[Num][Num];
		new FloydDemo(Num,in).floyd(path,dist);
	}
	

}
