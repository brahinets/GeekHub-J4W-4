package task_2;

/**
 * Class: Task
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 02.11.13
 * Time: 12:42
 * Mail: ysb.kanivtsi@gmail.com
 *
 */

/*
*   This class realize operation with object TASK
*
*   fields :
*       category: category of task(Home, Work, ...)
*       description: description of task(Eat food, clean car, ...)
*
*   methods:
*       getDescription : return task description
*       getCategory  : return task category
*       setDescription : set task description
*       setCategory : set task category
*       compareTo : compare two Task object, return -1 or 1
*
* */
public class Task implements Comparable{
    private String category;
    private String description;

    Task(String category, String description){
        this.category = category;
        this.description  = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /*
     *   compare objects of Task by fields,
     *      return EQUAL or NOT EQUAL
    */
    @Override
    public int compareTo(Object o) {
        Task task = (Task) o;
        if(task.getCategory().compareToIgnoreCase(this.category) == 0) {
            if(task.getDescription().compareToIgnoreCase(this.description) == 0) {
                return 1;
            } else {
                return -1;
            }
        } else {
                return -1;
        }
    }
}