package cc.home.jobber;

import cc.home.jobber.execute.process.TaskProcess;
import cc.home.jobber.execute.task.TaskStatus;

/**
 * Created by cheng on 2016/6/13 0013.
 */
public interface Task extends TaskConstant {

    TaskStatus getStatus();

    void setStatus(TaskStatus status);

    Integer getTimes();

    void increaseTimes();

    Long getDelayTime();

    TaskProcess getProcessHandler();

    String toString();

    String getTaskNum();

    int getPriority();

}
