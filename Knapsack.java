import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
    	
    	/* This is 0/1 knapsack problem without repetetions
    	 * 
    	 * Construct a n*w matrix where n is the weight leading upto W (subproblem)
    	 *
    	 * Sub-problem is to fill the smaller knapsack with the greatest weights possible
    	 * 
    	 * 
    	 * I solved it in the worst possible fashion....can  be optimized, but i m lazy
    	 */
    	
    	int result[][] = new int[w.length][W+1];
    	
    	//put first column as 0 as if weight is 0, the max that can be selected is 0
    	// alternatively a row can be added on the top with all 0s for a consistent logic
    	
    	int i=0,j=0;
    	while (j<w.length) {
    		result[i][j]=0;
    		j++;
    	}
    	
    	//fill the first row with a different logic
    	j=1;
    	while (j<=W) {
    		if (w[0]>j) {
    			result[0][j] = 0;
    		}
    		else if (w[0]==j) {
    			result[0][j] = w[0];
    		}
    		else  {
    			result[0][j] = result[0][j-1];
    		}
    		j++;
    	}
    	
    	//fill the remaining matrix as per the logic
    	
    	for (int k=1;k<w.length;k++) {
    		int l = 1;
    		while (l<=W) {
    		//System.out.println("value of l-->"+l);
    			if(w[k]>l) {
    				result[k][l] = result[k-1][l];
    			}
    			else if (w[k]==l) {
    				result[k][l] = w[k];
    			}
    			else {
    			//	System.out.println("k is-->"+k+ " weight is ==>"+w[k]);
    				result[k][l] = Math.max((w[k]+result[k-1][l-w[k]]), result[k-1][l]);
    			}
    			l++;
    		}
    	}
        return result[w.length-1][W];
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

