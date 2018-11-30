/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicprogramming;

/**
 *
 * @author Gaming PC
 */
public class KnapsackProblemDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int maxWeight = 5;
        int[] weights = { 2, 1, 3, 2};
        int[] values = { 12, 10, 20, 15};
        
        int[][] result = knapsack(maxWeight, weights, values);
        printArray(result);
        printFinalAnswer(result, maxWeight, weights, values);
    }
    
    public static void printFinalAnswer(int[][] result, int maxWeight, int[] weights, int[] values) {
        int xSize = result.length;
        int ySize = result[0].length;
        System.out.println("Maximum Value: " + result[xSize-1][ySize-1]);
        System.out.println("Which included :");
        int i = xSize -1;
        int x = ySize-1;
        while(i != 0){
            if(result[i][x] == result[i-1][x]) {
                i = i-1;
            }else{
                System.out.println("Item " + i + " has included which has weight as " + weights[i-1] + " and values as " + values[i-1]);
                x = x - weights[i-1];
                i = i-1;
            }
        }
    }
    
    public static int[][] knapsack(int maxWeight,int[] weights,int[] values) {
        int nItem = weights.length;
        int[][] result = new int[nItem+1][maxWeight+1];
        
        for(int i = 1; i <= nItem; ++i) {
            for(int j = 1; j <= maxWeight; ++j) {
                if(j - weights[i-1] >= 0) {
                    int max = result[i-1][j];
                    if(values[i-1] + result[i-1][j-weights[i-1]] > max) {
                        max = values[i-1] + result[i-1][j-weights[i-1]];
                    }
                    result[i][j] = max;
                }else{
                    result[i][j] = result[i-1][j];
                }
            }
        }
        
        return result;
    }
    
    public static void printArray(int[][] result) {
        for(int i = 0; i < result.length; ++i) {
            for(int j = 0; j < result[i].length; ++j) {
                System.out.print(result[i][j] + ""
                        + "\t");
            }
            System.out.println("");
        }
        System.out.println("\n-------------------------------\n");
    }
    
}
