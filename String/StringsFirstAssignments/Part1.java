
/**
 * Part 1: Finding a Gene - Using the Simplified Algorithm
 * This assignment is to write the code from the lesson from scratch by following the steps below.
 * This will help you see if you really understood how to put the code together, 
 * and might identify a part that you did not fully understand.
 * If you get stuck, then you can go back and watch the coding videos that go with this lesson again.
 * We recommend you try this with many of the future Java coding examples 
 * before starting programming exercises.
 * 
 * Specifically, you should do the following:
 * 1. Create a new Java project named StringsFirstAssignments.
 * You can put all the classes for this programming exercise in this project.
 * 2. Create a new Java Class named Part1. The following methods go in this class.
 * 
 * @author (Ping Cui) 
 * @version (2019 Jan 12)
 */
public class Part1 {
    // Write the method findSimpleGene that has one String parameter dna, representing a string of DNA.
    public String findSimpleGene (String dna){
        // Finds the index position of the start codon “ATG”.
        int start = dna.indexOf("ATG");
        // If there is no “ATG”, return the empty string.
        if(start == -1){
            return " No ATG";
        }
        // Finds the index position of the first stop codon “TAA” 
        // appearing after the “ATG” that was found.
        int stop = dna.indexOf("TAA",start+3);
        /* The following code was the mistake I made, Logic overlap with the
         * following logic check
         * if(start == -1){
            return " No TAA";
        } else */
        // If the length of the substring between the “ATG” and “TAA” is a multiple of 3, 
        // then return the substring that starts with that “ATG” and ends with that “TAA”.
        if ((stop-start)%3 == 0) {
            return dna.substring(start, stop+3);
        }else {
            //  If there is no such “TAA”, return the empty string.
            return "bad gene section!";
        }
    }
    // Write the void method testSimpleGene that has no parameters.
    public void testSimpleGene (){
        // Clear the terminal to avoid confusing
        System.out.println(\u000C);
        
        //You should create five DNA strings. The strings should have specific test cases,
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        // Print the DNA string.
        System.out.println("DNA Strand is  " + dna);
        // See if there is a gene by calling findSimpleGene with this string as the parameter. 
        String gene = findSimpleGene(dna);
        // If a gene exists following our algorithm above, then print the gene, 
        // otherwise print the empty string
        System.out.println("gene is  " + gene);
        
        //such as: DNA with no “ATG”,
        dna = "ATCGGATAA";
        System.out.println("DNA Strand is  " + dna);
        gene = findSimpleGene(dna);
        System.out.println("gene is  " + gene);
        
        //DNA with no “TAA” DNA with no “ATG” or “TAA”,
        dna = "ATGACTTTA";
        System.out.println("DNA Strand is  " + dna);
        gene = findSimpleGene(dna);
        System.out.println("gene is  " + gene);
        
        //DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        dna = "ATGGCATAA";
        System.out.println("DNA Strand is  " + dna);
        gene = findSimpleGene(dna);
        System.out.println("gene is  " + gene);
        
        // DNA with ATG, TAA and the substring between them is not a multiple of 3. 
        dna = "ATGTTTCCTAA";
        System.out.println("DNA Strand is  " + dna);
        gene = findSimpleGene(dna);
        System.out.println("gene is  " + gene);
    }
    

}
