package cc.home.jobber;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class TaskConfig implements TaskConstant {

    public TaskConfig() {
    }

//    private static final Logger logger = LoggerFactory.getLogger(TaskConfig.class);

    private int corePoolSize = 4;

    private int initDelayTime = 10;

    private int jobDelayTime = 60;

    private boolean autoRedo = true;

    private int maxTaskNum = 20;


    private int handlerStrategy = TaskConfig.NORMAL;

    public int getRedoPassNum() {
        if (this.handlerStrategy == NORMAL)  return (int) (maxTaskNum * 0.25);
        if (this.handlerStrategy == PRIORITY_FIRST)  return (int) (maxTaskNum * 0.25);
        if (this.handlerStrategy == ERROR_PRIORITY)  return (int) (maxTaskNum * 0.3);
        if (this.handlerStrategy == ERROR_FIRST)  return (int) (maxTaskNum * 0.5);
        return 0;
    }


    public int getPriPassNum() {
        if (this.handlerStrategy == NORMAL)  return (int) (maxTaskNum * 0.25);
        if (this.handlerStrategy == ERROR_FIRST)  return (int) (maxTaskNum * 0.25);
        if (this.handlerStrategy == PRIORITY_FIRST)  return (int) (maxTaskNum * 0.5);
        if (this.handlerStrategy == ERROR_PRIORITY)  return (int) (maxTaskNum * 0.3);
        return 0;
    }

    public void setHandlerStrategy(int handlerStrategy) {
        this.handlerStrategy = handlerStrategy;
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
}
