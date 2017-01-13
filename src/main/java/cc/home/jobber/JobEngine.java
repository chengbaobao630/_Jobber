package cc.home.jobber;

import cc.home.jobber.execute.container.TaskContainer;
import cc.home.jobber.execute.helper.TaskHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by cheng on 2017/1/13 0013.
 */
public class JobEngine {

    private static final Logger logger= LoggerFactory.getLogger(JobEngine.class);

    private ScheduledExecutorService executorService;

    private Semaphore currency;

    TaskContainer taskContainer;

    TaskHelper taskHelper;

    private void init(){

    }

    private void init(JobConfig jobConfig){

    }


    private void start(){
        currency = new Semaphore(Math.max(1, Runtime.getRuntime().availableProcessors()));
        executorService.scheduleWithFixedDelay(new Executor(), 30, 30, TimeUnit.SECONDS);
    }

    class Executor implements Runnable{

        @Override
        public void run() {
            return ;
        }
    }

}
