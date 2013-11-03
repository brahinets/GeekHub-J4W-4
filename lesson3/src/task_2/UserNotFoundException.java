package task_2;

/**
 * Class: UserNotFoundException
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 15:28
 * Mail: ysb.kanivtsi@gmail.com
 */
public class UserNotFoundException extends AuthException {

    public UserNotFoundException(){
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
