package task3;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: paradoxxx_ua (Yarik Brahinets)
 * Date: 10.10.13
 * Time: 23:30
 *
 * programm Factorial gets integer number n from command line, and prints value of n factorial
 */
public class Factorial {

    /*  main method where we scan value of 'n' from command line and
        call method getFactorial to calculate value of 'n' factorial */
    public static void main(String[] args) {
        Factorial fact = new Factorial();
        String input = null;

        /* initial value of n = -1, to start while cycle */
        int n = -1;

        /* while n is negative or n not integer number : scaning n*/
        while(n<0){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("\nInput non negative integer number : ");
                input = bufferedReader.readLine();
                n = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("\tSorry, please input integer number !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /* calculate and print value of 'n' factorial in command line */
        System.out.printf("\n%d factorial equals : %d",n, fact.getFactorial(n));
        System.exit(0);
    }


    /*
        method getFactorial gets integer number 'n' and returns value of 'n' factorial
        n >= 0
        factorial(0) = 1;
        factorial(n) = 1*2*3*.....*n
    */
    public int getFactorial(int n){
        int factorial = 1; /* initial value of factorial */
        if(n == 0){
            return 1;
        } else {
            for(int i=1; i <= n; i++)
                factorial *=i;
            return factorial;
        }
    }
}
