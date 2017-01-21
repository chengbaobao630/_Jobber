package cc.home.jobber.execute.task;

import cc.home.jobber.Task;
import cc.home.jobber.execute.helper.TaskHelper;
import cc.home.jobber.execute.process.TaskProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public abstract class BaseTask implements Task {

    private static final Logger logger = LoggerFactory.getLogger(BaseTask.class);

    private static TaskHelper taskHelper;

    private BaseTask() {
        this.taskProcess = task -> {
            System.out.println(task.getTaskNum());
            return null;
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

    private Long delayTime;

    private TaskProcess taskProcess;

    private String taskNum = "";

    private int priority;

    private static Integer totalTask = 0;

    public BaseTask(long delayTime) {
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
        this.times++;
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

    @Override
    public Object process(Object... objects) {
        try {
            return taskProcess.process(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
