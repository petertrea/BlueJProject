
/**
 * Part 3: Problem Solving with Strings
 * Create a new Java Class named Part3 in the StringsFirstAssignments project. 
 * Put the following methods in this class.
 * Key Learning Points from the practice:
 * indexOf(String,fromIndex); parameter transfer (+1,otherwise will repeat the same place)
 * while loop (how to break, how to use debug function)
 * 
 * @author (Ping Cui) 
 * @version (2018 Jan 13)
 */
public class Part3 {
    //Write the method named twoOccurrences that has two String parameters named stringa and stringb.
    public boolean twoOccurences (String stringa, String stringb){
        int start = -1;
        int occurNum = 0;
        int fromIndex =0;
        start = stringb.indexOf(stringa,fromIndex);
        // use a while loop to take care of unlimit situation
        while (start != -1){
            // update occurNum plus 1
            occurNum +=1;
            // This is the key ot avoid dead loop. otherwise, the loop alwasy check the same place
            fromIndex = start+1;
            start = stringb.indexOf(stringa,fromIndex);             
            // Debug code
            /*
            System.out.println("occurNum is " + occurNum);
            System.out.println("Start is " + start);
            */
           
        }
        //This method returns true if stringa appears at least twice in stringb
        if (occurNum >=2){
            return true;
        } else {
        //otherwise it returns false. 
            return false;
        }
    }
    //Write the void method named testing that has no parameters.
    public void testing(){
        // Clear the terminal to avoid confusing
        System.out.println(\u000C);
        // This method should call twoOccurrences on several pairs of strings 
        String stringa = "by";
        String stringb = "A story by Abby Long";
        boolean result = twoOccurences(stringa, stringb);
        //and print the strings and the result of calling twoOccurrences (true or false) for each pair.
        System.out.println("String a is " + stringa);
        System.out.println("String b is " + stringb);
        System.out.println("result is " + result);
        // Be sure to test examples that should result in true 
        stringa = "a";
        stringb = "banana";
        result = twoOccurences(stringa, stringb);
        System.out.println("String a is " + stringa);
        System.out.println("String b is " + stringb);
        System.out.println("result is " + result);
        
        // and examples that should result in false.
        stringa = "atg";
        stringb = "catgattgta";
        result = twoOccurences(stringa, stringb);
        System.out.println("String a is " + stringa);
        System.out.println("String b is " + stringb);
        System.out.println("result is " + result);        
    }
    //Write the method named lastPart that has two String parameters named stringa and stringb. 
    public String lastPart(String stringa, String stringb){
        int start = -1;
        int strLength = stringa.length();
        //This method finds the first occurrence of stringa in stringb,         
        start = stringb.indexOf(stringa); 
        
        if (start != -1){
            //and returns the part of stringb that follows stringa
            String newString;
            newString = stringb.substring(start+strLength);
            return newString;            
        }
        // If stringa does not occur in stringb, then return stringb        
        return stringb;
    }
    public void testingLastPart(){
        System.out.println(\u000C);
        String stringa = "an";
        String stringb = "banana";
        String result = lastPart(stringa, stringb);
        System.out.println("String a is " + stringa);
        System.out.println("String b is " + stringb);
        System.out.println("result is " + result);
        
        stringa = "zoo";
        stringb = "forest";
        result = lastPart(stringa, stringb);
        System.out.println("String a is " + stringa);
        System.out.println("String b is " + stringb);
        System.out.println("result is " + result);
    }
}
