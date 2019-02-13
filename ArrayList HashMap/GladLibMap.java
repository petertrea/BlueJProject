
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
 
public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private HashMap<String,Integer> map; 
    private ArrayList<String> usedList;
    private Random myRandom;   
	
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
	
    public GladLibMap(){
	initializeFromSource(dataSourceDirectory);
	myRandom = new Random();
    }
	
    public GladLibMap(String source){
	initializeFromSource(source);
	myRandom = new Random();
    }
	
    private void initializeFromSource(String source) { 
        usedList = new ArrayList<String>();
        map = new HashMap<String,Integer>();
        myMap = new HashMap<String, ArrayList<String>>();
        String[] labels = {"country","noun","animal","adjective","name","color",
        "timeframe","verb","fruit"};
        for (String s : labels) {
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }/*
        for(String w : myMap.keySet()) {
            System.out.println(w + "\t" + myMap.get(w));
        } */      
    }
    private ArrayList<String> readIt(String source){
	ArrayList<String> list = new ArrayList<String>();
	if (source.startsWith("http")) {
	    URLResource resource = new URLResource(source);
	    for(String line : resource.lines()){
	        list.add(line);
	    }
	}else {
	    FileResource resource = new FileResource(source);
	    for(String line : resource.lines()){
	        list.add(line);
	    }
	}
	return list;
    }
    private String randomFrom(ArrayList<String> source){
	int index = myRandom.nextInt(source.size());
	return source.get(index);
    }
    private String getSubstitute(String label) {
        if(label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        //System.out.println(label); 
        // Assume a private variable of type ArrayList<String> and named categoriesUsed is used
        //to store the unique categories found as the GladLib is created
        if (map.keySet().contains(label)){
            //int newInt = usedMap.get(label)+1;
            map.put(label,map.get(label)+1);
        }else{
            map.put(label,1);                 
        }       
        return randomFrom(myMap.get(label));                       
    }
	
    private String processWord(String w){
	int first = w.indexOf("<");
	int last = w.indexOf(">",first);
   	if (first == -1 || last == -1){
		return w;
	}
	String prefix = w.substring(0,first);
	String suffix = w.substring(last+1);
	String sub = getSubstitute(w.substring(first+1,last));
	// Learned from exercise: once it uses a word, it never uses that word again
	while (true){
	    if (! usedList.contains(sub)) {
	        usedList.add(sub);
                break;   	     
            }
            sub = getSubstitute(w.substring(first+1,last));
        }
	/* This is the one I wrote myself
	while (usedList.contains(sub)){
	    sub = getSubstitute(w.substring(first+1,last));
	    if (!usedList.contains(sub)){
	     break;}
	}
	//an additional private ArrayList to keep track of words that have been seen
	usedList.add(sub);
	*/
	return prefix+sub+suffix;	
     }
	
    private void printOut(String s, int lineWidth){
	int charsWritten = 0;
	//check out this regex: \; introduce escaped constructs; \s:a whitespace character;
	// +; Matches the preceding element one or more times
	for(String w : s.split("\\s+")){
	    if (charsWritten + w.length() > lineWidth){
	        System.out.println();
		charsWritten = 0;
	    }
	    System.out.print(w+" ");
	    charsWritten += w.length() + 1;
	}
    }
	
    private String fromTemplate(String source){
	String story = "";
	if (source.startsWith("http")) {
	    URLResource resource = new URLResource(source);
	    for(String word : resource.words()){
	        story = story + processWord(word) + " ";
	    }
	} else {
	    FileResource resource = new FileResource(source);
	    for(String word : resource.words()){
		story = story + processWord(word) + " ";
	    }
	}
	return story;
    }		
    public void makeStory(){        
        // clear out this new ArrayList in makeStory before each run of your program
        usedList.clear();
        map.clear();
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 80);
        System.out.println("\n");
        //print out the total number of words that were replaced right after the story is printed.
        for(int i=0; i<usedList.size();i++){
            System.out.println("\t"+usedList.get(i));
        } 
        System.out.println("\n");
        for(String w : map.keySet()) {
            System.out.println(w + "\t" +map.get(w));
        }
        int total = totalWordsConsidered ();
        System.out.println("total words are considered" + "\t" +total);
    }
    private int totalWordsInMap (){
        int totalWords = 0;
        for(String w : myMap.keySet()) {
            totalWords += myMap.get(w).size();
        }        
        return totalWords;
    }
    private int totalWordsConsidered (){
       int totalConsidered =0;
       
       for(String w : map.keySet()) {
           totalConsidered += map.get(w);
       }
       
       return totalConsidered;        
    }
}
