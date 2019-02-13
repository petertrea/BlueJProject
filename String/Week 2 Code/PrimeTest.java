
/**
 * Write a description of class PrimeTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PrimeTest{    
    public static void main(String[] args){
        int num = Integer.parseInt(args[0]);
        //int py1 = Integer.parseInt(args[1]);
        //int px2 = Integer.parseInt(args[2]);
        //int py2 = Integer.parseInt(args[3]);
        //MathTest p1 = new MathTest (px1, py1);
        PrimeTest p = new PrimeTest();
        
        //System.out.println(Math.E);
        System.out.println(num + " is Prime is " + p.isPrime(num));
        System.out.println(num + " sqrt is " + Math.sqrt(num));
    }
    public boolean isPrime(int num){
        // put your code here
        int div =2;
        if (num == 2){
            return true;
        }
        while (true) {
            if (num % div == 0){
                return false;
            }
            if (div > Math.sqrt(num)){ 
                break;
            }
            div=div +1;
        }
        return true;
    }
}
