package cc.home.jobber.execute.monitor;

import cc.home.jobber.Task;
import cc.home.jobber.execute.process.TaskProcess;
import cc.home.jobber.execute.task.TaskStatus;

/**
 * Created by Administrator on 2017/1/15 0015.
 */
public class StatusCheck implements CheckStrategy {

    private TaskCheckResult result;

    @Override
    public boolean check(Task task) {
        TaskStatus status = task.getStatus();
        if (status == null){
               result = new TaskCheckResult();
               result.setRes_code(Task.JOB_ERROR);
               result.setRes_msg("no status task");
            return false;
        }
        result = new TaskCheckResult();
        result.setRes_code(status.getCode());
        result.setRes_msg("");
        return true;
    }

    @Override
    public <R> R getResult() {
        return (R) result;
    }
}