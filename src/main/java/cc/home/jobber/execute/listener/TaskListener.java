package cc.home.jobber.execute.listener;

import cc.home.jobber.execute.process.TaskProcess;
import cc.home.jobber.Task;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public interface TaskListener {

    void onTaskAdd(Task task);

    void onProcessError(Task task,TaskProcess process);


}
