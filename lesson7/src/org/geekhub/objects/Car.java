/**
 * Class: Car
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 30.11.13
 * Time: 17:54
 * Mail: ysb.kanivtsi@gmail.com
 */


package org.geekhub.objects;

public class Car  extends Entity{
    private String concern;
    private String color;
    private int maxSpeed;
    private int passengers;

    public Car(){
    }

    public Car(String concern, String color, int maxSpeed, int passengers){
        this.concern = concern;
        this.color = color;
        this.passengers = passengers;
        this.maxSpeed = maxSpeed;
    }

    public String getConcern() {
        return concern;
    }

    public void setConcern(String concern) {
        this.concern = concern;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return ("Concern : "+this.getConcern()+" ,\tColor : "+this.getColor()+" ,\tMax speed : "+this.getMaxSpeed()+" ,\tPassengers :\t"+this.getPassengers());
    }
}
