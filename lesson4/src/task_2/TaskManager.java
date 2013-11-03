package task_2;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface: TaskManager
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 02.11.13
 * Time: 13:30
 * Mail: ysb.kanivtsi@gmail.com
 */

/*
* This interface describes operations of task manager
* */
public interface TaskManager {

    public void addTask(Date date, Task task);

    public void removeTask(Date date);

    public Collection<String> getCategories();

    //For next 3 methods tasks should be sorted by scheduled date
    public Map<String, List<Task>> getTasksByCategories();

    public List<Task> getTasksByCategory(String category);

    public List<Task> getTasksForToday();

}
