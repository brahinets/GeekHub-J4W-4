import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Paradoxxx
 * Date: 18.10.13
 * Time: 12:23
 */


/*
* Program about moving solar car
* user can accelerate car, slower car, turn to side(if speed not big)
*
* input data: wheels quality
*             maximum speed KM/PH
*             engine power in HP
*
*             step in time = 1 hour
* */

public class SolarPoweredCar extends AbstractVehicle {

        SolarPoweredCar(int maxSpeed, int enginePower, int wheels){
        this.maxSpeed = maxSpeed;
        this.engine = enginePower;
        this.speedStep = getSpeedStep(enginePower, wheels);
    }

    /*
    *  make car faster
    *   distance gets bigger  or not change
    *   speed gets bigger   or not change
    * */
    @Override
    public void accelerate(){
                if(this.maxSpeed > (this.currentSpeed+this.speedStep))
                    this.currentSpeed += this.speedStep;
                else{
                    if (this.currentSpeed == this.maxSpeed)
                        System.out.print("Car already near maximum speed\n");
                    else
                        this.currentSpeed = this.maxSpeed;
                }
                this.distance += this.currentSpeed;
        }

    /*
    *  make car slower
    *   distance gets bigger  or not change
    *   speed gets lower  or not change
    * */
    @Override
     public void brake(){
            if(this.currentSpeed > this.speedStep){
                this.distance += this.currentSpeed;
                this.currentSpeed -= this.speedStep;
            } else {
                if(this.currentSpeed <= this.speedStep && this.currentSpeed > 0){
                    this.distance += this.currentSpeed;
                    this.currentSpeed = 0;
                } else {
                    System.out.print("Car don't moving now\n");
                }
            }
        }

    /*
     *  turn car left or right(car must move and speed must be low)
     *   fuel not change
     *   distance get bigger  or not change
     *   speed not change
     * */
    @Override
    public void turn(){
        if(this.currentSpeed > 0 && this.currentSpeed < 25 ){
            System.out.print("Turned...\n");
            this.distance += currentSpeed;
        } else {
            if(this.currentSpeed == 0){
                System.out.print("Can't turn, car can't turn and don't move\n");
            }  else {
                System.out.printf("Can't turn, run slower than 25 KM/PH, current speed : %d\n", this.currentSpeed);
            }
        }
    }


    public static void main(String args[]){
        //сar : (maxSpeed, gasBank, enginePower, wheelsQuality)
        SolarPoweredCar solarCar = new SolarPoweredCar(200, 250, 17);
        String input = null;
        int n;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Current speed : " + solarCar.currentSpeed);
        System.out.println("Current distance : " + solarCar.distance);
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
                    solarCar.accelerate();
                    break;
                case 2:
                    solarCar.brake();
                    break;
                case 3:
                    solarCar.turn();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.print("Wrong input...");
                    continue;
            }
            System.out.println("Current speed : " + solarCar.currentSpeed);
            System.out.println("Current distance : " + solarCar.distance);
        }
    }
}
