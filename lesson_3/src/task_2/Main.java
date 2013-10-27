package task_2;

import java.util.List;
import java.util.ArrayList;

/**
 * Class: Main
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 14:56
 * Mail: ysb.kanivtsi@gmail.com
 */
public class Main {

    /*
    * testing of users login
    * */
    public static void main(String args[])  {
        List<User> users = new ArrayList<>();
        users.add(0, new User("login0","pass0"));
        users.add(1, new User("login1","pass1"));
        users.add(2, new User("login2","pass2"));
        users.add(3, new User("login3","pass3"));
        users.add(4, new User("login4","pass4"));
        users.add(5, new User("login5","pass5"));

        User userTest = new User("login1","pass1");
        User userTest1 = new User("login1","pass");
        User userTest2 = new User("","");

        System.out.println("\nTry to login user with login: '"+userTest.getLogin()+"', and pass '"+userTest.getPassword()+"'");
        try{
            userTest.auth(users);
            System.out.println("User '"+userTest.getLogin()+"' successfully logined");
        } catch (AuthException e) {
            System.out.println("Caught " + e);
        }
        System.out.println("\nTry to login user with login: '"+userTest1.getLogin()+"', and pass '"+userTest1.getPassword()+"'");
        try{
            userTest1.auth(users);
            System.out.println("User '"+userTest1.getLogin()+"' successfully logined");
        } catch (AuthException e) {
            System.out.println("Caught " + e);
        }

        System.out.println("\nTry to login user with login: '"+userTest2.getLogin()+"', and pass '"+userTest2.getPassword()+"'");
        try{
            userTest2.auth(users);
            System.out.println("User '"+userTest2.getLogin()+"' successfully logined");
        } catch (AuthException e) {
            System.out.println("Caught " + e);
        }
    }
}
