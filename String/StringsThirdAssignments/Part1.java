
/**
 * Part 1
 * 1. Create a new Java project named StringsThirdAssignments. You can put 
 * all the classes for this programming exercise in this project.
 * 
 * 2. Create a new Java Class named Part1. Copy and paste the code from your 
 * Part1 class in your StringsSecondAssignments project into this class.
 * 
 * 3. Make a copy of the printAllGenes method called getAllGenes. Instead of 
 * printing the genes found, this method should create and return a StorageResource 
 * containing the genes found. Remember to import the edu.duke libraries otherwise 
 * you will get an error message cannot find the class StorageResource.
 * 
 * 4. Make sure you test your getAllGenes method.
 * 
 * @author Ping Cui 
 * @version 1.0
 */
import java.util.Scanner;
import edu.duke.*; 
public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon){
         int currIndex; 
        // Find stopCodon starting from (startIndex +3), call this result currIndex
        currIndex = dna.indexOf(stopCodon, startIndex +3);
        // As long as currIndex is not equal to -1
        while (currIndex !=-1){
            // Check if (currIndex – startIndex ) is a muiltiple of 3
            if ((currIndex - startIndex) % 3 ==0) { 
                // If so, the current currIndex is your answer
                return currIndex;
            } else {
                // If not, update currIndex to the index of the next stopCodon starting from (currIndex +1)
                currIndex = dna.indexOf(stopCodon, currIndex +1);
            }        
        }
        // Your Answer is dnaStr.length().      
        return -1;
    }
    public String findGene(String dna,int where){
        // Find first occurrence of "ATG", call its index startIndex;
        int startIndex = dna.indexOf("ATG", where);        
        if (startIndex == -1) { 
            // If startIndex is -1, then your answer is the empty string;
            return "";
        } else { 
            // findStopCodon(dnaStr, startIndex, "TAA") and call the result taaIndex;
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            // findStopCodon(dnaStr, startIndex, "TAG") and call the result tagIndex;
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            // findStopCodon(dnaStr, startIndex, "TGA") and call the result tgaIndex;
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");
            
            int minIndex = -1;
            // Check if taaIndex is -1 OR (tgaIndex is not -1 And tgaIndex < taaIndex) 
            if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex <taaIndex)){
                //If so, set minIndex to tgaIndex 
                minIndex = tgaIndex;
            } else {           
                //If not, set minIndex to taaIndex 
                minIndex = taaIndex;
            }
            // Check if minIndex is -1 OR (tagIndex is not -1 And tagIndex < minIndex)
            if (minIndex == -1 || (tagIndex != -1 && tagIndex <minIndex)) {
                // If so, set minIndex to tagIndex 
                minIndex = tagIndex;
            }           
            // if minIndex is dnaStr.length(), your answer is the empty string;
            if (minIndex == -1) {                 
                //System.out.println("eva1="+eva1+" eva2="+ eva2);
                return "";                
            } else {
                // otherwise the answer is text from startIndex to minIndex + 3;                
                return dna.substring(startIndex, minIndex+3);
            }
        }       
    }
    public void printAllGenes(String dna) { 
        // Set startIndex to 0
        int startIndex = 0;
        // Repeat the following steps: while (true) { }
        while (true){ 
            // Find the next gene after startIndex
            String currGene = findGene(dna, startIndex);
            // If no gene was found, leave this loop: if (gene.isEmpty()) { break;}
            if (currGene.isEmpty()) {break;}
            // Print the gene out
            System.out.println(currGene);
            // Set startIndex to just past the end of the gene
            startIndex = dna.indexOf(currGene,startIndex) + currGene.length();    
        }
        
    }
    public StorageResource getAllGenes(String dna){
        // Create an empty StorageResource, call it geneList 
        StorageResource geneList = new StorageResource();
        // Set startIndex to 0
        int startIndex = 0;
        // Repeat the following steps: while (true) { }
        while (true){ 
            // Find the next gene after startIndex
            String currGene = findGene(dna, startIndex);
            // If no gene was found, leave this loop: if (gene.isEmpty()) { break;}
            if (currGene.isEmpty()) {break;}
            // Add that gene to geneList 
            geneList.add(currGene);
            //System.out.println(currGene);
            // Set startIndex to just past the end of the gene
            startIndex = dna.indexOf(currGene,startIndex) + currGene.length();    
        }
        return geneList;
        
    }
    // Part 2 Write the method cgRatio that has one String parameter dna, and returns 
    // the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA.
    public double cgRatio(String dna){
        int repeats = 0;
        int currIndex =0;
        // how many times stringa appears in stringb
        while (true) {
            currIndex = dna.indexOf("C",currIndex);
            if (currIndex == -1) {break;}
            currIndex = currIndex + 1;
            repeats +=1;    
        }
        while (true) {
            currIndex = dna.indexOf("G",currIndex);
            if (currIndex == -1) {break;}
            currIndex = currIndex + 1;
            repeats +=1;    
        }
        // if the String were “ATGCCATAG,” then cgRatio would return 4/9 or .4444444.
        //double cgRatio = (float)repeats/dna.length();
        //System.out.println(repeats);
        //System.out.println(dna.length());
        return (float)repeats/dna.length();    
    }
    // Write a method countCTG that has one String parameter dna, and returns the 
    // number of times the codon CTG appears in dna.
    public int countCTG(String dna){ 
        int repeats = 0;
        int currIndex =0;
        // how many times stringa appears in stringb
        while (true) {
            currIndex = dna.indexOf("CTG",currIndex);
            if (currIndex == -1) {break;}
            currIndex = currIndex +3;
            repeats +=1;    
        }       
        System.out.println(repeats);       
        return repeats;
    }
    // Part 3: Write the void method processGenes that has one parameter sr, 
    // which is a StorageResource of strings.
    public void processGenes(StorageResource sr) { 
        int longNineStr = 0;
        int bigCGratio = 0;
        int longestGene =0;
        for (String s: sr.data()){
            // print the number of strings in sr whose C-G-ratio is higher than 0.35
            if (cgRatio(s) >0.35){
                System.out.println("Big cgRatio String " +s);
                bigCGratio +=1;                                 
            }
            // print all the Strings in sr that are longer than 9 characters
            if (s.length() >60){
                System.out.println("Longer than 60 String is " +s);
                longNineStr +=1;               
            }
            // print the length of the longest gene in sr
            if (s.length() >longestGene){                 
                longestGene = s.length();               
            }
            
        }
        // print the number of Strings in sr that are longer than 9 characters
        System.out.println("Longer than 9 String Number is " + longNineStr);
        // print the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("CGratio bigger than 0.35 nubmer is " + bigCGratio);
        // print the length of the longest gene in sr
        System.out.println("the Length of the longest gene is " + longestGene);
    }
    // Write a method testProcessGenes.
    public void testProcessGens(){
        /*
        StorageResource sr = new StorageResource();
        // one DNA string that has some genes longer than 9 characters
        sr.add("ATGCCCTTTGGGTAA");
        // one DNA string that has no genes longer than 9 characters
        sr.add("ATGTAA");
        // one DNA string that has some genes whose C-G-ratio is higher than 0.35
        sr.add("ATGCCCGGGTGA");
        // one DNA string that has some genes whose C-G-ratio is lower than 0.35
        sr.add("ATGAAATTTTAA");
        sr.add("ATGCCCTTTGGGTAATCCTAA");
        */
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        System.out.println(dna);
        String DNA = dna.toUpperCase();
        int countCTG = countCTG(DNA);
        System.out.println("CTG number is " + countCTG);
        StorageResource sr = getAllGenes(DNA);
        processGenes(sr);    
    }
    
    public void testCountCTG(String dna){
        int countCTG = countCTG(dna);
        System.out.println(countCTG);
    } 
    
    public void testCgRatio(String dna){
        double cgRatio = cgRatio(dna);
        System.out.println(cgRatio);
    }
    public void testGetAllGenes(String dna){
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()){
            System.out.println(g);
        }
        
    }
    
    public void testFindGene(String dna){
        printAllGenes(dna);
        System.out.println(dna);
        System.out.println("tests finished");
    }
    public static void main(String[] args) { 
        // Create a Scanner object
        Scanner input = new Scanner (System.in);
        // Prompt the user to enter a radius�
        System.out.print("Enter a dna string: ");
        String dna = input.next();
        Part1 test = new Part1();
        test.testCountCTG(dna); 
    }

}
