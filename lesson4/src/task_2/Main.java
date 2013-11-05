package task_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Class: Car
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 02.11.13
 * Time: 11:32
 * Mail: ysb.kanivtsi@gmail.com
 */

 /*
 *   Main class for work with task manager
 */

public class Main {

    public static void main(String args[]){
        Main tester = new Main();
        tester.test();
    }


    /*
     * test of taskManager
     */
    public void test(){
        Manager testManager = new Manager();

        testManager.addTask(new Date(2013-1900, 10-1, 20, 12, 5),new Task("Home","Open door"));
        testManager.addTask(new Date(2013-1900, 10-1, 20, 5, 6),new Task("Home","Open door"));
        testManager.addTask(new Date(2013-1900, 10-1, 20, 7, 5),new Task("Home","Go sleep"));
        testManager.addTask(new Date(2013-1900, 11-1, 03, 11, 5),new Task("Home","Eat some food"));
        testManager.addTask(new Date(2013-1900, 11-1, 03, 10, 5),new Task("Class","Clean board"));
        testManager.addTask(new Date(2013-1900, 11-1, 03, 12, 5),new Task("Class","Clean room"));
        testManager.addTask(new Date(2013-1900, 12-1, 20, 6, 5),new Task("Class","Teach students"));
        testManager.addTask(new Date(2013-1900, 12-1, 20, 5, 5),new Task("Work","Close office"));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int year,  month, data, hour, min;
        String input = null, taskGroup = null, taskDescription = null;
        int n = -1;
        while(n != 0){
            try {
                System.out.print("\n0 - Exit\t1 - Add Task\t2 - Delete task\t3 - Get task categories");
                System.out.print("\n4 - Get task lists by categories\t5 - Get tasks in some category\t6 - Get task on today");
                System.out.print("\nInput choice number : ");
                input = bufferedReader.readLine();
                n = Integer.parseInt(input);
                switch (n){
                    case 1:
                        System.out.print("\nTask date:\n\tYear=");
                        year = Integer.parseInt(bufferedReader.readLine());
                        System.out.print("\tMonth=");
                        month = Integer.parseInt(bufferedReader.readLine());
                        System.out.print("\tDate=");
                        data = Integer.parseInt(bufferedReader.readLine());
                        System.out.print("\tHour=");
                        hour = Integer.parseInt(bufferedReader.readLine());
                        System.out.print("\tMin=");
                        min = Integer.parseInt(bufferedReader.readLine());
                        System.out.print("\nTask:\n\tgroup=");
                        taskGroup = bufferedReader.readLine();
                        System.out.print("\tdescription=");
                        taskDescription = bufferedReader.readLine();
                        testManager.addTask(new Date(year-1900, month-1, data, hour, min), new Task(taskGroup, taskDescription));
                        break;
                    case 2:
                        System.out.print("\nTask date:\n\tYear=");
                        year = Integer.parseInt(bufferedReader.readLine());
                        System.out.print("\tMonth=");
                        month = Integer.parseInt(bufferedReader.readLine());
                        System.out.print("\tDate=");
                        data = Integer.parseInt(bufferedReader.readLine());
                        System.out.print("\tHour=");
                        hour = Integer.parseInt(bufferedReader.readLine());
                        System.out.print("\tMin=");
                        min = Integer.parseInt(bufferedReader.readLine());
                        testManager.removeTask(new Date(year-1900, month-1, data, hour, min));
                        break;
                    case 3:
                        if(testManager.getCategories().size() != 0){
                            Collection<String> categories = testManager.getCategories();
                            System.out.print("Tasks categories : ");
                            for(String cat: categories){
                                System.out.print(cat+";  ");
                            }
                            System.out.println();
                        } else {
                            System.out.print("No tasks in list\n");
                        }
                        break;
                    case 4:
                        Map<String, List<Task>> tasksByCategories = testManager.getTasksByCategories();
                        if(tasksByCategories.size() != 0){
                            for(String val: tasksByCategories.keySet()){
                                System.out.print("Tasks in category '"+val+"' :\t\t");
                                for(Task task: tasksByCategories.get(val)){
                                    System.out.print(task.getDescription()+";  ");
                                }
                                System.out.println();
                            }
                        }  else {
                            System.out.print("No tasks in list\n");
                        }
                        break;
                    case 5:
                        System.out.print("\nInput task group : ");
                        taskGroup = bufferedReader.readLine();
                        List<Task> tasksListWithSameCategory = testManager.getTasksByCategory(taskGroup);
                        if(tasksListWithSameCategory.size() != 0){
                            System.out.print("Tasks in group '"+taskGroup+"' : ");
                            for(Task cat: tasksListWithSameCategory){
                                System.out.print(cat.getDescription()+";  ");
                            }
                            System.out.print("\n");
                        } else {
                            System.out.print("No tasks with such group\n");
                        }
                        break;
                    case 6:
                        List<Task> tasksListOnToday = testManager.getTasksForToday();
                        if(tasksListOnToday.size() != 0){
                            System.out.print("Tasks for today:  ");
                            for(Task cat: tasksListOnToday){
                                System.out.print(cat.getDescription() + ";  ");
                            }
                            System.out.print("\n");
                        } else {
                            System.out.print("No tasks today\n");
                        }
                        break;
                    case 0:
                        System.exit(0);
                }

            } catch (NumberFormatException ex) {
                System.out.println("\tSorry, please input integer number !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
