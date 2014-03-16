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
        
        ComputeResourceGenerator resourceGen =
                            new ComputeResourceGenerator(maxTime);
        
        CircularQueue<ComputeResource> circQueue = 
                            new CircularQueue<ComputeResource>(circularSize);
        
        PriorityQueue<Task> priorityQueue =
                            new PriorityQueue<Task>(taskComp, prioritySize);
        
        ProcessGenerator processGen = new ProcessGenerator();
        
        //add processes to processGen
        for(Process p : processes)
            processGen.addProcess(p.getPeriod(), p.getComputeTime());
        
        boolean deadlineMissed = false;
        int timeStep = 0;
        
        while(!deadlineMissed)
        {
            List<ComputeResource> resources = resourceGen.getResources();
            
            //@see step 1
            for(ComputeResource resource : resources)
            {
                try
                {
                    circQueue.enqueue(resource);
                }
                
                catch (FullQueueException e)
                {
                    break;
                }
            }
            
            //@see step 2
            List<Task> tasks = processGen.getTasks(timeStep);
            
            for(Task task : tasks)
            {
                try
                {
                    priorityQueue.enqueue(task);
                }
                
                catch (FullQueueException e)
                {
                    //TODO Failure: Too many tasks!
                }
            }
            
            //@see step 3 + 4: Apply resources + remove complete tasks
            
            //@see step 5: Have we missed a deadline?
        }
        
        
    }
}
