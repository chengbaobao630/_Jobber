package cc.home.jobber.execute.task;

import cc.home.jobber.Task;
import cc.home.jobber.execute.helper.TaskHelper;
import cc.home.jobber.execute.process.TaskProcess;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public abstract class BaseTask implements Task {

    private TaskHelper taskHelper;

    public BaseTask() {
        taskHelper = new TaskHelper();
    }

    public void register(){
        taskHelper.register(this);
    }

    private TaskStatus status;

    private Integer times;

    private Long delayTime;

    private TaskProcess taskProcess;

    private String taskNum = "";

    private int priority;

    private static Integer totalTask = 0;

    public BaseTask(long delayTime) {
        totalTask++;
        this.delayTime = delayTime;
        this.status = TaskStatus.NEW;
        synchronized (totalTask) {
            taskNum=this.getClass().getSimpleName() + ":" + (totalTask - 1 );
            System.out.println(taskNum);
        }
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

    }

    @Override
    public Integer getTimes() {
        return this.times;
    }

    @Override
    public void increaseTimes() {
            this.times ++ ;
    }

    @Override
    public Long getDelayTime() {
        return this.delayTime;
    }

    @Override
    public TaskProcess getProcessHandler() {
        return this.taskProcess;
    }

    @Override
    public String getTaskNum() {
        return this.taskNum;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }


}
