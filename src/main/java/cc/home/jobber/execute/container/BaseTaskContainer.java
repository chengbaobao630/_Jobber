package cc.home.jobber.execute.container;

import cc.home.jobber.JobConfig;
import cc.home.jobber.execute.task.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class BaseTaskContainer implements TaskContainer {

    private JobConfig jobConfig;

    private LinkedList<Task> taskList;

    private ArrayList<Task> errorTasklist;

    public BaseTaskContainer(JobConfig jobConfig) {
        this.jobConfig = jobConfig;
    }

    public void init() {
        taskList=new LinkedList();
        errorTasklist=new ArrayList<>();
    }

    public void destory() {
        if(taskList != null && taskList.size() >= 0 ){
            taskList.clear();
            taskList = null;
        }

        if(errorTasklist != null && errorTasklist.size() >= 0 ){
            errorTasklist.clear();
            errorTasklist = null;
        }
    }

    public List<Task> getTasks() {
        return null;
    }

    public List<Task> listErrorTask() {
        return null;
    }

    public void addTask() {

    }

    public void removeTask() {

    }
}
