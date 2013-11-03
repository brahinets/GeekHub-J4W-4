package task_1;

/**
 * Class: Human
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 18:36
 * Mail: ysb.kanivtsi@gmail.com
 */

/*
*   class for working with object Human
*   fields
*       Name
*       Age
*       Weight
*   methods
*       - Set\Get Name
*       - Set\Get Weight
*       - Set\Get Age
*      - comparing of two Human objects by their fields
* */
public class Human implements Comparable{
    private int age;
    private float weight;
    private String name;

    public Human(String name, int age, float weight){
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    /*
     *   compare objects of Humans by name,
     *      if equal then compare by age,
     *      if equal to, then compare by weight
     *   Name -> age -> weigh
    */
    @Override
    public int compareTo(Object o) {
        Human cmpHuman = (Human) o;
        /* if names equal then compare age and weight*/
        if(cmpHuman.name.compareToIgnoreCase(this.name) == 0) {
            /* if ages equal then compare age and weight*/
            if(cmpHuman.age == this.age) {
                /* if weight equal then compare age and weight*/
                if(cmpHuman.weight == this.weight) {
                    /* if everything equal then return 0 */
                    return 0;
                } else {
                    if(cmpHuman.weight < this.weight) {
                        return -1;
                    } else{
                        return 1;
                    }
                }
            } else {
                if(cmpHuman.age < this.age) {
                    return -1;
                } else{
                    return 1;
                }
            }
        } else {
            if(cmpHuman.name.compareToIgnoreCase(this.name) < 0) {
                return -1;
            } else{
                return 1;
            }
        }
    }


    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return ("Name : "+this.getName()+" ,\tAge : "+this.getAge()+" ,\tWeight : "+this.getWeight());
    }
}
