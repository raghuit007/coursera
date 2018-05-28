import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
       
    	
    	/* This is 0/1 knapsack problem without repetetions (cleaner code)
    	 * 
    	 * You are given a set of bars of gold and your goal is to take as much gold as possible into your bag. There is just one copy of each bar and for each bar you can either take it or not (hence you cannot take a fraction of a bar).
           Problem Description
           Task. Given 𝑛 gold bars,  nd the maximum weight of gold that  ts into a bag of capacity 𝑊 .
    	 * 
    	 * 
    	 * Used DP technique to create a 2D array. Each subproblem consists of taking only that weight and creating a 
    	 * smaller knapsack such that the smaller knapsack is most optimized one
    	 * 
    	 * Hit the nasty seg fault error which was rectified taking dynamically sized arraylist
    	 * 
    	 * 
    	 */
    	 

    	
    	
    	List<List<Integer>> result = new ArrayList<>(); //declare vector instead of array
    	
    	//set row 1 to 0
    	
    	int temp=0;
    	ArrayList<Integer> row1 = new ArrayList<Integer>();
    	while(temp<=W) {
    		row1.add(0);
    		temp++;
    	}
    	
    	result.add(row1);
    	
    	//fill the remaining rows
    	/*
    	 * row = each element in the array w
    	 * column = values from 0 to W
    	 * This makes sure that we are calculating the max knapsack given that weight only which will be aggregated in the end
    	 */
    	
    	for (int i=0;i<w.length;i++) {
    		int j=1;
    		ArrayList<Integer> row = new ArrayList<Integer>();
    		row.add(0);
    		while (j<=W) {
    			if (w[i]>j) {
    				row.add(result.get(i).get(j));
    			}
    			
    			else if (w[i]==j) {
    				row.add(w[i]);
    			}
    			
    			else {
    				//System.out.println("i'm in the else case");
    				int val_2_add = Math.max(w[i]+result.get(i).get(j-w[i]), result.get(i).get(j));
    				row.add(val_2_add);
    			}
    			j++;
    		}
    		
    		result.add(row);
    	}
    	
        return result.get(w.length).get(W);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}
