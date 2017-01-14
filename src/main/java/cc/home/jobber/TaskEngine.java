package cc.home.jobber;

import cc.home.jobber.execute.container.BaseTaskContainer;
import cc.home.jobber.execute.container.TaskContainer;
import cc.home.jobber.execute.helper.TaskHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private TaskConfig taskConfig;


    public TaskEngine(TaskConfig taskConfig, ScheduledExecutorService executorService) {
        this.taskConfig = taskConfig;
        this.executorService = executorService;
        init();
    }


    private void init() {
        this.taskHelper = new TaskHelper();
        this.taskContainer = new BaseTaskContainer();
        this.taskHelper.setContainer(this.taskContainer);
        start();

    }

    private void start() {
        currency = new Semaphore(Math.max(1, Runtime.getRuntime().availableProcessors()));
        executorService.scheduleWithFixedDelay(new Executor(), this.taskConfig.getInitDelayTime(),
                this.taskConfig.getJobDelayTime(), TimeUnit.SECONDS);
    }

    class Executor implements Runnable {

        @Override
        public void run() {
        }
    }

}
