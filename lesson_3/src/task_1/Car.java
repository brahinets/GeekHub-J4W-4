package task_1;

/**
 * Class: Car
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 17:46
 * Mail: ysb.kanivtsi@gmail.com
 */

/*
*   class for working with object Car
*   fields
*       concern
*       color
*       maximum speed
*       count of maximum passengers
*   methods
*       - Set\Get Concern
*       - Set\Get Color
*       - Set\Get MaxSpeed
 *      - Set\Get Passengers
 *      - comparing of two Car objects by their fields
* */
public class Car implements Comparable{
    private String concern;
    private String color;
    private int maxSpeed;
    private int passengers;

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

    /*
     *    compare two objects of Cars by concern,
     *       if equal then compare by MaxSpeed,
     *       if equal then compare by color,
     *       if equal to, then compare by passengers
     *    Concern -> MaxSpeed -> Color -> Passengers
     */
    @Override
    public int compareTo(Object o) {
        Car cmpCar = (Car) o;
        /* if concerns equal then compare maxSpeed, color, passengers */
        if(cmpCar.concern.compareToIgnoreCase(this.concern) == 0) {
            /* if maxSpeed equal then compare color, passengers */
            if(cmpCar.maxSpeed == this.maxSpeed) {
                /* if color equal then compare passengers */
                if(cmpCar.color.compareToIgnoreCase(this.color) == 0) {
                    /* if passengers equal then return 0 */
                    if(cmpCar.passengers == this.passengers) {
                        return 0;
                    } else {
                        if(cmpCar.passengers < this.passengers) {
                            return -1;
                        } else{
                            return 1;
                        }
                    }
                } else {
                    if(cmpCar.color.compareToIgnoreCase(this.color) < 0) {
                        return -1;
                    } else{
                        return 1;
                    }
                }
            } else {
                if(cmpCar.maxSpeed < this.maxSpeed) {
                    return -1;
                } else{
                    return 1;
                }
            }
        } else {
            if(cmpCar.concern.compareToIgnoreCase(this.concern) < 0) {
                return -1;
            } else{
                return 1;
            }
        }
    }
}
