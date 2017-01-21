package cc.home.jobber.execute.helper;

import cc.home.jobber.execute.container.TaskContainer;
import cc.home.jobber.execute.listener.DefaultTaskListener;
import cc.home.jobber.execute.listener.TaskListener;
import cc.home.jobber.execute.monitor.BaseTaskMonitor;
import cc.home.jobber.execute.monitor.TaskMonitor;
import cc.home.jobber.Task;

import java.util.List;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class TaskHelper {

    private TaskMonitor taskMonitor;

    private TaskListener taskListener;

    private TaskContainer container;

    public TaskHelper() {
        taskMonitor = new BaseTaskMonitor();
    }

    public Task transport(Task task) {
        per(task);
        process(task);
        after(task);
        return task;
    }

    private void after(Task task) {
    }

    private void process(Task task) {
        taskListener.onTaskAdd(task);
        register(task);
    }

    private void per(Task task) {
    }

    public void setContainer(TaskContainer container) {
        this.container = container;
    }

    public void register(Task task) {
        container.addTask(taskMonitor.check(task),task);
        if (this.taskListener == null) {
            this.taskListener = new DefaultTaskListener();
        }
    }

    public List<Task> getUseAbleTask(int size){
//todo
        return container.getTasks();
    }
}
