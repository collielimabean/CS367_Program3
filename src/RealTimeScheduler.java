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
import java.util.ArrayList;

/**
 * This class determines whether a set of processes and tasks can be executed
 * without missing any deadlines.
 */
public class RealTimeScheduler
{    
    /**
     * Returns the least common multiple (LCM) of two nonnegative numbers.
     * @param a the first number to get LCM
     * @param b the second number to get LCM
     * @return the least common multiple of both numbers
     * @throws IllegalArgumentException if non-positive numbers are passed in
     */
    static int lcm(int a, int b)
    {
        return (a * b) / gcd(a, b);
    }
    
    /**
     * Computes the greatest common denominator (GCD) of two positive numbers
     * using Euclid's algorithm.
     * @param a the first number to get GCD
     * @param b the second number to get GCD
     * @return the greatest common denominator of both numbers
     * @throws IllegalArgumentException if non-positive numbers are passed in
     */
    static int gcd(int a, int b)
    {
        if(a < 0 || b < 0)
            throw new IllegalArgumentException();
        
        if(b == 0)
            return a;
        
        return gcd(b, a % b);
    }
        
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
            System.err.println("Failed to parse configuration file. " 
                                + "Please make sure the file exists, or that"
                                + " you typed it in correctly! \n");
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
        
        //holds final deadline (lcm of all processes)
        int deadline = 1;
        
        //add processes to processGen and determine deadline
        for(Process p : processes)
        {
            processGen.addProcess(p.getPeriod(), p.getComputeTime());
            deadline = lcm(deadline, p.getPeriod()); 
        }
        
        for(int timeStep = 0; timeStep <= deadline; timeStep++)
        {
            //grab all available compute resources and add to circle queue
            for(ComputeResource resource : resourceGen.getResources())
            {
                try
                {
                    circQueue.enqueue(resource);
                }
                
                catch (FullQueueException e)
                {
                    //waste the rest of the resources
                    break;
                }
            }
            
            //grab all generated tasks and add to priority queue
            for(Task task : processGen.getTasks(timeStep))
            {
                try
                {
                    priorityQueue.enqueue(task);
                }
                
                catch (FullQueueException e)
                {
                    //if this ever happens
                    //we know that we missed a deadline
                    //at the previous timeStep
                    System.out.println("Deadline missed at timestep " 
                                                              + (timeStep - 1));
                    return;
                }
            }
            
            List<Task> incompleteTasks = new ArrayList<Task>();            
            
            //apply resources until there are no more tasks to apply OR
            //there are no more resources to apply
            while(!circQueue.isEmpty() && !priorityQueue.isEmpty())
            {
                try
                {
                    ComputeResource resource = circQueue.dequeue();
                    Task highest = priorityQueue.dequeue();
                    
                    highest.updateProgress(resource.getValue());
                    
                   if(!highest.isComplete())
                        incompleteTasks.add(highest);
                }
                
                catch (EmptyQueueException e)
                {
                    System.out.println("Queue is empty, " +
                    							   " this shouldn't happen!");
                    return;
                }
            }
            
            //add popped tasks back into queue
            for(Task t : incompleteTasks)
            {
                try
                {
                    priorityQueue.enqueue(t);
                }
                
                catch (FullQueueException e)
                {
                	System.out.println("Queue is full, " +
                								   "this shouldn't happen!");
                    return;
                }
            }
            
            //check if any deadline missed
            try 
            {
                Task top = priorityQueue.peek();
                
                if(top.missedDeadline(timeStep))
                {
                    System.out.println("Deadline missed at timestep " + 
                    												  timeStep);
                    return;
                }
            } 
            
            catch (EmptyQueueException e)
            {
            	//queue is empty, all tasks completed
            }
            
        }
        
        //Success
        System.out.println("Scheduling complete after "
                                        + deadline + " timesteps.");
        
    }
}