package cc.home.jobber.execute.monitor;

import cc.home.jobber.Task;
import cc.home.jobber.execute.process.TaskProcess;

/**
 * Created by Administrator on 2017/1/15 0015.
 */
public class ProcessCheck implements CheckStrategy {

    private TaskCheckResult result;

    @Override
    public boolean check(Task task) {
        TaskProcess process=task.getProcessHandler();
        if (process == null){
            result = new TaskCheckResult();
            result.setRes_code(Task.JOB_ERROR);
            result.setRes_msg("no task process");
            return false;
        }
        return true;
    }

    @Override
    public <R> R getResult() {
        return (R) result;
    }
}
