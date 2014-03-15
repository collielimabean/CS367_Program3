///////////////////////////////////////////////////////////////////////////////
// Main Class File:  RealTimeScheduler.java
// File:             ProcessGenerator.java
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
import java.util.ArrayList;

/**
 * This class tracks all processes in the system.
 */
public class ProcessGenerator
{
    private ArrayList<Process> processes;
    
    /**
     * Adds a Process with a specified period and compute time to a list
     * of processes.
     * @param period The period of a process
     * @param computeTime The compute time of a process
     */
    public void addProcess(int period, int computeTime)
    {
        processes.add(new Process(period, computeTime));
    }
    
    /**
     * Returns a list of tasks that are generated at a multiple of the
     * specified time.
     * @param time Time of the tasks to retrieve
     * @return A list of all tasks with the requested time
     */
    public ArrayList<Task> getTasks(int time)
    {
        ArrayList<Task> tasks = new ArrayList<Task>();
        
        //TODO if time = 0?
        for(Process process : processes)
        {
            if(time % process.getPeriod() == 0)
            {
                tasks.add(new Task(process, time));
            }
        }
        
        return tasks;
    }
}