package cc.home.jobber.execute.listener;

import cc.home.jobber.execute.process.TaskProcess;
import cc.home.jobber.Task;

/**
 * Created by Administrator on 2017/1/14 0014.
 */
public class DefaultTaskListener  implements  TaskListener{

    @Override
    public void onTaskAdd(Task task) {

    }

    @Override
    public void onProcessError(Task task, TaskProcess process) {

    }

    public static void main(String[] args) {
        System.out.println(Task.ERROR_FIRST);
    }
}
