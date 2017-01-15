package cc.home.jobber.execute.container;


import cc.home.jobber.Task;
import cc.home.jobber.execute.monitor.CheckResult;
import cc.home.jobber.execute.monitor.TaskCheckResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class BaseTaskContainer implements TaskContainer {

    private static final Logger logger = LoggerFactory.getLogger(BaseTaskContainer.class);

    private static TaskTuple taskTuple;

    public BaseTaskContainer() {
        this.init();
    }

    public static Map<String, List<Task>> toMap() {
        Map<String, List<Task>> listMap = new HashMap<>();
        listMap.put("_normalTask", taskTuple.taskList);
        listMap.put("_errorTask", taskTuple.errorTaskList);
        listMap.put("_priTask", taskTuple.priTaskList);
        listMap.put("_redoTask", taskTuple.redoTaskList);
        return listMap;
    }


    @Override
    public void init() {
        taskTuple = new TaskTuple();
    }

    public List<Task> getTasks() {
        return taskTuple.taskList;
    }

    public List<Task> listErrorTask() {
        return taskTuple.errorTaskList;
    }

    public List<Task> listPriTask() {
        return taskTuple.priTaskList;
    }

    public List<Task> listRedorTask() {
        return taskTuple.redoTaskList;
    }

    public List<Task> listNormalTask() {
        return taskTuple.taskList;
    }


    @Override
    public void addTask(CheckResult checkResult, Task task) {
        TaskCheckResult taskCheckResult = (TaskCheckResult) checkResult;
        taskTuple.get(taskCheckResult.getRes_code()).add(task);
    }

    @Override
    public void removeTask(String taskNum) {

    }

    @Override
    public void destroy() {

    }


    class TaskTuple implements Tuple<Integer, List> {

        TaskTuple[] taskTuples;

        private Integer listTag;

        private List data;

        public TaskTuple() {
            init();
        }

        public TaskTuple(Integer listTag, List data) {
            this.listTag = listTag;
            this.data = data;
        }

        public void init() {
            taskList = new LinkedList();
            errorTaskList = new ArrayList<>();
            redoTaskList = new LinkedList();
            priTaskList = new LinkedList();
            taskTuples = new TaskTuple[]{
                    new TaskTuple(Task.JOB_ERROR, errorTaskList),
                    new TaskTuple(Task.JOB_NORMAL, taskList),
                    new TaskTuple(Task.JOB_REDO, redoTaskList),
                    new TaskTuple(Task.JOB_PRI, priTaskList)
            };
        }

        private LinkedList<Task> taskList;

        private ArrayList<Task> errorTaskList;

        private LinkedList<Task> redoTaskList;

        private LinkedList<Task> priTaskList;


        @Override
        public List get(Integer tag) {
            for (TaskTuple taskTuple : taskTuples) {
                if (taskTuple.listTag == tag) {
                    return taskTuple.data;
                }
            }
            return taskList;
        }

        @Override
        public void set(Integer integer, List list) {

        }

        private List<Task> unionTask() {
            List<Task> tasks = new ArrayList<>();
            toList(tasks, taskList, errorTaskList, priTaskList, redoTaskList);
            return tasks;
        }

        private void toList(List<Task> resultList, List<Task>... oriLists) {
            for (List<Task> tasks : oriLists) {
                ListIterator<Task> it = tasks.listIterator();
                while (it.hasNext()) {
                    resultList.add(it.next());
                }
            }
        }
    }
}
