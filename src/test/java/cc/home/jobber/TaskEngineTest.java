package cc.home.jobber;

import cc.home.jobber.execute.container.BaseTaskContainer;
import cc.home.jobber.execute.task.BaseTask;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/15 0015.
 */
public class TaskEngineTest {

    @Before
    public static void start() {
        TaskConfig taskConfig=new TaskConfig();
        taskConfig.setJobDelayTime(30);
        TaskEngine engine = TaskEngine.build(taskConfig, new CcScheduledThreadPoolExecutor(4));
        engine.start();
    }

    @Test
    public static void addTask() {
        new BaseTask(10,2) {
        }.register();

//        Map map= BaseTaskContainer.toMap();

//        System.out.println(map.size());
    }

    public static void main(String[] args) {
        start();
        addTask();
        addTask();

        BaseTask task=new BaseTask(10,5) {
        };
        task.setProcessHandler(taskProcess -> {
            System.out.println(task.getTaskNum());
            return null;
        });
        task.register();
    }

}