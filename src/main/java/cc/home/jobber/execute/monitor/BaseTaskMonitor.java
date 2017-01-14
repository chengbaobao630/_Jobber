package cc.home.jobber.execute.monitor;

import cc.home.jobber.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/14 0014.
 */
public class BaseTaskMonitor implements TaskMonitor {

    private List<CheckStrategy> strategies;

    private CheckResult<Integer ,String> checkResult;

    private static final CheckResult<Integer ,String> DEFAULT_SUCCESS=getDefaultSuccess();

    @Override
    public CheckResult check(Task task) {
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

    public static CheckResult<Integer,String> getDefaultSuccess() {
        CheckResult<Integer,String> defaultSuccess=new CheckResult<>();
        defaultSuccess.setRes_code(200);
        defaultSuccess.setRes_msg("success");
        return defaultSuccess;
    }
}
