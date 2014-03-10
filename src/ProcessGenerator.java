
import java.util.ArrayList;

public class ProcessGenerator
{
    private ArrayList<Process> processes;
    
    void addProcess(int period, int computeTime)
    {
        processes.add(new Process(period, computeTime));
    }
    
    ArrayList<Task> getTasks(int time)
    {
        ArrayList<Task> tasks = new ArrayList<Task>();
        
        //TODO if time = 0?
        for(Process process : processes)
            if(time % process.getPeriod() == 0)
                tasks.add(new Task(process, time));
        
        return tasks;
    }
}