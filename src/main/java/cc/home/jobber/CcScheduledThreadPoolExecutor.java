package cc.home.jobber;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * Created by cheng on 2016/6/13 0013.
 */
public class CcScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {

    public CcScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    public CcScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }

    public CcScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
    }

    public CcScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
    }

}
