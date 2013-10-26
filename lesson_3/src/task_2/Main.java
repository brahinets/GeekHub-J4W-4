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
    public static void main(String args[]) throws AuthException {
        List<User> users = new ArrayList<>();
        users.add(0, new User("login1","pass1"));
        users.add(1, new User("login1","pass1"));
        users.add(1, new User("login1","pass1"));
        users.add(2, new User("login2","pass2"));
        users.add(3, new User("login3","pass3"));
        users.add(4, new User("login4","pass4"));
        users.add(5, new User("login5","pass5"));

        User user = new User("login1","pass1");
        User user1 = new User("login1","pass");
        User user2 = new User("","");

        System.out.println("\nTry to login user with login: '"+user.login+"', and pass '"+user.password+"'");
        try{
            user.auth(users);
            System.out.println("User '"+user.login+"' successfully logined");
        } catch (WrongCredentialsException e) {
            System.out.println("Caught " + e);
        } catch (UserNotFoundException e) {
            System.out.println("Caught " + e);
        } catch (WrongPasswordException e) {
            System.out.println("Caught " + e);
        }

        System.out.println("\nTry to login user with login: '"+user1.login+"', and pass '"+user1.password+"'");
        try{
            user1.auth(users);
            System.out.println("User '"+user1.login+"' successfully logined");
        } catch (WrongCredentialsException e) {
            System.out.println("Caught " + e);
        } catch (UserNotFoundException e) {
            System.out.println("Caught " + e);
        } catch (WrongPasswordException e) {
            System.out.println("Caught " + e);
        }

        System.out.println("\nTry to login user with login: '"+user2.login+"', and pass '"+user2.password+"'");
        try{
            user2.auth(users);
            System.out.println("User '"+user2.login+"' successfully logined");
        } catch (WrongCredentialsException e) {
            System.out.println("Caught " + e);
        } catch (UserNotFoundException e) {
            System.out.println("Caught " + e);
        } catch (WrongPasswordException e) {
            System.out.println("Caught " + e);
        }
    }
}
