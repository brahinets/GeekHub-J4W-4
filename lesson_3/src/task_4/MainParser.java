package task_4;

import java.lang.*;

/**
 * Class: MainIntegerParser
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 11:11
 * Mail: ysb.kanivtsi@gmail.com
 */
public class MainParser{

    /* Exceptions testing of parsing String to Integer
    *  input can't start with 0, and include not number symbols, else exceptions
    * */
    public static void main(String args[]){

        IntegerParser test = new IntegerParser();

        System.out.print("Try to parse '12345' : ");
        try {
            System.out.print(test.parseInt("12345"));
            System.out.println("  Success");
        } catch (NumberFormatException e){
            System.out.println("Can't do this, because "+e);
        }


        System.out.print("Try to parse '02345': ");
        try {
            System.out.print(test.parseInt("02345"));
            System.out.println("  Success");
        } catch (NumberFormatException e){
            System.out.println("Can't do this, because "+e);
        }

        System.out.print("Try to parse '123a5': ");
        try {
            System.out.print(test.parseInt("123a5"));
            System.out.println("  Success");
        } catch (NumberFormatException e){
            System.out.println("Can't do this, because "+e);
        }

        System.out.print("Try to parse '023p5': ");
        try {
            System.out.print(test.parseInt("023p5"));
            System.out.println("  Success");
        } catch (NumberFormatException e){
            System.out.println("Can't do this, because "+e);
        }
    }
}
