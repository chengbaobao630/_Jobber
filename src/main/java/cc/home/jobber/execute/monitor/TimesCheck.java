package cc.home.jobber.execute.monitor;

import cc.home.jobber.Task;
import cc.home.jobber.execute.task.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cheng on 2017/1/22 0022.
 */
public class TimesCheck implements CheckStrategy {

    private static final Logger logger = LoggerFactory.getLogger(TimesCheck.class);

    private TaskCheckResult result;


    @Override
    public boolean check(Task task) {
        logger.info("check :" + task.getTaskNum() +", status " + task.getStatus());
        int times = task.getTimes();

        logger.info(task.getTaskNum() +",times :"+times);
        int totalTimes = task.getTotalTimes();
        logger.info(task.getTaskNum() +",totalTimes :"+totalTimes);
        int successTimes = task.getSuccessTimes();
        logger.info(task.getTaskNum() +",successTimes :"+successTimes);

        if (times == Task.MAX_RETRY_TIMES) {
            task.setStatus(TaskStatus.ABANDON);
            return false;
        }

        if (successTimes < totalTimes && TaskStatus.DOWN.equals(task.getStatus())) {
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
