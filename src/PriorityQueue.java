import java.util.Comparator;


public class PriorityQueue<E> implements QueueADT<E> 
{
    private static final int INITIAL_SIZE = 10;
    
    private E[] queue;
    private Comparator<E> comparator;
    private int numItems;
    
    public PriorityQueue(Comparator<E> comparator)
    {       
        this.comparator = comparator;
        queue = (E[]) new Object[INITIAL_SIZE];
        numItems = 0;
    }
    
    @Override
    public boolean isEmpty() 
    {
        return numItems == 0;
    }

    @Override
    public boolean isFull() 
    {
        return numItems == queue.length;
    }

    @Override
    public E peek() throws EmptyQueueException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E dequeue() throws EmptyQueueException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void enqueue(E item) throws FullQueueException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public int capacity()
    {
        return queue.length;
    }

    @Override
    public int size()
    {
        return numItems;
    }

}
