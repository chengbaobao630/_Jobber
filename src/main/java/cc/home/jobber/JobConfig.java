package cc.home.jobber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class JobConfig {

    private static final Logger logger = LoggerFactory.getLogger(JobConfig.class);

    //错误优先
    public static final int ERROR_FIRST = -1;

    //优先级优先
    public static final int PRIORITY_FIRST = 9;

    //顺序
    public static final int NORMAL = 1;

    //错误+优先级
    public static final int ERROR_PRIORITY = 2;


    private int corePoolSize = 4;

    private int initDelayTime = 10;

    private boolean autoRedo = true;

    private int maxTaskNum = 50;

    private int errorPassNum = 20;

    private int priPassNum = 30;

    private int handlerStrategy = JobConfig.NORMAL;

    public int getErrorPassNum() {
        return errorPassNum;
    }

    public void setErrorPassNum(int errorPassNum) {
        this.errorPassNum = errorPassNum;
    }

    public int getPriPassNum() {
        return priPassNum;
    }

    public void setPriPassNum(int priPassNum) {
        this.priPassNum = priPassNum;
    }

    public int getHandlerStrategy() {
        return handlerStrategy;
    }

    public void setHandlerStrategy(int handlerStrategy) {
        this.handlerStrategy = handlerStrategy;
    }

    public JobConfig() {
    }

    public static Logger getLogger() {
        return logger;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getInitDelayTime() {
        return initDelayTime;
    }

    public void setInitDelayTime(int initDelayTime) {
        this.initDelayTime = initDelayTime;
    }

    public boolean isAutoRedo() {
        return autoRedo;
    }

    public void setAutoRedo(boolean autoRedo) {
        this.autoRedo = autoRedo;
    }

    public int getMaxTaskNum() {
        return maxTaskNum;
    }

    public void setMaxTaskNum(int maxTaskNum) {
        this.maxTaskNum = maxTaskNum;
    }
}
