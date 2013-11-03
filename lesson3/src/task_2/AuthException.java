package task_2;

/**
 * Class: AuthException
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 15:22
 * Mail: ysb.kanivtsi@gmail.com
 *
 *
 */
abstract class AuthException extends Exception{
    public AuthException(){

    }

    public AuthException(String msg) {
        super(msg);
    }
}
