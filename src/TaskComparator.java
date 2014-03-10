import java.util.Comparator;


public class TaskComparator implements Comparator<Task> 
{

    @Override
    public int compare(Task task1, Task task2)
    {
        int deadline1 = task1.getDeadline();
        int deadline2 = task2.getDeadline();
        
        if(deadline1 < deadline2)
            return -1;
        
        else if(deadline1 > deadline2)
            return 1;
        
        return 0;
    }
    
}
