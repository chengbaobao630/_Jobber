package cc.home.jobber.execute.monitor;

/**
 * Created by Administrator on 2017/1/14 0014.
 */
public class TaskCheckResult extends CheckResult<Integer, String> {

    public TaskCheckResult(Integer res_code, String res_msg) {
        super(res_code, res_msg);
    }

    public TaskCheckResult() {
        super();
    }
}
