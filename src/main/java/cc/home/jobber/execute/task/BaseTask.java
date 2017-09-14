package cc.home.jobber.execute.task;

import cc.home.jobber.Task;
import cc.home.jobber.TaskHelper;
import cc.home.jobber.execute.process.TaskProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public  class BaseTask implements Task,Serializable {

    private transient static final Logger logger = LoggerFactory.getLogger(BaseTask.class);

    private transient static TaskHelper taskHelper;

    private BaseTask() {
        this.taskProcess = new TaskProcess() {
            @Override
            public Object process(Task task) throws Exception {
                System.out.println(task.getTaskNum());
                return null;
            }

            @Override
            public synchronized void shutdown() throws InterruptedException {
                    Thread thread=Thread.currentThread();
                    thread.wait();
            }
        };
    }

    public static void setTaskHelper(TaskHelper taskHelper) {
        BaseTask.taskHelper = taskHelper;
    }

    public void register() {
        taskHelper.register(this);
    }

    private TaskStatus status;

    private Integer times;

    private Integer totalTimes;

    private Integer successTimes;

    @Override
    public Integer getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(Integer totalTimes) {
        this.totalTimes = totalTimes;
    }

    @Override
    public Integer getSuccessTimes() {
        return successTimes;
    }

    @Override
    public void shutdown() throws InterruptedException {
        this.shutdown = true ;
        this.taskProcess.shutdown();
    }

    private boolean shutdown;


    @Override
    public boolean isShutdown() {
        return shutdown;
    }

    public void setSuccessTimes(Integer successTimes) {
        this.successTimes = successTimes;
    }

    private Long delayTime;

    private transient TaskProcess taskProcess;

    private String taskNum = "";

    private int priority;

    private static Integer totalTask = 0;

    public BaseTask(long delayTime,int totalTimes) {
        this();
        totalTask++;
        this.delayTime = delayTime;
        this.status = TaskStatus.NEW;
        synchronized (totalTask) {
            String taskNumPrefix = this.getClass().getSimpleName();
            if ("".equals(taskNumPrefix) || taskNumPrefix == null || taskNumPrefix.length() <= 0) {
                taskNumPrefix = "anonymousTask";
            }
            taskNum = taskNumPrefix + ":" + (totalTask - 1);
            if (logger.isDebugEnabled()) {
                logger.debug("task " + taskNum + " created ");
            }
        }
        this.totalTimes =totalTimes;
        this.times = 0;
        this.successTimes = 0;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public void setDelayTime(Long delayTime) {
        this.delayTime = delayTime;
    }

    public TaskProcess getTaskProcess() {
        return taskProcess;
    }

    public void setTaskProcess(TaskProcess taskProcess) {
        this.taskProcess = taskProcess;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public TaskStatus getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public Integer getTimes() {
        return this.times;
    }

    @Override
    public void increaseTimes(Task task) {
       switch (task.getStatus()){
           case REDO: times ++; break;
           case DOWN: times = 0; successTimes ++ ; break;
           default: break;
       }
    }

    @Override
    public Long getDelayTime() {
        return this.delayTime;
    }



    @Override
    public String getTaskNum() {
        return this.taskNum;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public Object process(Object... objects) throws Exception {
        try {
            return taskProcess.process(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
