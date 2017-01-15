package cc.home.jobber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class TaskConfig implements TaskConstant {

    public TaskConfig() {
    }

    private static final Logger logger = LoggerFactory.getLogger(TaskConfig.class);

    private int corePoolSize = 4;

    private int initDelayTime = 10;

    private int jobDelayTime = 60;

    private boolean autoRedo = true;

    private int maxTaskNum = 20;

    private int errorPassNum = 5;

    private int priPassNum = 15;

    private int handlerStrategy = TaskConfig.NORMAL;

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

    public int getJobDelayTime() {
        return jobDelayTime;
    }

    public void setJobDelayTime(int jobDelayTime) {
        this.jobDelayTime = jobDelayTime;
    }

    public static void main(String[] args) {
        TaskEngine engine = TaskEngine.build(new TaskConfig(), new CcScheduledThreadPoolExecutor(4));
        engine.start();
    }

}
