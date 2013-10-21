import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Paradoxxx
 * Date: 17.10.13
 * Time: 22:21
 */

/*
* Program about moving car
* user can accelerate car, slower car, turn to side(if speed not big)
*
* input data: fuel count LITTERS
*             maximum speed KM/PH
*             wheels quality
*             engine power in HP
*
*             step in time = 1 hour
* */
public class Car extends AbstractVehicle{
    private double fuelPerHundredKm = 5.2; //  litters/km
    private double gasBank = 50;// current fuel in litters

    Car(int maxSpeed, int gasBank, int enginePower, int wheels){
        this.maxSpeed = maxSpeed;
        this.fuelPerHundredKm = enginePower/100;
        this.gasBank = gasBank;
        this.engine = enginePower;
        this.wheels = wheels;
        this.speedStep = getSpeedStep(enginePower, this.wheels);
    }

    /*
    *  make car faster
    *   fuel gets lower
    *   distance gets bigger
    *   speed gets bigger or not change
    * */
    @Override
    public void accelerate(){
        if(this.gasBank > (this.fuelPerHundredKm*((double)currentSpeed/100))){
            if(this.maxSpeed > (this.currentSpeed+this.speedStep))
                this.currentSpeed += this.speedStep;
            else{
                if (this.currentSpeed == this.maxSpeed)
                    System.out.print("Car already near maximum speed\n");
                else
                    this.currentSpeed = this.maxSpeed;
            }
            this.distance += this.currentSpeed;
            this.gasBank -= this.fuelPerHundredKm*((double)currentSpeed/100);
        } else {
            if(this.gasBank <= this.fuelPerHundredKm*((double)currentSpeed/100) && this.gasBank > 0){
                if(this.maxSpeed > (this.currentSpeed+this.speedStep))
                    this.currentSpeed = (this.currentSpeed+speedStep) % this.maxSpeed;//(this.speedStep*((this.gasBank *100)/this.powerPerHundredKm)/100);
                else{
                    if (this.currentSpeed == this.maxSpeed)
                        System.out.print("Car already near maximum speed\n");
                    else
                        this.currentSpeed = this.maxSpeed;
                }
                this.distance += ((this.gasBank *100)/this.fuelPerHundredKm);
                this.gasBank = 0;
            } else {
                System.out.print("No fuel anymore...\n");
                System.exit(0);
            }
        }
    }

    /*
    *  make car slower
    *   fuel don't change
    *   distance gets bigger or not change
    *   speed gets lower or not change
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
                System.out.print("Car already stopped\n");
            }
        }
    }

    /*
      *  turn car left or right(car must move and speed must be low)
      *   fuel not change
      *   distance get bigger or not change
      *   speed not change
      * */
    @Override
    public void turn(){
        if(this.currentSpeed > 0 && this.currentSpeed < 51 ){
            this.distance += currentSpeed;
            this.gasBank -= this.fuelPerHundredKm*(currentSpeed/100);
            System.out.print("Turned...\n");
         } else {
            if(this.currentSpeed == 0){
                System.out.print("Can't turn, car can't turn and don't move\n");
            }  else {
                System.out.printf("Can't turn, run slower than 51 KM/PH, current speed : %d\n", this.currentSpeed);
            }
         }
    }


    public static void main(String args[]){
        //car : (maxSpeed, gasBank, enginePower, wheelsQuality)
        Car gasCar = new Car(300, 30, 250, 18);
        String input = null;
        int n;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Current speed : " + gasCar.currentSpeed);
        System.out.println("Current distance : " + gasCar.distance);
        System.out.println("Fuel level : "+gasCar.gasBank+"\n");
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
                    gasCar.accelerate();
                    break;
                case 2:
                    gasCar.brake();
                    break;
                case 3:
                    gasCar.turn();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.print("Wrong input...");
            }
            System.out.println("Current speed : " + gasCar.currentSpeed);
            System.out.println("Current distance : " + gasCar.distance);
            System.out.println("Fuel level : "+gasCar.gasBank+"\n");
        }
    }
}

