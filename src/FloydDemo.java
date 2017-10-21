import edu.princeton.cs.algs4.In;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qtfs on 2017/10/12.
 */
public class FloydDemo {
    private static int[] matrix;
    private static int[][] initMatrix;
    private List[] list ;
    public FloydDemo(int Num,In in){
        matrix = new int[Num];
        initMatrix = new int[Num][Num];
//        list = (ArrayList<Integer>[]) new ArrayList[Num];
//        for(int i = 0; i < Num; i++) {
//            list[i] = new ArrayList<Integer>();
//        }
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
              //  path[i][j] = j;
                path[i][j] = j + 1;
            }
        }
        for(int k = 0; k < matrix.length; k++){
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix.length; j++){
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        //path[i][j] = k;
                        path[i][j] = k + 1;

                    }

                }
            }
        }
       // List<Integer> list = new ArrayList<Integer>();
        int temp = 789;
        int src = 5;
        List<Integer> list = new ArrayList<Integer>();
        list.add(src);
        itereator(list, path, src, temp);
        list.add(temp);
//        list.add(temp);
//        while(temp != path[src][temp]) {
//            temp = path[src][temp];
//            list.add(temp);
//        }
//        list.add(src);

        System.out.println("floyd: \n");
        for(int i : list) {
            System.out.print( "->" + (i + 1));
        }


    }
    private void itereator(List<Integer> list, int[][] path, int start, int end) {
        int temp = path[start][end] - 1;
        if(temp == end) return;
        itereator(list, path, start, temp);
        list.add(temp);
        itereator(list, path, temp, end);
    }
    public static void main(String[] args){

        In in = new In(args[0]);
        int Num = in.readInt();
        int[][] path = new int[Num][Num];
        int[][] dist = new int[Num][Num];
        new FloydDemo(Num,in).floyd(path,dist);
    }
}