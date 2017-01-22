package cc.home.jobber;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public interface TaskConstant {

    int MAX_RETRY_TIMES = 5;

    int JOB_ERROR = -1;

    int JOB_NORMAL = 1;

    int JOB_REDO = 3;

    int JOB_PRI = 9;

    int JOB_DOWN = -9;


    //错误优先
    int ERROR_FIRST = -1;

    //优先级优先
    int PRIORITY_FIRST = 9;

    //顺序
    int NORMAL = 1;

    //错误+优先级
    int ERROR_PRIORITY = 8;


}
