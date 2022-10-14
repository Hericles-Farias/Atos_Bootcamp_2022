package LogicaEstruturada;

public class exec1 {
    
    static boolean isPrime(int n){
        //a filter before we iterate through things
        if (n<=1){
            return false;
        }else if (n==2){
            return true;
        }
        for (int i=3;i<=Math.sqrt(n);i+=2){
            //basically all even numbers are not prime, so we skip them (by starting at 3 and summing up 2 each time)
            //and for each odd number we verify it the remainder is null 
            if (n%i==0){
                return false; 
            }
            return true;
        }
        return true;
    }

    public static void main(String[] args) {
        
    }

}
