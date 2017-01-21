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
        TaskEngine engine = TaskEngine.build(new TaskConfig(), new CcScheduledThreadPoolExecutor(4));
        engine.start();
    }

    @Test
    public static void addTask() {
        new BaseTask(10) {
        }.register();
        Map map= BaseTaskContainer.toMap();

        System.out.println(map.size());
    }

    public static void main(String[] args) {
        start();
        addTask();
    }

}