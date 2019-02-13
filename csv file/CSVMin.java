
/**
 * This File is for Weather CSV Problem assignment (see PDF file in week 3)
 * it is good for Class DirectoryResource
 * FileResource
 * CSVParser
 * fine path usage
 * 
 * @Ping Cui 
 * @version 1.0
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser){ 
        //Start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser){ 
            // Sometimes there was not a valid reading at a specific hour, 
            //so the temperature field says -9999
            if (!currentRow.get(1).contains("-9999")) {
                smallestSoFar = getSmallestOfTwo(currentRow,smallestSoFar,1); 
            } else { 
                //System.out.println(currentRow.get(13));
            }              
        }
        //The smallestSoFar is our answer
        return smallestSoFar;
    }
     public CSVRecord coldestHourInManyFiles(){ 
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
             // use method to get largest in file
            CSVRecord currentRow =coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currentRow,smallestSoFar,1);            
            }        
        // the largestSoFar is the answer
        return smallestSoFar;
    }
    
    public String fileWithColdestTemperature() {
        // Directory Resource Usage
        DirectoryResource dr = new DirectoryResource();
        String fileName = null;
        // iterate over files
        for (File f : dr.selectedFiles()){
            //https://docs.oracle.com/javase/8/docs/api/java/io/File.html
            fileName = f.getPath();
            }                 
        return fileName;        
    }
    
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar,int colomn){ 
        if (smallestSoFar == null) { 
            smallestSoFar = currentRow;
            } else {
                //Other wise  //Check if currentRow’s temperature > largestsoFar’s
                double currentTemp = Double.parseDouble(currentRow.get(colomn));
                //System.out.println(currentTemp);
                double smallestTemp = Double.parseDouble(smallestSoFar.get(colomn));
                //System.out.println(smallestTemp);
                //currentTemp != -9999 &&
                if (currentTemp < smallestTemp) {
                    // If so update largestSoFar to currentRow
                    smallestSoFar = currentRow;
                    }                  
                }
        return smallestSoFar; 
    }
    public CSVRecord lowestHunidityInFile(CSVParser parser){ 
        //Start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser){ 
            //make sure the value you get is not “N/A” before converting it to a number.
            if (!currentRow.get(3).contains("N/A")) {
                smallestSoFar = getSmallestOfTwo(currentRow,smallestSoFar,3);
            } else {                
                //System.out.println(currentRow.get(13));
            }            
        }
        //The smallestSoFar is our answer
        return smallestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){ 
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
             // use method to get largest in file
            CSVRecord currentRow =lowestHunidityInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currentRow,smallestSoFar,3);            
            }        
        // the largestSoFar is the answer
        return smallestSoFar;
    }
    
    public double averageTemperatureInFile (CSVParser parser){ 
        double averageTemp = 0.0;
        double tempTotal = 0.0;
        int rowTotal = 0;
        for (CSVRecord currentRow : parser){ 
            tempTotal += Double.parseDouble(currentRow.get(1)); 
            rowTotal ++;
        }
        averageTemp = tempTotal /rowTotal;
        return averageTemp;
    }
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value){
        double averageTemp = 0.0;
        double tempTotal = 0.0;
        int rowTotal = 0;
        for (CSVRecord currentRow : parser){
            int currHumidity = Integer.parseInt(currentRow.get(3));
            if (currHumidity >= value){
                tempTotal += Double.parseDouble(currentRow.get(1)); 
                rowTotal ++;
            }
        }
        if (rowTotal !=0) {
            averageTemp = tempTotal /rowTotal;
            return averageTemp;
        } else {
            return -1;
        }        
    }
    
    public void testAverageTemperatureWithHighHumidityInFile (){
        FileResource fr = new FileResource();
        CSVParser parser =fr.getCSVParser();
        double averageTemp = averageTemperatureWithHighHumidityInFile (parser, 80);
        if (averageTemp == -1){ 
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println ("Average Temp when high Humidity is " + averageTemp);
        }
    }
    
    public void testAverageTemperatureInFile (){
        FileResource fr = new FileResource();
        CSVParser parser =fr.getCSVParser();
        double averageTemp = averageTemperatureInFile (parser);
        System.out.println ("Average temperature in file is " + averageTemp);
    }
    
    public void testFileWithColdestTemperature() {
        String fileName = fileWithColdestTemperature();
        FileResource fr = new FileResource(fileName);
        CSVParser parser =fr.getCSVParser();
        //CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file " + fileName);
        /*
        System.out.println("Coldest temperature on that day was " + smallest.get(1));
        System.out.println("All the Temperature on the coldest day were:");
        for (CSVRecord currentRow : parser){ 
            System.out.println(currentRow.get(13) + " " + currentRow.get(1));
            } */      
        }
    public void testColdestInDay(){
        // How to use FileResource Class
        FileResource fr = new FileResource();
        // How to use CSVParser
        CSVParser parser =fr.getCSVParser();
        // How to use CSVRecord
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get(1) + " at " + smallest.get(0));
    }
    public void testLowestHunidityInFile(){ 
        FileResource fr = new FileResource();
        CSVParser parser =fr.getCSVParser();
        CSVRecord smallest = lowestHunidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + smallest.get(3) + " at " + smallest.get(13));
        }
     public void testLowestHumidityInManyFiles(){ 
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get(3) + " at " + lowest.get(13));
    } 
     public void testColdestHourInManyFiles(){ 
        CSVRecord lowest = coldestHourInManyFiles();
        System.out.println("Coldest temperatur was " + lowest.get(1) + " at " + lowest.get(13));
    }   

}
