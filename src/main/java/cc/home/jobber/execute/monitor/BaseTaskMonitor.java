package cc.home.jobber.execute.monitor;

import cc.home.jobber.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/14 0014.
 */
public class BaseTaskMonitor implements TaskMonitor {

    private List<CheckStrategy> strategies;

    private TaskCheckResult checkResult;

    public BaseTaskMonitor() {
        strategies = new ArrayList<>();
        strategies.add(new ProcessCheck());
    }

    private static final TaskCheckResult DEFAULT_SUCCESS=getDefaultSuccess();

    @Override
    public TaskCheckResult check(Task task) {
        for (CheckStrategy strategy : strategies){
            if ( !strategy.check(task)){
                checkResult = strategy.getResult();
                return checkResult;
            }
        }
        return DEFAULT_SUCCESS;
    }

    {
        strategies = new ArrayList<>();
    }

    public static TaskCheckResult getDefaultSuccess() {
        TaskCheckResult defaultSuccess=new TaskCheckResult();
        defaultSuccess.setRes_code(200);
        defaultSuccess.setRes_msg("success");
        return defaultSuccess;
    }
}
