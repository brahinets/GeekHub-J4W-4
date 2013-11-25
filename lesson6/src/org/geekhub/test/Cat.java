/**
 * Class: Cat
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 23.11.13
 * Time: 12:58
 * Mail: ysb.kanivtsi@gmail.com
 */


package org.geekhub.test;

import org.geekhub.json.Ignore;
import org.geekhub.json.adapters.*;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Simple Cat that will be used for testing JSON serialization.
 */
public class Cat {

    private int age;
    private String name;
    @Ignore private Cat myself;
    @UseDataAdapter(DateAdapter.class)      private Date birthDate;
    @UseDataAdapter(ColorAdapter.class)     private Color color;
    @UseDataAdapter(CollectionAdapter.class)private List<Integer> whiskers = new ArrayList<>();
    @UseDataAdapter(MapAdapter.class)       private Map<String, Paw> paws = new HashMap<>();

    public Cat() {
        myself = this;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Map<String, Paw> getPaws() {
        return paws;
    }

    public void setPaws(Map<String, Paw> paws) {
        this.paws = paws;
    }

    public List<Integer> getWhiskers() {
        return whiskers;
    }

    public void setWhiskers(List<Integer> whiskers) {
        this.whiskers = whiskers;
    }

    public Cat getMyself() {
        return myself;
    }

    public void setMyself(Cat myself) {
        this.myself = myself;
    }
}