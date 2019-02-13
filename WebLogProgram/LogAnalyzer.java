
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
     //create a LogEntry and store it in the records ArrayList.
     public void readFile(String filename) {
         FileResource fr = new FileResource (filename);
         records.clear();
         for (String line : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     /* Finding Unique IP Address */
     //return an integer representing the number of unique IP addresses
     public int countUniqueIPs() {
         /*
         //uniqueIPs Start as empty list
         ArrayList<String> list = new ArrayList<String>();
         //for each element le in reords
         for (LogEntry le : records) {
             //ipAddr is le's ipAddress
             String ipAddr = le.getIpAddress();
             //if ipAddr is not in uniqueIps
             if(!list.contains(ipAddr)){
                 list.add(ipAddr);
                }
         }
         return list.size();
         */
         //AlternateSolution:
         HashMap<String,Integer> counts = countVisitsPerIP();
         return counts.size();
     }
     //examine all the web log entries in records and print those LogEntrys that have a status code greater than num
     public void printAllHigherThanNum (int num){
         for (LogEntry le : records) {
            if (le.getStatusCode()>num){
                System.out.println(le.getAccessTime());
                System.out.println(le);
            }
         }
     }
     //returns an ArrayList of Strings of unique IP addresses that had access on the given day
     public ArrayList<String> uniqueIPVisitsOnDay (String someday){
         //returns an ArrayList of Strings of unique IP addresses that had access on the given day
         ArrayList<String> list = new ArrayList<String>(); 
         for (LogEntry le : records) {
            //dates in LogEntrys are stored as a Date object, but using toString will allow you to access the characters in the Date
            String str =le.getAccessTime().toString();
            String day =str.substring(4,10);
            //System.out.println(day);
            String ipAddr = le.getIpAddress();
            if (day.equals(someday)){
                if(!list.contains(ipAddr)){
                    list.add(ipAddr);
                }
            }
         }
         return list;   
     }
     //returns the number of unique IP addresses in records that have a status code 
     //in the range from low to high , inclusive
     public int countUniqueIPsInRange (int low, int high){
         ArrayList<String> list = new ArrayList<String>(); 
         for (LogEntry le : records) {
            if (low<=le.getStatusCode() && le.getStatusCode() <= high){
                String ipAddr = le.getIpAddress();
                //if ipAddr is not in uniqueIps
                if(!list.contains(ipAddr)){
                 list.add(ipAddr);
                }
            }
         }           
         return list.size();
     }
     /* Counting Website Visits */
     //returns a HashMap<String, Integer> that maps an IP address
     //to the number of times that IP address appears in records
     public HashMap<String,Integer> countVisitsPerIP (){
         // Make an empty HashMaps<String,Integer> (counts)
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        //For each le in records
        for (LogEntry le : records) {
            //ip is le’s ipAddress
            String ipAddr = le.getIpAddress();
            //check if ip is in counts
            if(!counts.containsKey(ipAddr)){
                //If not: put ip in with a value of 1
                counts.put(ipAddr,1);
            } else {
                //If so: update the value to be 1 more
                counts.put(ipAddr,counts.get(ipAddr)+1);
            }
        }
        //counts is the answer   
        return counts;
       }
     //returns the maximum number of visits to this website by a single IP address.
     public int mostNumberVisitsByIP (HashMap<String,Integer> countVisits){
         int maxNumbers = 0;
         for (String s : countVisits.keySet()){
             if(countVisits.get(s)>maxNumbers){
                 maxNumbers = countVisits.get(s);
                }
            }         
         return maxNumbers;
        }
     //returns an ArrayList of Strings of IP addresses that all have the maximum number of visits to this website
     public ArrayList<String> iPsMostVisits (HashMap<String,Integer> countVisits){
        ArrayList<String> iP = new ArrayList<String>();
        int maxNumbers = mostNumberVisitsByIP (countVisits) ;
        for (String s : countVisits.keySet()){
             if(countVisits.get(s)==maxNumbers){
                 iP.add(s);
                }
            }         
        return iP;
        }
     //returns a HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an
     //ArrayList of IP addresses that occurred on that day.
     public HashMap<String, ArrayList<String>> iPsForDays (){
         HashMap<String, ArrayList<String>> ipsForDays = new HashMap<String, ArrayList<String>>();
         for (LogEntry le : records) {
            //dates in LogEntrys are stored as a Date object, but using toString will allow you to access the characters in the Date
            String str =le.getAccessTime().toString();
            String day =str.substring(4,10);
            //System.out.println(day);
            String ipAddr = le.getIpAddress();
         if (!ipsForDays.keySet().contains(day)){
             ArrayList<String> list = new ArrayList<String>();
             list.add(ipAddr);
             ipsForDays.put(day,list);                
         } else{
              ipsForDays.get(day).add(ipAddr);
             //if(!ipsForDays.get(day).contains(ipAddr)){
             //   ipsForDays.get(day).add(ipAddr);}
            }
         }
         return ipsForDays;
        }
     //returns the day that has the most IP address visits
     public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> hashMap){
         String day = null; 
         int maxIp = 0;
         for (String s : hashMap.keySet()){             
             if (hashMap.get(s).size() > maxIp){
                maxIp = hashMap.get(s).size();
                day = s;
            }
         }
         return day;
        }
     //returns an ArrayList<String> of IP addresses that had the most accesses on the given day
     public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String>> hashMap,String day){
        ArrayList<String> iP = new ArrayList<String>();
        for (String s : hashMap.keySet()){
            if (s.equals(day)){
                HashMap<String,Integer> map = new HashMap<String,Integer>();
                for (String ip: hashMap.get(s)){
                    if (!map.keySet().contains(ip)){
                        map.put(ip,1);
                    }else{
                        map.put(ip,map.get(ip)+1);
                    }
                }
                iP = iPsMostVisits(map);
            }
         }        
        return iP;
        }
}
