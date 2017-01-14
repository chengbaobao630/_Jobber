package cc.home.jobber.execute.monitor;

import cc.home.jobber.Task;

/**
 * Created by Administrator on 2017/1/14 0014.
 */
public interface CheckStrategy {

    boolean check(Task task);

    <R> R getResult();

}
