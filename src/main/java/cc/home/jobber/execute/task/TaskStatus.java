package cc.home.jobber.execute.task;

import cc.home.jobber.TaskConstant;

/**
 * Created by thinkpad on 2016/6/14.
 */
public enum TaskStatus implements TaskConstant {
    NEW(JOB_NORMAL),
    PROCESSING(JOB_PROCESSING),
    ABANDON(JOB_ERROR),
    DOWN(JOB_DOWN),
    SHUTDOWN(JOB_SHUTDOWN),
    REDO(JOB_REDO);

    TaskStatus() {
    }

    TaskStatus(int code) {
        this.code = code;
    }

    int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static void main(String[] args) {
        System.out.println( PROCESSING);
    }

}
