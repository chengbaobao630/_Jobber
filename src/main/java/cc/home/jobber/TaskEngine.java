package cc.home.jobber;

import cc.home.jobber.execute.container.BaseTaskContainer;
import cc.home.jobber.execute.container.TaskContainer;
import cc.home.jobber.execute.helper.TaskHelper;
import cc.home.jobber.execute.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class TaskEngine {

    private static final Logger logger = LoggerFactory.getLogger(TaskEngine.class);

    private ScheduledExecutorService executorService;

    private Semaphore currency;

    private BaseTaskContainer taskContainer;

    private TaskHelper taskHelper;

    public void setTaskHelper(TaskHelper taskHelper) {
        this.taskHelper = taskHelper;
    }

    public void setTaskContainer(BaseTaskContainer taskContainer) {
        this.taskContainer = taskContainer;
    }

    private TaskConfig taskConfig;

    private static TaskEngine taskEngine;

    private static TaskEngine build(){
            return taskEngine;
    }

    public static TaskEngine build(TaskConfig taskConfig, ScheduledExecutorService executorService){
        if (taskEngine == null) {
            taskEngine = new TaskEngine(taskConfig, executorService);
        }
        return build();
    }




    private TaskEngine(TaskConfig taskConfig, ScheduledExecutorService executorService) {
        this.taskConfig = taskConfig;
        this.executorService = executorService;
        init();
    }


    private void init() {
        this.taskHelper = new TaskHelper();
        this.taskContainer = new BaseTaskContainer();
        this.taskHelper.setContainer(this.taskContainer);
        BaseTask.setTaskHelper(taskHelper);
    }

    public void start() {
        currency = new Semaphore(Math.max(1, Runtime.getRuntime().availableProcessors()));
        executorService.scheduleWithFixedDelay(new Executor(), this.taskConfig.getInitDelayTime(),
                this.taskConfig.getJobDelayTime(), TimeUnit.SECONDS);
    }

    private class Executor implements Runnable {

        @Override
        public void run() {
        }
    }

}
