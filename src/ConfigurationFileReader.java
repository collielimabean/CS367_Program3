///////////////////////////////////////////////////////////////////////////////
// Main Class File:  RealTimeScheduler.java
// File:             ConfigurationFileReader.java
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class attempts to read in the configuration file and reads the 
 * appropriate values.
 */
public class ConfigurationFileReader 
{
    private File path;
    
    private List<Process> processes;
    private int maxComputeTime;
    private int capacityCircular;
    private int capacityPriority;
    
    /**
     * Constructs a ConfigurationFileReader object with the specified path.
     * @param filePath A path to the file to be parsed
     * @throws BadConfigurationFileException if the configuration file is not
     * valid
     */
    public ConfigurationFileReader(String filePath)
                                      throws BadConfigurationFileException
    {
        path = new File(filePath);
        
        processes = new ArrayList<Process>();
        maxComputeTime = 0;
        capacityCircular = 0;
        capacityPriority = 0;
        
        boolean success = parseFile();
        
        if(!success)
            throw new BadConfigurationFileException();
    }
    
    /**
     * Reads in the raw text file.
     * @return List of Strings where each String is a line of text.
     * @throws FileNotFoundException if the file cannot be read or found
     */
    private List<String> readFile() throws FileNotFoundException
    {
        List<String> lines = new ArrayList<String>();
        
        Scanner input = new Scanner(path);
        
        while(input.hasNextLine())
            lines.add(input.nextLine());
        
        input.close();
        
        return lines;
    }
    
    /**
     * Parses the max compute time, sizes of the queues, and the processes.
     * @return true if successful, false otherwise
     */
    private boolean parseFile()
    {
        try
        {
            List<String> lines = readFile();
            
            if(lines.size() < 3)
                return false;
            
            maxComputeTime = Integer.parseInt(lines.get(0));
            capacityCircular = Integer.parseInt(lines.get(1));
            capacityPriority = Integer.parseInt(lines.get(2));
            
            for(int i = 3; i < lines.size(); i++)
            {
                String[] processData = lines.get(i).split(" ");
                
                //any more or less than 2 elements mean
                //invalid file
                if(processData.length != 2)
                    return false;
                
                int period = Integer.parseInt(processData[0]);
                int compTime = Integer.parseInt(processData[1]);
                
                processes.add(new Process(period, compTime));
            }
        }
        
        catch(NumberFormatException | FileNotFoundException e)
        {
            return false;
        }
        
        return true;
    }
    
    /**
     * Gets the maximum compute time from the configuration file.
     * @return max compute time
     */
    public int getMaxComputeTime()
    {
        return maxComputeTime;
    }
    
    /**
     * Gets the size of the circular queue. 
     * @return size of the circular queue.
     */
    public int getCircularQueueCapacity()
    {
        return capacityCircular;
    }
    
    /**
     * Gets the size of the priority queue.
     * @return size of the priority queue.
     */
    public int getPriorityQueueCapacity()
    {
        return capacityPriority;
    }
    
    /**
     * Gets all processes in the configuration file.
     * @return a List containing all processes.
     */
    public List<Process> getProcesses()
    {
        return processes;
    }
}
