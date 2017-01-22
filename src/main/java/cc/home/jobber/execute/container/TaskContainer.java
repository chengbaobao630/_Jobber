package cc.home.jobber.execute.container;

import cc.home.jobber.TaskConfig;
import cc.home.jobber.execute.monitor.CheckResult;
import cc.home.jobber.Task;

import java.util.List;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public interface TaskContainer {

    void init();

    List<Task> getTasks();

    List<Task> listErrorTask();

    void addTask(CheckResult checkResult,Task task);

    void removeTask(String taskNum);

    void destroy();

    List<Task> getAvlTasks(TaskConfig taskConfig);

}
