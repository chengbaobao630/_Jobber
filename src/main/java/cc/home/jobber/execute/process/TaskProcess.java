package cc.home.jobber.execute.process;

import cc.home.jobber.Task;

/**
 * Created by cheng on 2016/6/13 0013.
 */
public interface TaskProcess {

    Object process(Task task) throws Exception;
}
