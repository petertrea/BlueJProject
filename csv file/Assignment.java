
/**
 * Write a description of Assignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Assignment {
    //Write a method named tester that will create your CSVParser and call 
    //each of the methods below in parts 2, 3, 4, and 5. 
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser =fr.getCSVParser();
        //String selectRow = countryInfo (parser, "Nauru");
        //System.out.println(selectRow);
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //int numberOfExporters = numberOfExporters(parser, "cocoa");
        //System.out.println(numberOfExporters);
        bigExporters(parser, "$999,999,999,999");        
    }
    public void testStriper(){ 
        String stripedString = signStriper ("$999",",");
        System.out.println(stripedString);
    }
    //Write a method named countryInfo that has two parameters, parser is a 
    //CSVParser and country is a String.
    public String countryInfo (CSVParser parser, String country) { 
        String returnString = "";
        for (CSVRecord record : parser){ 
            //Look at the “Exports Column of that row            
            String eachRow =record.get("Country");
            //System.out.println(eachRow);
            //Check if it contains country
            if (eachRow.contains(country) ){                
                //This method returns a string of information about the country   
                returnString = (record.get(0) + ": "+ record.get(1) + ": " + record.get(2));
            }            
        }
        //or returns “NOT FOUND” if there is no information about the country. This is KEY!!
        if (returnString.length() == 0) {returnString = "NOT FOUND"; } 
         // The format of the string returned is the country, followed by “: “, 
        //followed by a list of the countries’ exports, followed by “: “, 
        //followed by the countries export value. 
        return returnString;
    }
    //Write a void method named listExportersTwoProducts that has three parameters,
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,String exportItem2) { 
        for (CSVRecord record : parser){ 
            //Look at the “Exports Column of that row            
            String eachRow =record.get(1);
            //System.out.println(eachRow);
            //Check if it contains country
            if (eachRow.contains(exportItem1) && eachRow.contains(exportItem2) ) {                
                //If so, write down the “Country” from that row
                String country = record.get(0);
                System.out.println(country);
            }            
        }
    } 
    //Write a method named numberOfExporters , which has two parameters, parser is a 
    //CSVParser, and exportItem is a String.
    public int numberOfExporters(CSVParser parser, String exportItem) {
        //This method returns the number of countries that export exportItem
        int numberOfCountry = 0;
        for (CSVRecord record : parser){             
            //Look at the “Exports Column of that row   
            String eachRow =record.get(1);
            //System.out.println(eachRow);
            //Check if it contains country
            if (eachRow.contains(exportItem)) {                
                //If so, write down the “Country” from that row
                numberOfCountry += 1;
            }            
        }
        return numberOfCountry;
    }
    //Write a void method named bigExporters that has two parameters,
    public void bigExporters(CSVParser parser, String amount) { 
        for (CSVRecord record : parser){ 
            //Look at the “Exports Column of that row            
            String eachRow =record.get(2);
            //System.out.println(eachRow);
            String eachRowNoSign = signStriper(eachRow,",");
            String dollarAmount =signStriper(amount,",");
            //System.out.println(dollarAmount);
            long eachRowNumber = Long.parseLong(eachRowNoSign);
            Long dollarNumber = Long.parseLong(dollarAmount);
            //System.out.println(dollarNumber);
            //System.out.println(eachRowNumber);
            //Check if it contains country
            if (eachRowNumber > dollarNumber ) {                
                //If so, write down the “Country” from that row
                String country = record.get(0);
                System.out.println(country + " Export Total: "  + record.get(2));
            }            
        }
    } 
    public String signStriper (String signString,String sign){ 
        int pos = signString.indexOf(sign);
        //System.out.println(pos);
        int startPos = 1;
        int length = signString.length();
        //System.out.println(length);
        String newString = "";
        if (pos == -1) {return signString.substring(1,length); }
        
        while (true){ 
            newString = newString + signString.substring(startPos,pos);
            //System.out.println(newString);
            startPos = pos + 1;
            pos = signString.indexOf (sign, startPos);
            if (pos== -1) {
                newString = newString + signString.substring(length-3,length);
                break;}                        
        }
        return newString;
    }
}
