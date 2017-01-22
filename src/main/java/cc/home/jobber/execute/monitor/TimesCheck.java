package cc.home.jobber.execute.monitor;

import cc.home.jobber.Task;
import cc.home.jobber.execute.task.TaskStatus;

/**
 * Created by cheng on 2017/1/22 0022.
 */
public class TimesCheck implements CheckStrategy {

    private TaskCheckResult result;


    @Override
    public boolean check(Task task) {
        int times = task.getTimes();
        int totalTimes = task.getTotalTimes();
        int successTimes = task.getSuccessTimes();

        if (times == Task.MAX_RETRY_TIMES) {
            task.setStatus(TaskStatus.ABANDON);
            return false;
        }

        if (successTimes <= totalTimes && TaskStatus.DOWN.equals(task.getStatus())) {
            task.setStatus(TaskStatus.NEW);
        }else if (TaskStatus.DOWN.equals(task.getStatus())){
            result = new TaskCheckResult();
            result.setRes_code(task.getStatus().getCode());
            result.setRes_msg(" task finish ");
            return false;
        }
        return true;
    }

    @Override
    public <R> R getResult() {
        return (R) result;
    }
}
