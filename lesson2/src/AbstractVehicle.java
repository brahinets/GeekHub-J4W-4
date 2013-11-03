/**
 * Created with IntelliJ IDEA.
 * User: Paradoxxx
 * Date: 18.10.13
 * Time: 12:36
 */
abstract class AbstractVehicle implements Driveable{

    int engine = 300;       //    power of engine in HP
    int currentSpeed = 0;   //    currentSpeed
    int speedStep = 10;     //    speed step
    int maxSpeed = 200;     //    maximum
    double distance = 0;    //    roaded distance
    int wheels = 18;        //    wheels quality

    /* return car speed step in KM/PH */
    int getSpeedStep(int enginePower, int wheels){
        if(wheels == 0) {
            return (int) Math.round(Math.sqrt(enginePower / 10));
        } else {
            return (int) Math.round(Math.sqrt(enginePower * wheels / 10));
        }
    }

}