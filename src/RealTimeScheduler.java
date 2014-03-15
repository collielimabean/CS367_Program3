///////////////////////////////////////////////////////////////////////////////
// Title:            RealTimeScheduler
// Files:            CircularQueue.java, PriorityQueue.java,
//                   TaskComparator.java, RealTimeScheduler.java, README.txt
// Semester:         CS367 Spring 2014
//
// Author:           William Jen
// Email:            wjen@wisc.edu
// CS Login:         jen
// Lecturer's Name:  Professor Jim Skrentny
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
// Pair Partner:     Allen Hung
// Email:            athung2@wisc.edu
// CS Login:         ahung
// Lecturer's Name:  Professor Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.List;

/**
 * This class determines whether a set of processes and tasks can be executed
 * without missing any deadlines.
 */
public class RealTimeScheduler
{    
    /**
     * This method is the entry point for the RealTimeScheduler application.
     * @param args Command-line arguments
     */
    public static void main(String[] args)
    {
        if(args.length != 1)
        {
            System.err.println("Usage: java RealTimeScheduler <file>");
            return;
        }
        
        //read in file
        ConfigurationFileReader reader = null;
        
        try
        {
            reader = new ConfigurationFileReader(args[0]);
        }
        
        catch (BadConfigurationFileException e)
        {
            System.err.println("Failed to parse configuration file. \n");
            System.err.println("Your configuration file should be: \n" + 
                               "\t <Max number of computable resources> \n" + 
                               "\t <Capacity of Circular Queue> \n" + 
                               "\t <Capacity of Priority Queue> \n" + 
                               "\t <Process 1 period> <Process 1 time> \n" + 
                               "\t <Process n period> <Process n time> \n");
            return;
        }
        
        //Get parsed data from config reader
        int maxTime = reader.getMaxComputeTime();
        int circularSize = reader.getCircularQueueCapacity();
        int prioritySize = reader.getPriorityQueueCapacity();
        List<Process> processes = reader.getProcesses();
        
        //initialize comparators, resource generators, and queues
        TaskComparator taskComp = new TaskComparator();
        
        ComputeResourceGenerator generator = new ComputeResourceGenerator(
                                                                       maxTime);
        
        CircularQueue<Task> circQueue = new CircularQueue<Task>(
                                                        taskComp, circularSize);
        
        PriorityQueue<Task> priorQueue = new PriorityQueue<Task>(
                                                        taskComp, prioritySize);
        
        ProcessGenerator processGen = new ProcessGenerator();
        
        //add processes to processGen
        for(Process p : processes)
            processGen.addProcess(p.getPeriod(), p.getComputeTime());
        
        
        
    }
}
