package cc.home.jobber.execute.container;

import cc.home.jobber.execute.task.Task;

import java.util.List;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public interface TaskContainer {

    void init();

    List<Task> getTasks();

    List<Task> listErrorTask();

    void addTask();

    void removeTask();

    void destory();

}
