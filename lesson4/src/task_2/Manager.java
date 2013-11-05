package task_2;

import java.util.*;

/**
 * Class : Manager
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 02.11.13
 * Time: 14:31
 * Mail: ysb.kanivtsi@gmail.com
 */

/*
* This class implements operations of task manager
*   fields :
*       tasks - map of all tasks and their dates Map<Date, Task>
*       tasks - map of all tasks and their dates Map<Date, Task>
*
*    methods :
*       addTask(Date date, Task task) - add task (in no task at this time) to some time
*       removeTask(Date date) - delete task (in there is task at this time) at some time
*       getCategories() - return Set of all tasks categories
*       getTasksByCategories() - return map of task combined on categories
*       getTasksByCategory(String category) - return list of tasks in same category
*       sortListByDates(List<Task> taskList, List<Date> dateList) - return list of task sorted by dates (growing)
*
* */
public class Manager implements TaskManager{

    private Map<Date, Task> tasks = new HashMap<Date, Task>();

    /*
    *  add task to some date (if not exists)
    *
    *  in : Date date, Task task
    *
    *  out : -
    *
    */
    @Override
    public void addTask(Date date, Task task) {
        if(this.tasks.containsKey(date)) {
            //System.out.print( " " +this.tasks.get(date)+" , "+(task)+" ; ");
            if(this.tasks.get(date).equals(task))
                System.out.println("This task already in task list\n");
            else
                System.out.println("There are already task at this time\n");
        } else {
                this.tasks.put(date, task);
                System.out.println("Task added successfully\n");
        }
    }



    /*
    *  remove task in some date (if exists)
    *
    *  in : Date date
    *
    *  out : -
    */
    @Override
    public void removeTask(Date date) {
        if(this.tasks.containsKey(date)) {
                this.tasks.remove(date);
                System.out.println("Task removed successfully\n");
        } else {
            System.out.println("No tasks on this date\n");
        }
    }



    /*
    *  return list of all task's categories
    *
    *  in : -
    *
    *  out : Collection<String>
    */
    @Override
    public Collection<String> getCategories() {
        Set<String> categories = new HashSet<String>();

        for(Task val: tasks.values()){
            categories.add(val.getCategory());
        }
        return categories;
    }



    /*
    * return all task's combined to categories
    *
    *  in : -
    *
    *  out : Map<String, List<Task>>
    */
    @Override
    public Map<String, List<Task>> getTasksByCategories() {
        Map<String, List<Task>> tasksByCategories = new HashMap<String, List<Task>>();
        List<Task> tasksListWithSameCategory;
        System.out.println();
        for(String cat: getCategories()){
            tasksListWithSameCategory = getTasksByCategory(cat);
            tasksByCategories.put(cat, tasksListWithSameCategory);
        }
        return tasksByCategories;
    }



    /*
    *  return list of all task's description from some category sorted by time
    *
    *  in : String category
    *
    *  out : List<Task>
    */
    @Override
    public List<Task> getTasksByCategory(String category) {
        List<Task> tasksListWithSameCategory = new ArrayList<Task>();
        List<Date> tasksListWithSameCategoryDate = new ArrayList<Date>();
        for(Date val: tasks.keySet()){
            if((category).compareTo(tasks.get(val).getCategory()) == 0){
                tasksListWithSameCategory.add(tasks.get(val));
                tasksListWithSameCategoryDate.add(val);
            }
        }
        tasksListWithSameCategory = sortListByDates(tasksListWithSameCategory, tasksListWithSameCategoryDate);

        return tasksListWithSameCategory;
    }



    /*
    *  return list of all task's at today sorted by time
    *
    *  in : -
    *
    *  out: List<Task>
    */
    @Override
    public List<Task> getTasksForToday() {
        List<Task> tasksListForToday = new ArrayList<Task>();
        List<Date> tasksListForTodayDates = new ArrayList<Date>();
        for(Date val: tasks.keySet()){
            if((val.getYear()-(new Date()).getYear() == 0) && (val.getMonth()-(new Date()).getMonth() == 0) && (val.getDate()-(new Date()).getDate() == 0)){
                tasksListForToday.add(tasks.get(val));
                tasksListForTodayDates.add(val);
            }
        }

        tasksListForToday = sortListByDates(tasksListForToday, tasksListForTodayDates);
        return tasksListForToday;
    }



    /*
    * sort list of tasks sorted by their dates
    *
    * in:   List<Task> taskList
    *       List<Date> dateList
    *
    * out:  List<Task> sortedTaskList
    *
    * */
    public List<Task> sortListByDates(List<Task> taskList, List<Date> dateList) {
        int a_length = taskList.size();
        Date tmpD;
        Task tmpT;
        /* sort by date */
        for(int j=a_length; j>=2;j--){
            for(int i=0; i<j-1; i++){
                if(dateList.get(i).compareTo(dateList.get(i+1)) > 0){
                    tmpD = dateList.get(i);
                    tmpT = taskList.get(i);

                    dateList.set(i, dateList.get(i+1));
                    taskList.set(i, taskList.get(i+1));

                    dateList.set(i+1, tmpD);
                    taskList.set(i+1, tmpT);
                }
            }
        }
        return taskList;
    }

}
