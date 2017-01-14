package cc.home.jobber.execute.handler;

import cc.home.jobber.Task;

import java.util.List;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public interface HandlerStrategy {

    void handler(List<Task> taskList);

}
