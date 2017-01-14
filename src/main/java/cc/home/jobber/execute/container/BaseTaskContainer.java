package cc.home.jobber.execute.container;


import cc.home.jobber.execute.monitor.CheckResult;
import cc.home.jobber.execute.monitor.TaskCheckResult;
import cc.home.jobber.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class BaseTaskContainer implements TaskContainer {

    private TaskList taskList;

    public BaseTaskContainer() {
    }


    @Override
    public void init() {
        taskList=new TaskList();
    }

    public List<Task> getTasks() {
        return null;
    }

    public List<Task> listErrorTask() {
        return null;
    }

    @Override
    public void addTask(CheckResult checkResult,Task task) {
        TaskCheckResult taskCheckResult= (TaskCheckResult) checkResult;
        taskList.get(taskCheckResult.getRes_code()).add(task);

    }

    @Override
    public void removeTask(String taskNum) {

    }

    @Override
    public void destroy() {

    }



    class TaskList implements Tuple<Integer,List> {

        TaskList[] taskLists;

        private Integer listTag;

        private List data;

        public TaskList() {
            init();
        }

        public TaskList(Integer listTag, List data) {
            this.listTag = listTag;
            this.data = data;
        }

        public void init() {
            taskList=new LinkedList();
            errorTaskList=new ArrayList<>();
            redoTaskList = new LinkedList();
            priTaskList = new LinkedList();
            taskLists = new TaskList[]{
                    new TaskList(Task.JOB_ERROR,errorTaskList),
                    new TaskList(Task.JOB_NORMAL,taskList),
                    new TaskList(Task.JOB_REDO,redoTaskList),
                    new TaskList(Task.JOB_PRI,priTaskList)
            };
        }

        private  LinkedList<Task> taskList;

        private  ArrayList<Task> errorTaskList;

        private  LinkedList<Task> redoTaskList;

        private  LinkedList<Task> priTaskList;


        @Override
        public List get(Integer tag) {
            for (TaskList taskList : taskLists){
                if (taskList.listTag == tag){
                    return taskList.data;
                }
            }
            return null;
        }

        @Override
        public void set(Integer integer, List list) {

        }

    }




}
