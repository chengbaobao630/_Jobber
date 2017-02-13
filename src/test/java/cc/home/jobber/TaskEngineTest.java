package cc.home.jobber;

import cc.home.jobber.execute.task.BaseTask;
import org.junit.Before;
import org.junit.Test;

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


}