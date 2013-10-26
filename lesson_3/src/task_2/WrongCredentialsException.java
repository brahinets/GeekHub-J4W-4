package task_2;

/**
 * Class: WrongCredentialsException
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 15:25
 * Mail: ysb.kanivtsi@gmail.com
 */
public class WrongCredentialsException extends AuthException{

    public WrongCredentialsException(){
    }

    public WrongCredentialsException(String msg) {
        super(msg);
    }
}
