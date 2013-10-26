package task_2;

/**
 * Class: WrongPasswordException
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 15:22
 * Mail: ysb.kanivtsi@gmail.com
 */
public class WrongPasswordException extends AuthException {

    public WrongPasswordException(){
    }

    public WrongPasswordException(String msg) {
        super(msg);
    }

}
