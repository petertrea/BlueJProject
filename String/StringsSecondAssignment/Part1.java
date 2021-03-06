
/**
 *  Part 1: Finding many Genes
 *  This assignment is to write the code from the lesson to make the following
 *  improvements to your algorithm:
 *  A. Find a gene in a strand of DNA where the stop codon could be any of 
 *  the three stop codons “TAA”, “TAG”, or “TGA”.
 *  B. Find all the genes (where the stop codon could be any of the three 
 *  stop codons) in a strand of DNA.
 *  This will help you see if you really understood how to put the code together, 
 *  and might identify a part that you did not fully understand. If you get 
 *  stuck, then you can go back and watch the coding videos that go with this lesson again.
 *  
 *  Specifically, you should do the following:
 *  1. Create a new Java project named StringsSecondAssignments. You can put all the 
 *  classes for this programming exercise in this project.
 *  2. Create a new Java Class named Part1. The following methods go in this class.
 *  3. Write the method findStopCodon that has three parameters, a String parameter 
 *  named dna, an integer parameter named startIndex that represents where the first 
 *  occurrence of ATG occurs in dna, and a String parameter named stopCodon. 
 *  This method returns the index of the first occurrence of stopCodon that appears 
 *  past startIndex and is a multiple of 3 away from startIndex. 
 *  If there is no such stopCodon, this method returns the length of the dna strand.
 *  4. Write the void method testFindStopCodon that calls the method findStopCodon 
 *  with several examples and prints out the results to check if findStopCodon is 
 *  working correctly. Think about what types of examples you should check. 
 *  For example, you may want to check some strings of DNA that have genes and some 
 *  that do not. What other examples should you check?
 *  
 *  5. Write the method findGene that has one String parameter dna, representing a 
 *  string of DNA. In this method you should do the following:
 *  Find the index of the first occurrence of the start codon “ATG”. If there is 
 *  no “ATG”, return the empty string.
 *  Find the index of the first occurrence of the stop codon “TAA” after the first 
 *  occurrence of “ATG” that is a multiple of three away from the “ATG”. 
 *  Hint: call findStopCodon.
 *  Find the index of the first occurrence of the stop codon “TAG” after the first 
 *  occurrence of “ATG” that is a multiple of three away from the “ATG”. 
 *  Find the index of the first occurrence of the stop codon “TGA” after the first 
 *  occurrence of “ATG” that is a multiple of three away from the “ATG”.
 *  Return the gene formed from the “ATG” and the closest stop codon that is a multiple
 *  of three away. If there is no valid stop codon and therefore no gene, return 
 *  the empty string.
 *  
 *  6. Write the void method testFindGene that has no parameters. You should create five
 *  DNA strings. The strings should have specific test cases such as DNA with no “ATG”, 
 *  DNA with “ATG” and one valid stop codon, DNA with “ATG” and multiple valid stop codons, 
 *  DNA with “ATG” and no valid stop codons. Think carefully about what would be good 
 *  examples to test. For each DNA string you should:
 *  Print the DNA string.
 *  Calculate the gene by sending this DNA string as an argument to findGene. If a gene 
 *  exists following our algorithm above, then print the gene, otherwise print the 
 *  empty string.
 *  
 * 
 * @author (Ping Cui) 
 * @version (a version number or a date)
 */

import java.util.Scanner;
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
        test.testFindGene(dna); 
    }
   
    /*
    public void testFindStop(){
        //            01234567890123456789012345
        String dna = "TTTTTTTTTTAAGGGGGGGGGTAACC";
        int dex = findStopCodon(dna,0,"TAA");
        if (dex !=9) System.out.println ("error on 9");
        dex = findStopCodon(dna,9,"TAA");
        if (dex !=21) System.out.println ("error on 21");
        dex = findStopCodon(dna,1,"TAA");
        if (dex !=26) System.out.println ("error on 26");
        dex = findStopCodon(dna,9,"TAG");
        if (dex !=26) System.out.println ("error on 26");        
        System.out.println("tests finished!");        
    }
    /*
    public void testFindGene(){
        //            012345678901234567
        String dna = "ATTGCCCGGGAAATTGACCC";
        String gene = findGene(dna);
        System.out.println(dna);
        System.out.println(gene);
        System.out.println("tests finished");
        
        //     012345678901234567
        dna = "ATGCCCGGGAAATTACCC";
        gene = findGene(dna);
        System.out.println(dna);
        System.out.println(gene);
        System.out.println("tests finished");
        
        //     012345678901234567
        dna = "ATGCCCGGGAAATGACCC";
        gene = findGene(dna);
        System.out.println(dna);
        System.out.println(gene);
        System.out.println("tests finished");
        
        //     012345678901234567
        dna = "ATGCCCGGGTAATGACCC";
        gene = findGene(dna);
        System.out.println(dna);
        System.out.println(gene);
        System.out.println("tests finished");
        
        //     012345678901234567
        dna = "ATGCCTCGGGTAAtATGACCC";
        gene = findGene(dna);
        System.out.println(dna);
        System.out.println(gene);
        System.out.println("tests finished");       
    }
    */
}
