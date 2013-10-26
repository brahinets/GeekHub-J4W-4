package task_2;

import java.util.List;

/**
 * Class: User
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 13:10
 * Mail: ysb.kanivtsi@gmail.com
 *
 * User has:
 *   - login
 *   - password
 *
 * Task:
 *   make authorisation process
 *
 **/
public class User {

    String login;
    String password;

    User(String login, String password){
        this.login = login;
        this.password = password;
    }

    User auth(List<User> users) throws AuthException{
        boolean log = false;
        boolean goodLogBadPass = false;
        /* if length of login or pass == 0 then WrongCredentialsException*/
        if((this.login).trim().length()==0 || this.password.trim().length()==0){
            throw new WrongCredentialsException();
        }

        /* if good login and wrong pass then WrongPasswordException */
        for(int i=0; i<users.size(); i++){
            if(((this.login).compareTo(users.get(i).login) == 0) && ((this.password).compareTo(users.get(i).password) != 0)){
                goodLogBadPass = true;
                break;
            }
        }
        if(goodLogBadPass == true)
            throw new WrongPasswordException();


        /* if no users with such login and pass then UserNotFoundException */
        for(int i=0; i<users.size(); i++){
            if(((this.login).compareTo(users.get(i).login) == 0) && ((this.password).compareTo(users.get(i).password) == 0))
                    log = true;
        }
        if(log == false)
            throw new UserNotFoundException();

        return new User(this.login, this.password);
    }
}