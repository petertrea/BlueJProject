
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){ 
        //Start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser){ 
            largestSoFar = getLargestOfTwo(currentRow,largestSoFar);                
        }
        //The largestSoFar is our answer
        return largestSoFar;
    }
    public CSVRecord hottestInManyDays(){ 
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
             // use method to get largest in file
            CSVRecord currentRow =hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow,largestSoFar);            
            }        
        // the largestSoFar is the answer
        return largestSoFar;
    }
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar){ 
        if (largestSoFar == null) { 
            largestSoFar = currentRow;
            } else {
                //Other wise  //Check if currentRow’s temperature > largestsoFar’s
                double currentTemp = Double.parseDouble(currentRow.get(2));
                double largestTemp = Double.parseDouble(largestSoFar.get(2));
                if (currentTemp > largestTemp) {
                    // If so update largestSoFar to currentRow
                    largestSoFar = currentRow;
                    }                  
                }
        return largestSoFar; 
    }
    public void testHottestInDay(){ 
        FileResource fr = new FileResource();
        //CSVParser parser =fr.getCSVParser();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get(1) + " at " + largest.get(0));
    }
     public void testHottestInManyDay(){        
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get(1) + " at " + largest.get(13));
    }
}
