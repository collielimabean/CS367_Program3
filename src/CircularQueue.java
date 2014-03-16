///////////////////////////////////////////////////////////////////////////////
// Main Class File:  RealTimeScheduler.java
// File:             CircularQueue.java
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

import java.util.List;
import java.util.ArrayList;

public class CircularQueue<E> implements QueueADT<E>
{
    private List<E> queue;
    private int numItems;
    
    public CircularQueue(int capacity)
    {
        queue = new ArrayList<E>(capacity);
        
        numItems = 0;
    }
    
    public boolean isEmpty()
    {
        return numItems == 0;
    }

    public boolean isFull()
    {
        return numItems == queue.size();
    }

    public E peek() throws EmptyQueueException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public E dequeue() throws EmptyQueueException 
    {
        return null;
    }

    public void enqueue(E item) throws FullQueueException
    {
        // TODO Auto-generated method stub

    }

    public int capacity() 
    {
        return queue.size();
    }

    public int size()
    {
        return numItems;
    }

}
