package cc.home.jobber.execute.container;


import cc.home.jobber.Task;
import cc.home.jobber.TaskConfig;
import cc.home.jobber.execute.monitor.CheckResult;
import cc.home.jobber.execute.monitor.TaskCheckResult;
import cc.home.jobber.execute.task.NullTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

    private static Map<String,Location> locationMap =new ConcurrentHashMap<>();

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

    public List<Task> listErrorTask(int num) {
        return taskTuple.errorTaskList.subList(0,num -1);
    }

    public List<Task> listPriTask() {
        return taskTuple.priTaskList;
    }

    public List<Task> listPriTask(int num) {
        int size = taskTuple.priTaskList.size();
        return size > 0 ? taskTuple.priTaskList.subList(0 , num -1 > size ? size : num -1) : null;
    }

    public List<Task> listRedoTask() {
        return taskTuple.redoTaskList;
    }

    public List<Task> listRedoTask(int num) {
        int size = taskTuple.redoTaskList.size();
        return size > 0 ? taskTuple.redoTaskList.subList(0 , num -1 > size ? size : num -1) : null;
    }

    public List<Task> listNormalTask() {
        return taskTuple.taskList;
    }

    public List<Task> listNormalTask(int num) {
        int size = taskTuple.taskList.size();
        return size > 0 ? taskTuple.taskList.subList(0 , num -1 > size ? size : num -1) : null;
    }


    @Override
    public void addTask(CheckResult checkResult, Task task) {
        TaskCheckResult taskCheckResult = (TaskCheckResult) checkResult;
        int taskTag = taskCheckResult.getRes_code() == 0 ?
                1 : taskCheckResult.getRes_code();
        List<Task> tasks=taskTuple.get(taskTag);
        tasks.add(task);
        locationMap.put(task.getTaskNum(),new Location(taskTag,tasks.size() - 1));
    }

    @Override
    public void removeTask(String taskNum) {
        Location location = locationMap.get(taskNum);
        taskTuple.get(location.getListTag()).set(location.getIndex(),new NullTask(0,0));
        locationMap.remove(taskNum);
        locationClear();
    }

    private void locationClear() {
        for (TaskTuple tuple : taskTuple.taskTuples) {
            for (Object obj : tuple.data) {
                if( !(obj instanceof NullTask)){
                    return ;
                }
            }
            tuple.data.clear();
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public List<Task> getAvlTasks(TaskConfig taskConfig) {
        int maxSize = taskConfig.getMaxTaskNum();
        List<Task> tasks=new ArrayList<>(maxSize);
        int priSize = taskConfig.getPriPassNum();
        List<Task> priLists= listPriTask(priSize);
        if (priLists != null && priLists.size() > 0)
            tasks.addAll(priLists);
        int redoSize = taskConfig.getRedoPassNum();
        List<Task> redoLists= listRedoTask(redoSize);
        if (redoLists != null && redoLists.size() > 0)
            tasks.addAll(redoLists);
        tasks.addAll(listNormalTask(maxSize - priSize - redoSize));
        return tasks;
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
            finishTaskList = new ArrayList<>();
            redoTaskList = new LinkedList();
            priTaskList = new LinkedList();
            taskTuples = new TaskTuple[]{
                    new TaskTuple(Task.JOB_ERROR, errorTaskList),
                    new TaskTuple(Task.JOB_NORMAL, taskList),
                    new TaskTuple(Task.JOB_REDO, redoTaskList),
                    new TaskTuple(Task.JOB_PRI, priTaskList),
                    new TaskTuple(Task.JOB_DOWN, finishTaskList)
            };
        }

        private LinkedList<Task> taskList;

        private ArrayList<Task> errorTaskList;

        private LinkedList<Task> redoTaskList;

        private LinkedList<Task> priTaskList;

        private ArrayList<Task> finishTaskList;


        @Override
        public List<Task> get(Integer tag) {
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


    private class Location{

        public Location(int listTag, int index) {
            this.listTag = listTag;
            this.index = index;
        }

        private int listTag;

        private int index;

        public int getListTag() {
            return listTag;
        }

        public void setListTag(int listTag) {
            this.listTag = listTag;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

}
