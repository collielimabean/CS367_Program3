///////////////////////////////////////////////////////////////////////////////
// Main Class File:  RealTimeScheduler.java
// File:             TaskComparator.java
// Semester:         CS367 Spring 2014
//
// Author:           William Jen <wjen@wisc.edu>
// CS Login:         jen
// Lecturer's Name:  Professor Jim Skrentny
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
// Pair Partner:     Allen Hung
// CS Login:         ahung
// Lecturer's Name:  Professor Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Comparator;

/**
 * This class compares two tasks by their deadline.
 * @see Comparator<E> for interface
 */
public class TaskComparator implements Comparator<Task> 
{
    
    /**
     * Compares two tasks by deadline. If task1 has an earlier deadline, a 
     * negative number will be returned, and later a positive number. If they
     * are equivalent, returns 0.
     * @param task1 A task with a deadline to compare
     * @param task2 A second task with a deadline to compare
     * @return -1 if task1 has an earlier deadline, 1 if task1 later, and 0
     * if same deadline
     * @throws IllegalArgumentException if task1 or task2 is null
     */
    public int compare(Task task1, Task task2)
    {
        if(task1 == null || task2 == null)
            throw new IllegalArgumentException();
        
        int deadline1 = task1.getDeadline();
        int deadline2 = task2.getDeadline();
        
        if(deadline1 < deadline2)
            return -1;
        
        else if(deadline1 > deadline2)
            return 1;
        
        return 0;
    }
    
    /**
     * Not implemented in the TaskComparator
     * @throws UnsupportedOperationException Unsupported operation.
     */
    public boolean equals(Object o)
    {
        throw new UnsupportedOperationException();
    }
    
}
