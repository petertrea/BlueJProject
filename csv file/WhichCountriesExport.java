
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfIntest) {        
        //For each row in the CSV File
        for (CSVRecord record : parser){ 
            //Look at the “Exports Column of that row
            String export =record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfIntest)){
                //If so, write down the “Country” from that row
                String country = record.get(0);
                System.out.println(country);
            }      
        }
    }
    public void whoExportsCoffee(){ 
        FileResource fr = new FileResource();
        CSVParser parser =fr.getCSVParser();
        listExporters(parser, "coffee");
    }
}
