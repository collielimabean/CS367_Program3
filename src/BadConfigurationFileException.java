///////////////////////////////////////////////////////////////////////////////
// Main Class File:  RealTimeScheduler.java
// File:             BadConfigurationFileException.java
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

/**
 * This exception class is thrown when the input configuration file is not 
 * of the correct format.
 * 
 * <p>
 * The correct format is:
 * <b>Line 1:</b> Max computable resources (integer)
 * <b>Line 2:</b> Size of Circular Queue (integer)
 * <b>Line 3:</b> Size of Priority Queue (integer)
 * <b>Line 4:</b> (Process period) [space] (Process time)
 * </p>
 */
public class BadConfigurationFileException extends Exception 
{

}
