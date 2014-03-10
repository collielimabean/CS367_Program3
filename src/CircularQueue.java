
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class CircularQueue<E> implements QueueADT<E>
{
    private List<E> queue;
    private Comparator<E> comparator;
    private int numItems;
    
    public CircularQueue(Comparator<E> comparator)
    {
        this.comparator = comparator;
        queue = new ArrayList<E>();
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
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int size()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
