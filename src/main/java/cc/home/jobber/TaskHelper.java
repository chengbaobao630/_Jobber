package cc.home.jobber;

import cc.home.jobber.execute.container.TaskContainer;
import cc.home.jobber.execute.listener.DefaultTaskListener;
import cc.home.jobber.execute.listener.TaskListener;
import cc.home.jobber.execute.monitor.BaseTaskMonitor;
import cc.home.jobber.execute.monitor.TaskMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class TaskHelper {

    private static final Logger logger = LoggerFactory.getLogger(TaskHelper.class);

    private TaskMonitor taskMonitor;

    private TaskListener taskListener;

    private static TaskContainer container;

    private static TaskHelper taskHelper;

    public void setTaskListener(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    public TaskHelper() {
        taskMonitor = new BaseTaskMonitor();
    }

    public Task transport(Task task) {


        return task;
    }

    private void after(Task task) {
    }

    private void process(Task task) {
        if (this.taskListener == null) {
            this.taskListener = new DefaultTaskListener();
        }
        if (task == null) {
            return;
        }
        taskListener.onTaskAdd(task);
        logger.info("register task :" + task.getTaskNum() + ",status is :" + task.getStatus());
        container.addTask(taskMonitor.check(task), task);
    }

    private void per(Task task) {
        container.removeTask(task.getTaskNum());
    }

    public void setContainer(TaskContainer container) {
        this.container = container;
    }

    public void register(Task task) {
        per(task);
        process(task);
        after(task);
    }

    public List<Task> getUseAbleTask(TaskConfig taskConfig) {
        return container.getAvlTasks(taskConfig);
    }

    public void remove(String taskNum) {
        container.removeTask(taskNum);
    }


    public static Map showTask() {
        return container.toMap();
    }

    public void shutdown(String taskNum) {
        try {
            Task task = container.getTask(taskNum);
            if (task != null) {
                task.shutdown();
            } else
                logger.error("no task find  by num: " + taskNum);
        } catch (InterruptedException e) {
            logger.error("taskNum has been shutdown");
        }
    }

    public static TaskHelper newInstance() {
        if (taskHelper == null) {
            taskHelper = new TaskHelper();
        }
        return taskHelper;
    }
}
