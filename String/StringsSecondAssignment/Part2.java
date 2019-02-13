
/**
 * Part 2: HowMany - Finding Multiple Occurrences
 * 
 * This assignment will write a method to determine how many occurrences of a string appear in another string.
 * 
 * Specifically, you should do the following:
 * 
 * 1. Create a new Java Class named Part2 in the StringsSecondAssignments project.
 * 
 * 2. Write the method named howMany that has two String parameters named stringa and stringb. 
 * This method returns an integer indicating how many times stringa appears in stringb, where 
 * each occurrence of stringa must not overlap with another occurrence of it. For example, 
 * the call howMany(“GAA”, “ATGAACGAATTGAATC”) returns 3 as GAA occurs 3 times. The call 
 * howMany(“AA”, “ATAAAA”) returns 2. Note that the AA’s found cannot overlap.
 * 
 * 3.Write the void method testHowMany has no parameters. Add code in here to call howMany with 
 * several examples and print the results. Think carefully about what types of examples would be 
 * good to test to make sure your method works correctly.
 * 
 * @author Ping Cui
 * @version 1.0
 * 
 */
public class Part2 {
    public int howMany (String stringa, String stringb) {
        int repeats = 0;
        int currIndex =0;
        // how many times stringa appears in stringb
        while (true) {
            currIndex = stringb.indexOf(stringa,currIndex);
            if (currIndex == -1) {break;}
            currIndex = currIndex + stringa.length();
            repeats +=1;    
        }
        return repeats;
    }
    public void testHowMany(){
        //            012345678901234567
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        int tms = howMany(stringa,stringb);
        System.out.println(stringa);
        System.out.println(stringb);
        System.out.println("repeats =" + tms);
        
        stringa = "AA";
        stringb = "ATAAAAAAAAAA";
        tms = howMany(stringa,stringb);
        System.out.println(stringa);
        System.out.println(stringb);
        System.out.println("repeats =" + tms);
    }

}
