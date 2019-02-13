
/**
 * Write a description of Part4 here.
 * 
 * Part 4: Finding Web Links
 * Write a program that reads the lines from the file at this URL location, 
 * http://www.dukelearntoprogram.com/course2/data/manylinks.html, 
 * and prints each URL on the page that is a link to youtube.com
 * Assume that a link to youtube.com has no spaces in it and would be in the format 
 * (where [stuff] represents characters that are not verbatim): “http:[stuff]youtube.com[stuff]”
 * 
 * 1. Create a new Java Class named Part4 in the StringsFirstAssignments project 
 * and put your code in that class.
 * 2. Use URLResource to read the file at 
 * http://www.dukelearntoprogram.com/course2/data/manylinks.html word by word.
 * 
 * @author (Ping Cui) 
 * @version (2019 Jan 13)
 */

import edu.duke.*;

public class Part4 {
    public void runUrl() {
        URLResource url = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        // Use URLResource to read the file word by word 
        for (String word : url.words()){
            //The word Youtube could appear in different cases such as YouTube, youtube, or YOUTUBE. 
            // You can find the URLs more easily by converting the string to lowercase
            String lowerWord = word.toLowerCase();
            String newUrl;
            int leftStart = 0;
            int rightEnd = 0;
            //For each word, check to see if “youtube.com” is in it.
            int start = lowerWord.indexOf("youtube.com");
            if(start != -1){
                //If it is, find the double quote to the left and right of the occurrence 
                // of “youtube.com” to identify the beginning and end of the URL.
                leftStart = word.indexOf("\"") + 1;
                // consider using the String method lastIndexOf(s, num) 
                // that can be used with two parameters s and num.
                rightEnd = word.lastIndexOf("\"");
                // However, you will need the original string (with uppercase and lowercase letters) 
                // o view the YouTube URL to answer a quiz question 
                //because YouTube links are case sensitive.
                newUrl = word.substring(leftStart,rightEnd);
                System.out.println(word);                
                //prints each URL on the page that is a link to youtube.com. 
                System.out.println(newUrl);
                System.out.println();
                
            }           
            
        }
    
    }     
    

}
