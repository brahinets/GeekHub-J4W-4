package task_1;

import java.util.HashSet;
import java.util.Set;

/**
 * Class: Main
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 02.11.13
 * Time: 12:07
 * Mail: ysb.kanivtsi@gmail.com
 */
public class Main{
    /*
    * testing operation with collection (Set)
    * - IsEquals
    * - Union
    * - Subtraction
    * - Intersection
    * - Symmetric Subtraction
    * */
    public static void main (String args[]){
        MySet test = new MySet();

        Set test1 = new HashSet();
        Set test2 = new HashSet();

        for(int i=0; i<10; i++){
            test1.add(i);
            test2.add(i*2);
        }

        System.out.println(test1);
        System.out.println(test2);

        System.out.println("Is equal : "+test.equals(test1, test2));
        System.out.println("Union : "+test.union(test1, test2));
        System.out.println("Difference : "+test.subtract(test1, test2));
        System.out.println("Intersection : "+test.intersect(test1, test2));
        System.out.println("Symmetric subtract : "+test.symmetricSubtract(test1, test2));
    }
}