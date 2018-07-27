/*
 * Subset.java
*/

public class Subset {
    public static void main(String[] args)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        int k = Integer.parseInt(args[0]);
        int count = 0;
        
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            
            if (k != 0) {
                if (count < k) {
                rq.enqueue(s);
                count++;
                }
                else {
                    count++;
                    int r = StdRandom.uniform(count);
                    if (r < k) {
                        rq.dequeue();
                        rq.enqueue(s);
                    }
                }
            }
        }
            
        for (int j = 0; j < k; j++) {
            System.out.println(rq.dequeue());
        }
    }
}