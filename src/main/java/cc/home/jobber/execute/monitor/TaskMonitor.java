package cc.home.jobber.execute.monitor;

import cc.home.jobber.execute.task.Task;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public interface TaskMonitor {

    boolean check(Task task);

}
