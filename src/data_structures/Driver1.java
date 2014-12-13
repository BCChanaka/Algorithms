/*  Driver.java
    A simple driver program for project #1.  Enable the implementation you 
    want to test.  
    CS310 Fall 2011
    Alan Riggins
    
//////////////////////////////////////////////////////////////////////    
   ** Correct output for UnorderedArray implementation is: **
    
Now testing remove(), the elements must print in proper order.
AAAA
CCCC
PPPP
ZZZZ
Testing the iterator using the iterable interface.
Elements may print in any order.
A priority=5
B priority=7
C priority=2
D priority=10
E priority=5
F priority=1
G priority=5
H priority=1

Now testing remove(), the elements must print in proper order.
F priority=1
H priority=1
C priority=2
A priority=5
E priority=5
G priority=5
B priority=7
D priority=10

//////////////////////////////////////////////////////////////////////
*/  

package data_structures;

public class Driver1 {

    public Driver1() {
        doStringTest();
        doJobTest();
        }
        
    private void doStringTest() {
        PriorityQueue<String> pq = 
                    //new OrderedArrayPriorityQueue<String>();
                   //new UnorderedArrayPriorityQueue<String>();
                    //new OrderedListPriorityQueue<String>();
                    new UnorderedListPriorityQueue<String>();                                        
        pq.insert("ZZZZ");
        pq.insert("PPPP");
        pq.insert("AAAA");
        pq.insert("CCCC");
        
        System.out.println("Now testing remove(), the elements must print" +
                " in proper order.");
        while(!pq.isEmpty())
            System.out.println(pq.remove());
        }  
        
    private void doJobTest() {
        PriorityQueue<Job> pq = 
                    //new OrderedArrayPriorityQueue<Job>();
                    //new UnorderedArrayPriorityQueue<Job>();
                   // new OrderedListPriorityQueue<Job>();
                    new UnorderedListPriorityQueue<Job>();        
        pq.insert(new Job(5,"A"));      
        pq.insert(new Job(7,"B"));
        pq.insert(new Job(2,"C"));
        pq.insert(new Job(10,"D"));
        pq.insert(new Job(5,"E"));
        pq.insert(new Job(1,"F"));
        pq.insert(new Job(5,"G"));
        pq.insert(new Job(1,"H"));
        
        System.out.println("Testing the iterator using the iterable interface.");
        System.out.println("Elements may print in any order.");
        for(Job j : pq)
            System.out.println(j);
            
        System.out.println();
        
        System.out.println("Now testing remove(), the elements must print" +
                " in proper order.");
        while(!pq.isEmpty())
            System.out.println(pq.remove());
        }
        
    private class Job implements Comparable<Job> {
        private int priority;
        private String label;
        
        public Job(int p, String s) {
            priority = p;
            label = s;
            }
            
        public int compareTo(Job j) {
            return priority-j.priority;
            }
            
        public String toString() {
            return label + " priority=" + priority;
            }
        }    
        
    public static void main(String [] args) {
        new Driver1();
        }
    }