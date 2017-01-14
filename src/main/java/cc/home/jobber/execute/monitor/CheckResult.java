package cc.home.jobber.execute.monitor;

/**
 * Created by Administrator on 2017/1/14 0014.
 */
public class CheckResult<C,M> {

    private C res_code;

    private M res_msg;

    public C getRes_code() {
        return res_code;
    }

    public void setRes_code(C res_code) {
        this.res_code = res_code;
    }

    public M getRes_msg() {
        return res_msg;
    }

    public void setRes_msg(M res_msg) {
        this.res_msg = res_msg;
    }

}
