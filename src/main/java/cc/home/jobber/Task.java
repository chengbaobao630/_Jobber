package cc.home.jobber;

import cc.home.jobber.execute.process.TaskProcess;
import cc.home.jobber.execute.task.TaskStatus;

import java.util.Objects;

/**
 * Created by cheng on 2016/6/13 0013.
 */
public interface Task extends TaskConstant {

    TaskStatus getStatus();

    void setStatus(TaskStatus status);

    Integer getTimes();

    void increaseTimes(Task task);

    Long getDelayTime();

    TaskProcess getTaskProcess();

    void setTaskProcess(TaskProcess processHandler);

    String toString();

    String getTaskNum();

    int getPriority();

    Object process(Object... objects) throws Exception;

    Integer getTotalTimes();

    Integer getSuccessTimes();

    void shutdown() throws InterruptedException;

    boolean isShutdown();
}
