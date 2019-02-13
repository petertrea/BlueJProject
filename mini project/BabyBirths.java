import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyBirths {
    public void functionTest_printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            //int size = rec.size();
            //System.out.println ("size " + rec.size());
            long recordNumber = rec.getRecordNumber();
            System.out.println ("recordNumber " + rec.getRecordNumber());
            long characterPostion = rec.getCharacterPosition();
            System.out.println ("recordPosition " + rec.getCharacterPosition());
            String comment = rec.getComment();
            System.out.println ("comment " + rec.getComment());
            String toString = rec.toString();
            System.out.println ("toSring " + rec.toString());
            /*
            int numBorn = Integer.parseInt (rec.get(2));
            if (numBorn <= 100) {
                System.out.println ("Name " + rec.get(0) + 
                                " Gender " + rec.get(1) +
                                " Num Born " + rec.get(2));
                            }*/
        } 
    }
    public void totalBirths (FileResource fr) { 
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int numBoysName = 0;
        int numGirlsName = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){ 
            int numBorn = Integer.parseInt (rec.get(2));            
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){ 
                totalBoys += numBorn;
                numBoysName ++;
            } else { 
                totalGirls += numBorn;
                numGirlsName ++;
            } 
        }
        System.out.println ("total births = " + totalBirths);
        System.out.println ("total boys = " + totalBoys);
        System.out.println ("total girls = " + totalGirls);
        System.out.println ("total boys name = " + numBoysName);
        System.out.println ("total girls name = " + numGirlsName);
    }
    public int getRank (int year, String name, String gender) {
        // First Stage; find getRank and totalSameGender inside loop
        // Second Stage; Handle not finding situation outside the loop
        int getRank = 0;
        int totalSameGender = 0;
        String fname ="data/yob"+year+ ".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser (false);
        
        for (CSVRecord rec : parser){ 
            // Handling getRank and tatalSameGender         
            if (gender.equals(rec.get(1))) {
                // As long as same gender, count;
                totalSameGender ++;
                if (name.equals(rec.get(0))){
                    //Same gender and same name set getRank (only i)
                    getRank = totalSameGender;
                }
            }        
        }
        // handling not find situation
        if (getRank == 0){
            return -1;
        }else {
            return getRank;
        }
    }
    public String getName (int year, int rank, String gender) {
        String getName = null;
        int totalSameGender = 0;
        String fname ="data/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser (false);
        for (CSVRecord rec : parser){ 
            // Handling getRank and tatalSameGender         
            if (gender.equals(rec.get(1))) {
                // As long as same gender, count;
                totalSameGender ++;
                if (totalSameGender == rank){
                    getName = rec.get(0);
                }
            }        
        }
        if (getName == null){
            return "NO NAME";
        }else {
            return getName;
        }
    }
    public void whatIsNameInYear (String name, int year, int newYear, String gender ){
        int getRank = getRank(year,name,gender);
        String newName = getName(newYear,getRank,gender);
        System.out.println(name + " born in " + year + " would be " + newName
                            + " If she was born in " + newYear);
    }
    public int yearOfHighestRank (String name, String gender) { 
        int highRankYear = 0;
        // Need to improve the following Statement
        int rankMin = 999999999;
        DirectoryResource dr = new DirectoryResource();
        String fileName = null;        
        for (File f : dr.selectedFiles()){
            //https://docs.oracle.com/javase/8/docs/api/java/io/File.html
            fileName = f.getName();
            int year =Integer.parseInt(fileName.substring(3,7));
            int rank = getRank(year,name,gender);
            if (rank == -1) {
                highRankYear = -1;
            } else {
                if(rank < rankMin){
                rankMin = rank;
                highRankYear = year; 
                }               
            }
        } 
        return highRankYear;
    }
    public double getAverageRank (String name, String gender) {
        double averageRank = 0.0;
        // Need to improve the following Statement
        double rankTotal = 0.0;
        int totalYear =0;
        DirectoryResource dr = new DirectoryResource();
        String fileName = null;        
        for (File f : dr.selectedFiles()){
            //https://docs.oracle.com/javase/8/docs/api/java/io/File.html
            fileName = f.getName();
            int year =Integer.parseInt(fileName.substring(3,7));
            int rank = getRank(year,name,gender);
            if (rank == -1) {
                averageRank = -1.0;
            } else {
                rankTotal += rank;
                totalYear ++ ;
                averageRank =rankTotal / totalYear;               
            }
        } 
        return averageRank;
    }
    public int getTotalBirthsRankedHigher (int year,String name, String gender) { 
        int higherRankedName = 0;
        int totalSameGender = 0;
        int rank = getRank(year,name,gender);
        
        String fname ="data/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser (false);
        for (CSVRecord rec : parser){ 
            // Handling getRank and tatalSameGender         
            if (gender.equals(rec.get(1))) {
                // As long as same gender, count;
                totalSameGender ++;
                if (totalSameGender < rank){
                    higherRankedName += Integer.parseInt(rec.get(2));
                }
            }        
        }       
        return higherRankedName;
    }
    public void testGetTotalBirthsRankedHigher (){
        int average = getTotalBirthsRankedHigher (1990,"Emily","F");
        System.out.println(average);
    }
    public void testGetAverageRank (){
        double average = getAverageRank ("Robert","M");
        System.out.println(average);
    }
    public void testYearOfHighestRank (){
        int year = yearOfHighestRank ("Mich","M");
        System.out.println(year);
    }
    public void testGetName() { 
        String getName = getName (1982, 450, "M");
        System.out.println("The selected rank name is " + getName);
    }
    public void testGetRank() { 
        int getRank = getRank (1971, "Frank", "M");
        System.out.println("The selected name rank is " + getRank);
    }
    public void testTotalBirths () {
        //FileResource fr = new FileResource ("/us_babynames/us_babynames_test/example-small");
        FileResource fr = new FileResource ();
        totalBirths (fr);
    }

}
