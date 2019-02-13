
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer ();
        //la.readFile("test/short-test_log");
        //la.readFile("test/weblog-short_log");
        //la.readFile("test/weblog2-short_log");
        //la.readFile("test/weblog3-short_log");
        //la.readFile("test/weblog1_log");
        la.readFile("test/weblog2_log");
        la.printAll();
    }
    public void testUniqueIP (){
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("test/weblog2_log");
        int uniqueIps = la.countUniqueIPs();
        System.out.println("There are " + uniqueIps + " IPs");
        System.out.println("status code greater than 400 logs are ");
        la.printAllHigherThanNum(400);
    }
    public void testUniqueIPVisitsOnDay(){
        ArrayList<String> list = new ArrayList<String>(); 
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("test/weblog2_log");
        list = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("Sep 24 List size is " + list.size());
        for (int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }        
    }
    public void testCountUniqueIPsInRange(){
        ArrayList<String> list = new ArrayList<String>(); 
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("test/weblog2_log");
        //la.printAll();
        int count= la.countUniqueIPsInRange(400,499);
        System.out.println("Unique IPs in Range 400-499 is " + count);
    }
    public void WebVisitTester1(){       
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("test/weblog2_log");
        //la.readFile("test/short-test_log");
        //la.readFile("test/weblog2-short_log");
        //la.readFile("test/weblog3-short_log");
        //la.readFile("test/weblog-short_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        //System.out.println(counts);
        /*
        for (String s : counts.keySet()){
            System.out.println(s + "\t" + counts.get(s));
        }
        */
        int maxVisit = la.mostNumberVisitsByIP(counts);
        System.out.println("Most visits by single Ip is " + maxVisit);
        
        ArrayList<String> mostVisitsIps = la.iPsMostVisits(counts);
        System.out.println("Most visits by Ips are " +  mostVisitsIps);
            }
    public void WebVisitTester2(){       
        LogAnalyzer la = new LogAnalyzer ();
        la.readFile("test/weblog2_log");
        //la.readFile("test/short-test_log");
        //la.readFile("test/weblog2-short_log");
        //la.readFile("test/weblog3-short_log");
        //la.readFile("test/weblog-short_log");             
        HashMap<String,ArrayList<String>> map= la.iPsForDays();
        //System.out.println(map);
        for (String s : map.keySet()){
            System.out.println(s + "\t" + map.get(s).size()+"\t" + map.get(s) );           
        }
        String day = la.dayWithMostIPVisits(map);
        System.out.println("Most visits day by Ips is " +  day);
        ArrayList<String> iPs = la.iPsWithMostVisitsOnDay(map,"Sep 30");
        System.out.println("Most visits Ips on Sep 30 are " +  iPs);
    }
}
