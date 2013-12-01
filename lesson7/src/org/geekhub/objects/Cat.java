/**
 * Class: Cat
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 30.11.13
 * Time: 17:54
 * Mail: ysb.kanivtsi@gmail.com
 */

package org.geekhub.objects;

public class Cat extends Entity{
    private String name;
    private int age;

    public String getName() {
        return name;
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
}