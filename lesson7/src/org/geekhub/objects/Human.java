/**
 * Class: Human
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 30.11.13
 * Time: 17:54
 * Mail: ysb.kanivtsi@gmail.com
 */


package org.geekhub.objects;

public class Human extends Entity{
    private int age;
    private float weight;
    private String name;

    public Human(){
    }

    public Human(String name, int age, float weight){
        this.name = name;
        this.age = age;
        this.weight = weight;
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
