package task2;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User:Paradoxxx (Yarik Brahinets)
 * Date: 11.10.13
 * Time: 00:30
 *
 * programm Fibonacci gets integer number 'n'>0 from command line, and prints value of first 'n' fibonacci numbers
 */
public class Fibonacci {

    /*  main method where we scan value of 'n' from command line and
        call method getFibonacchi 'n' times to calculate value of first 'n' fibonacci numbers */
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        String input = null;

        /* initial value of n = -1, to start while cycle */
        int n = -1;

        /* while 'n' is non positive or 'n' not integer number : scaning n*/
        while(n<=0){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("\nInput positive integer number : ");
                input = bufferedReader.readLine();
                n = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("\tSorry, please input integer number !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /* calculate and print value of first 'n' fibonacci numbers in command line */
        System.out.printf("\nFirst %d fibonacci numbers :  ",n);
        for(int i=0; i<n; i++){
            System.out.printf("\t%d",fibonacci.getFibonacci(i));
        }

        System.exit(0);
    }


    /*
        method getFibonacci gets integer number 'n' and returns value of 'n'-th fibonacci number
        n >= 0
        fionacci(0) = 0;
        fionacci(1) = 1;
        fionacci(2) = 1;
        fionacci(3) = fionacci(1)+fionacci(2);
            ......
        fionacci(n) = fionacci(n-1)+fionacci(n-2);
    */
    public int getFibonacci(int n){
        int fibPrevPrev = 0;/* fib(0)=0;    value of fib(i-2) */
        int fibPrev = 1;    /* fib(1)=1;    value of fib(i-1) */
        int fibCur = 0;     /* value of fib(i)   */
        if(n == 0){
            return 0;
        } else {
            if(n == 1 || n == 2){
                return 1;
            } else {
                for(int i=1; i<n; i++){
                    fibCur =  fibPrev + fibPrevPrev;
                    fibPrevPrev = fibPrev;
                    fibPrev = fibCur;
                }
                return fibCur;
            }
        }
    }
}
