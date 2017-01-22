package cc.home.jobber;

import cc.home.jobber.execute.container.BaseTaskContainer;
import cc.home.jobber.execute.helper.TaskHelper;
import cc.home.jobber.execute.process.TaskProcess;
import cc.home.jobber.execute.task.BaseTask;
import cc.home.jobber.execute.task.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
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

    private TaskHelper taskHelper;
/*
    public void setTaskHelper(TaskHelper taskHelper) {
        this.taskHelper = taskHelper;
    }

    public void setTaskContainer(BaseTaskContainer taskContainer) {
        this.taskContainer = taskContainer;
    }*/

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
        this.taskHelper.setContainer(new BaseTaskContainer());
        BaseTask.setTaskHelper(taskHelper);
    }

    public void start() {
        currency = new Semaphore(Math.max(Math.max(1, Runtime.getRuntime().availableProcessors()),
                taskConfig.getCorePoolSize()));
        executorService.scheduleWithFixedDelay(new Executor(), this.taskConfig.getInitDelayTime(),
                this.taskConfig.getJobDelayTime(), TimeUnit.SECONDS);
    }

    private class Executor implements Runnable {

        @Override
        public void run() {
            List<Task> taskList=taskHelper.getUseAbleTask(taskConfig);
            Iterator it = taskList.iterator();
            while (it.hasNext()) {
                Task task;
                try {
                    task = (Task) it.next();
                    taskHelper.remove(task.getTaskNum());
                    if (task.getTimes() > task.MAX_RETRY_TIMES) {
                        task.setStatus(TaskStatus.ABANDON);
                        taskHelper.register(task);
                        continue;
                    } else {
                        if (task.getStatus().compareTo(TaskStatus.PROCESSING) == 0) {
                            continue;
                        } else if (task.getStatus().compareTo(TaskStatus.REDO) == 0) {
                            task.setStatus(TaskStatus.PROCESSING);
                            logger.info("task:" + task.getTaskNum() + "================= redo" + (task.getTimes() + 1 ));
                        } else {
                            task.setStatus(TaskStatus.PROCESSING);
                        }
                    }
                    executorService.schedule(new Runner(task), task.getDelayTime(), TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                } finally {
                }

            }
        }
    }

    private class Runner implements Runnable{

        private Task  task;

        public Runner(Task task) {
            this.task = task;
        }

        @Override
        public void run() {
            try {
                if (currency.tryAcquire(500, TimeUnit.MILLISECONDS)) {
                    logger.info("task begin to start:" + task.getTaskNum());
                    TaskProcess process = task.getProcessHandler();
                    process.process(task);
                    task.setStatus(TaskStatus.DOWN);
                } else {
                    task.setStatus(TaskStatus.REDO);
                    logger.error("currency.tryAcquire due to error");
                }
            } catch (Exception e) {
                task.setStatus(TaskStatus.REDO);
                logger.error("ScheduledExecutorEngine.run due to error", e);
            } finally {
                task.increaseTimes(task);
                taskHelper.register(task);
                currency.release();
            }
        }
    }

}
