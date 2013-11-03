package task_1;

/**
 * Class: Main
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 17:36
 * Mail: ysb.kanivtsi@gmail.com
 */
public class Main {

    /*
    * testing sorting arrays of comparable objects:
    *   - humans (Name, Age, Weight)
    *   - cars  (Concern, Color, MaxSpeed, Passengers)
    * */
    public static void main(String args[]){
        Main tester = new Main();
        tester.testHumans();
        //tester.testCars();
    }



    /*
    * sorting comparable objects by fields
    * using bubble method
    *
    * input: array of comparable objects (their classes implements Comparable interface)
    * out :  sorted array of comparable objects (their classes implements Comparable interface)
    * */
    public static Comparable[] sort(Comparable[] elements){
        Comparable[] sortedArray = (Comparable[])elements.clone();
        Object tmp;

        for (int i = sortedArray.length; i >= 2; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (sortedArray[j].compareTo(sortedArray[j + 1]) < 0) {
                    tmp = sortedArray[j];
                    sortedArray[j] = sortedArray[j+1];
                    sortedArray[j+1] = (Comparable)tmp;
                }
            }
        }

        return sortedArray;
    }



    /*
    *   test of sorting of array of Humans objects Human(Name, Age, Weight)
    * */
    private void testHumans(){
        Human[] humans = new Human[7];
        humans[0] = new Human("Alex",21,78.8f);
        humans[1] = new Human("Alex",23,78.8f);
        humans[2] = new Human("Alex",22,78.8f);
        humans[3] = new Human("John",34,120.2f);
        humans[4] = new Human("Piter",34,120.2f);
        humans[5] = new Human("Mike",15,52.2f);
        humans[6] = new Human("Alan",42,90.2f);

        Comparable[] sortedArray = new Comparable[humans.length];

        sortedArray = sort(humans);

        System.out.println("Source array of Humans:");
        for(int i=0; i<humans.length; i++){
            System.out.println(humans[i].toString());
        }

        System.out.println("");

        System.out.println("Sorted array of Humans:");
        for(int i=0; i<sortedArray.length; i++){
            System.out.println(((Human)sortedArray[i]).toString());
        }

    }


    /*
    *   test of sorting of array of Cars objects Car(Concern, Color, MaxSpeed, passengersCount)
    * */
    private void testCars(){
        Car[] cars = new Car[11];
        cars[0] = new Car("Nissan","White",200,4);
        cars[1] = new Car("Opel","Green",130,4);
        cars[2] = new Car("Toyota","Yellow",310,5);
        cars[3] = new Car("Volkswagen","Yellow",240,6);
        cars[4] = new Car("Lexus","Silver",340,5);
        cars[5] = new Car("Lada","Yellow",260,10);
        cars[6] = new Car("Lada","Yellow",250,11);
        cars[7] = new Car("Lexus","Black",340,2);
        cars[8] = new Car("Ferrari","Red",350,2);
        cars[9] = new Car("Lamborghini","Yellow",370,2);
        cars[10] = new Car("Lada","Yellow",250,10);

        Comparable[] sortedArray = new Comparable[cars.length];

        sortedArray = sort(cars);

        System.out.println("Source array of Cars:");
        for(int i=0; i<cars.length; i++){
            System.out.println(cars[i].toString());
        }

        System.out.println("");

        System.out.println("Sorted array of Cars:");
        for(int i=0; i<sortedArray.length; i++){
            System.out.println(((Car)sortedArray[i]).toString());
        }
    }
}


