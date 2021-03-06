
/**
 * Part 3: How Many Genes?
 * Write a program to count how many genes are in a strand of DNA.
 * 
 * 1. Create a new Java Class named Part3 in the StringsSecondAssignments project. 
 * Put the following methods in this class.
 * 
 * 2. Copy your methods from Part1 to find one gene and print all genes.
 * 
 * 3. Write the method named countGenes that has a String parameter named dna representing 
 * a string of DNA. This method returns the number of genes found in dna. For example the call 
 * countGenes(“ATGTAAGATGCCCTAGT”) returns 2, finding the gene ATGTAA first and then the gene ATGCCCTAG. 
 * Hint: This is very similar to finding all genes and printing them, except that instead of printing 
 * all the genes you will count them.
 * 
 * 4. Write the void method named testCountGenes that has no parameters. This method calls countGenes 
 * with many example strings and prints the result for each. You should create several examples with 
 * different numbers of genes to test your code.
 * 
 * @author Ping Cui
 * @version version 1.0 
 */
import java.util.Scanner;
public class Part3 {
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
    public int countGenes(String dna) { 
        // Set startIndex to 0
        int startIndex = 0;
        int repeats = 0;
        // Repeat the following steps: while (true) { }
        while (true){ 
            // Find the next gene after startIndex
            String currGene = findGene(dna, startIndex);
            // If no gene was found, leave this loop: if (gene.isEmpty()) { break;}
            if (currGene.isEmpty()) {break;}
            // Print the gene out
            repeats += 1;
            // Set startIndex to just past the end of the gene
            startIndex = dna.indexOf(currGene,startIndex) + currGene.length();    
        }        
        return repeats;
    }
    
    public void testFindGene(String dna){
        System.out.println(dna);
        printAllGenes(dna);
        System.out.println("tests finished");
    }
    public void testCountGenes(String dna){
        System.out.println(dna);
        printAllGenes(dna);
        int rpts =countGenes(dna);
        System.out.println("Total DNA are " + rpts);
        System.out.println("tests finished");
    }
    public static void main(String[] args) { 
        // Create a Scanner object
        Scanner input = new Scanner (System.in);
        // Prompt the user to enter a radius�
        System.out.print("Enter a dna string: ");
        String dna = input.next();
        Part3 test = new Part3();
        test.testCountGenes(dna); 
    }

}
