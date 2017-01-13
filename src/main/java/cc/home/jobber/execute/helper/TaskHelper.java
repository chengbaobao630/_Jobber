package cc.home.jobber.execute.helper;

import cc.home.jobber.execute.listener.TaskListener;
import cc.home.jobber.execute.monitor.TaskMonitor;
import cc.home.jobber.execute.task.Task;

import java.util.List;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class TaskHelper{

    TaskMonitor taskMonitor;

    TaskListener taskListener;


    public TaskHelper() {
    }

    public void  transport(Task task){

        per(task);
        process(task);
        after(task);

    }

    private void after(Task task) {
    }

    private void process(Task task) {
        taskListener.onTaskAdd(task);
        check(task);
    }

    private void per(Task task) {
    }

    boolean check(Task task){
        return taskMonitor.check(task);
    }

}
