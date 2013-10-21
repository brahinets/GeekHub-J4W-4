import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Paradoxxx
 * Date: 18.10.13
 * Time: 21:23
 */


/*
* Program about moving boat on river
* user can accelerate boat, slower boat, turn to side(if speed not big)
*
* input data: boaterMaxSpeed KM/PH, maximum performance of boater
*             stream speed KM/PH
*
*             step in time = 1 hour
*             full speed = stream speed + boater speed
* */
public class Boat implements Driveable{

    private double streamSpeed = 0; // speed of river stream
    private double boaterMaxSpeed = 0; // maximum speed of boater (in KM/PH)
    private double stepSpeed; // difference between boater speeds(in KM/PH)
    private double fullSpeed = 0; // speed of boat (boater+stream)(in KM/PH)
    private double distance = 0; // roaded distance

    Boat(double boaterMaxSpeed, double streamSpeed){
        this.boaterMaxSpeed = boaterMaxSpeed;
        this.streamSpeed = streamSpeed;
        this.fullSpeed = this.streamSpeed;
        this.stepSpeed =   this.boaterMaxSpeed / 10;
    }

    /*
    *  make boat faster
    *   distance gets bigger
    *   speed gets bigger or not change
    * */
    public void accelerate(){
        if(boaterMaxSpeed > (Math.abs(this.fullSpeed) - Math.abs(this.streamSpeed)))
            this.fullSpeed += this.stepSpeed;
        else
            System.out.println("Boater work on full power");
        this.distance += this.fullSpeed;
    }

    /*
   *  make boat slower
   *   distance gets bigger or not change
   *   speed gets lower or not change
   * */
    public void brake(){
        if(this.fullSpeed > 0 && (this.fullSpeed-this.stepSpeed)>=0){
            this.fullSpeed -= stepSpeed;
            this.distance += this.fullSpeed;
        } else {
            if(this.fullSpeed < 0 && (this.fullSpeed+this.stepSpeed)<0){
                this.fullSpeed += stepSpeed;
                this.distance += this.fullSpeed;
            } else {
                this.fullSpeed = 0;
                System.out.println("Boat don't moving");
            }
        }
    }

    /*
    *  turn boat left or right(boat must move and speed must be low)
    *   distance get bigger or not change
    *   speed not change
    * */
    public void turn(){
        if(this.fullSpeed > 15){
            System.out.printf("Can't turn, very fast movement ");
        } else {
            System.out.print("Turned...\n");
            this.distance += this.fullSpeed;
        }
    }


    public static void main(String args[]){
        //Boat : (boaterMaxSpeed, streamSpeed)
        Boat boat = new Boat(20, 10);
        String input = null;
        int n;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Current speed : " + boat.fullSpeed);
        System.out.println("Current distance : " + boat.distance);
        while(true ){
            System.out.print("\nCase variant : \t1- Accelerate\t2- Break\t3- Turn\t0- Exit");
            try {
                input = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            n = Integer.parseInt(input);

            switch (n){
                case 1:
                    boat.accelerate();
                    break;
                case 2:
                    boat.brake();
                    break;
                case 3:
                    boat.turn();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.print("Wrong input...");
                    continue;
            }
            System.out.println("Current speed : " + boat.fullSpeed);
            System.out.println("Current distance : " + boat.distance);
        }
    }
}