import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        //calcluate the hash before hand
        
        int h = s.hashCode();
        int m = s.length(), n = t.length();
        
        
        List<Integer> occurrences = new ArrayList<Integer>();
        double [] hashes = new double[n-m+1];
        
        // change the code here to implement fast version of Rabin-Karp algorithm
        // first lets try to get it working with the default hash computed with Java (hash code)
        
        // we need to pre compute the hash, rather than calculating it on the fly
        //else there is a time limit exceeded error
        //get a prime number >> m*n (p)
        
        double p =250000003003D;
        double rand = 101149D;
        double pHash = polyHash(s,p,rand);
        hashes =  preComputeHash(t,m,p,rand);
        for (int i=0;i<=n-m;i++) {
        	 // System.out.println("value if i-->"+i);
        	//  System.out.println("value of hash is-->"+hashes[i]);
        	//System.out.println("substring to compare-->"+t.substring(i,((i+m))));
        	  if (pHash!=hashes[i]) {
        		  System.out.println("inside if!!!!!");
        		  continue;
        	  }
        	  else {
        		  System.out.println("i m here!!!!!");
        		  if (t.substring(i,i+m).equals(s)) {
        			  occurrences.add(i);
        		  }
        	  }
        	
        	}
        
        return occurrences;
        }

	private static double[] preComputeHash(String t, int n, double p, double rand) {
		// TODO Auto-generated method stub
		//System.out.println("i m here!!!!!");
		double [] hashes = new double[t.length()-n+1];
		String s = t.substring((t.length()-n),t.length());
		hashes[hashes.length-1] = polyHash(s,p,rand);
		double y = 0D;
		for (int i=1;i<=n;i++) 
			y = (y*rand)%p;
		for (int j=(t.length()-n-1);j>=0;j--){
		//	System.out.println("i m here inside for loop!!!!!");
		//	System.out.println("value of j-->"+j);
			hashes[j] = ((rand*hashes[j+1]+ t.charAt(j) - y*t.charAt(j+n)))%p;
		
		}
		return hashes;
	}
        
        
        
       // aabbvcs  p = 2 
	
	 
        
    

    private static double polyHash(String s, double p, double rand) {
		// TODO Auto-generated method stub
    	double hash =0D;
    	
    	for (int i=s.length()-1;i>=0;i--) {
    		hash = (hash*rand+ s.charAt(i))%p;
    	}
    	
		return hash;
	}



	static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}


/**
 import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        //calcluate the hash before hand
        
        int h = s.hashCode();
        int m = s.length(), n = t.length();
        
        
        List<Integer> occurrences = new ArrayList<Integer>();
        double [] hashes = new double[n-m+1];
        
        // change the code here to implement fast version of Rabin-Karp algorithm
        // first lets try to get it working with the default hash computed with Java (hash code)
        
        // we need to pre compute the hash, rather than calculating it on the fly
        //else there is a time limit exceeded error
        //get a prime number >> m*n (p)
        
        double p =492876847D;
        double rand = 256D;
        
        double pHash = polyHash(s,p,rand);
        hashes =  preComputeHash(t,m,p,rand);
        
        //for (int j=0;j<)
        
        
        return occurrences;
        }

	private static double[] preComputeHash(String t, int m, double p,double rand) {
		
		double preHashes [] = new double[t.length()-m+1];
		String firstSub = t.substring(0,m);
		
		preHashes[preHashes.length-1] = polyHash(firstSub,p,rand);
		
		for (int i=t.length()-m-1;i>=0;i++) {
			//(d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;
			preHashes[i] = ((rand*(preHashes[i+1]-t.charAt(i)+ t.charAt(i+m))))%p;
		}
		return preHashes;
		
	}
        
        
        
     private static double polyHash(String s, double p,double rand) {
		// TODO Auto-generated method stub
    	double hash =0D;
    	
    	for (int i=1;i<=s.length()-1;i++) {
    		hash = (rand*hash + s.charAt(i))%p;
    	}
    	
    	
    	
		return hash;
	}



	static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}


**/


