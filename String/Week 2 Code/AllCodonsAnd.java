
/**
 * Using AND && OR ||.
 * 
 * @author Ping Cui
 * @version 1.0
*/
import java.util.Scanner;
import edu.duke.*;
public class AllCodonsAnd {
    private int startIndex;
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex; 
        // Find stopCodon starting from (startIndex +3), call this result currIndex
        currIndex = dnaStr.indexOf(stopCodon, startIndex +3);
        // As long as currIndex is not equal to -1
        while (currIndex !=-1){
            // Check if (currIndex – startIndex ) is a muiltiple of 3
            if ((currIndex - startIndex) % 3 ==0) { 
                // If so, the current currIndex is your answer
                return currIndex;
            } else {
                // If not, update currIndex to the index of the next stopCodon starting from (currIndex +1)
                currIndex = dnaStr.indexOf(stopCodon, currIndex +1);
            }        
        }
        // Your Answer is -1.      
        return -1;
    }   
    public String findGene(String dna,int where){
        // Find first occurrence of "ATG", call its index startIndex;
        int startIndex = dna.indexOf("ATG",where);        
        if (startIndex == -1) { 
            // If startIndex is -1, then your answer is the empty string;
            return "";
        } else { 
            // findStopCodon(dnaStr, startIndex, "TAA") and call the result taaIndex;
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            System.out.println("taaIndex ="+ taaIndex);
            // findStopCodon(dnaStr, startIndex, "TAG") and call the result tagIndex;
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            System.out.println("tagIndex ="+ tagIndex);
            // findStopCodon(dnaStr, startIndex, "TGA") and call the result tgaIndex;
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");
            System.out.println("tgaIndex ="+ tgaIndex);
            
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
            // Set startIndex to just past the end of the gene
            startIndex = dna.indexOf(currGene,startIndex) + currGene.length();    
        }
        return geneList;
        
    }
    
    public void testFindStop(){
        //            01234567890123456789012345
        String dna = "TTTTTTTTTTAAGGGGGGGGGTAACC";
        int dex = findStopCodon(dna,0,"TAA");
        if (dex !=9) System.out.println ("error on 9");
        dex = findStopCodon(dna,9,"TAA");
        if (dex !=21) System.out.println ("error on 21");
        dex = findStopCodon(dna,1,"TAA");
        if (dex !=-1) System.out.println ("error on 26");
        dex = findStopCodon(dna,9,"TAg");
        if (dex !=-1) System.out.println ("error on 26");
        
        System.out.println("tests finished!");
    }  
    public void testGetAllGenes(String dna){
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()){
            System.out.println(g);
        }
        
    }
    public void testFindGene(String dna){
        //            012345678901234567
        //String dna = "ATGCCCGGGAAATGACCC";
        printAllGenes(dna);
              
        System.out.println(dna);
        //System.out.println(gene);
        System.out.println("tests finished");
    }
    public static void main(String[] args) { 
        // Create a Scanner object
        Scanner input = new Scanner (System.in);
        
        // Prompt the user to enter a radius�
        System.out.print("Enter a dna string: ");
        String dna = input.next();
        AllCodonsAnd test = new AllCodonsAnd();
        test.testFindGene(dna);
        
        // Compute area
        // double radis = input.nextdouble();
        //double area = radius * radius * 3.14159;
        
        // Display results
        //System.out.println("The area for the circle of radius " + radius + " is " + area);
    }

}
