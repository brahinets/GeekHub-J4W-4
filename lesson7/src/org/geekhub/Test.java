/**
 * Class: Test (Main class)
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 30.11.13
 * Time: 18:10
 * Mail: ysb.kanivtsi@gmail.com
 */

package org.geekhub;

import org.geekhub.objects.Car;
import org.geekhub.objects.Cat;
import org.geekhub.objects.Human;
import org.geekhub.objects.User;
import org.geekhub.storage.DatabaseStorage;
import org.geekhub.storage.Storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        Connection connection = createConnection("root", "", "geekdb");

        Storage storage = new DatabaseStorage(connection);


            /* start test with USER objects */
        List<Cat> cats = storage.list(Cat.class);
        for (Cat cat : cats) {
            storage.delete(cat);
        }
        cats = storage.list(Cat.class);
        if (!cats.isEmpty()) throw new Exception("Cats should not be in database!");

        for(int i = 1; i <= 20; i++) {
            Cat cat = new Cat();
            cat.setName("cat" + i);
            cat.setAge(i);
            storage.save(cat);
        }

        cats = storage.list(Cat.class);
        if (cats.size() != 20) throw new Exception("Number of cats in storage should be 20!");
            /* end test with CAT objects */


            /* start test with USER objects */
        User user = new User();
        user.setAdmin(true);
        user.setAge(23);
        user.setName("Victor");
        user.setBalance(22.23);
        storage.save(user);

        User user1 = storage.get(User.class, user.getId());
        if (!user1.getName().equals(user.getName())) throw new Exception("Users should be equals!");

        user.setAdmin(false);
        storage.save(user);

        User user2 = storage.get(User.class, user.getId());
        if (!user.getAdmin().equals(user2.getAdmin())) throw new Exception("Users should be updated!");

        storage.delete(user1);

        User user3 = storage.get(User.class, user.getId());

        if (user3 != null) throw new Exception("User should be deleted!");
            /* end test with USER objects */


            /* start test with HUMAN objects */
        Human human = new Human();
        human.setAge(10);
        human.setName("John");
        human.setWeight(123);
        storage.save(human);

        Human human1 = storage.get(Human.class, human.getId());
        if (!human1.getName().equals(human.getName())) throw new Exception("Human should be equals!");
        storage.delete(human1);

        Human human3 = new Human();
        human3.setAge(78);
        human3.setName("Lil");
        human3.setWeight(97);
        storage.save(human3);

        storage.delete(human);
        human3 = storage.get(Human.class, human.getId());
        if (human3 != null) throw new Exception("Human should be deleted!");
            /* end test with HUMAN objects */


            /* start test with CAR objects */
        Car car = new Car();
        car.setColor("Red");
        car.setConcern("BMW");
        car.setPassengers(4);
        car.setMaxSpeed(240);
        storage.save(car);

        Car car1 = new Car("Audi", "White", 280, 2);
        storage.save(car1);

        Car car2 = storage.get(Car.class, car.getId());
        car2 = car1;
        if (!car2.getConcern().equals(car1.getConcern())) throw new Exception("Cars should be equal!");
        storage.delete(car1);

        car2 = storage.get(Car.class, car.getId());
        if (!car2.getConcern().equals(car.getConcern())) throw new Exception("Cars should be equal!");
            /* end test with CAR objects */


        System.out.println("OK");
        connection.close();
    }

    private static Connection createConnection(String login, String password, String dbName) throws Exception {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, login, password);
    }
}