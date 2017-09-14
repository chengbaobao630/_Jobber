package cc.home.jobber;

import cc.home.jobber.execute.process.TaskProcess;
import cc.home.jobber.execute.task.BaseTask;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/1/15 0015.
 */
public class TaskEngineTest {

    @Before
    public void start() {
        TaskConfig taskConfig=new TaskConfig();
        taskConfig.setJobDelayTime(30);
        TaskEngine engine = TaskEngine.build(taskConfig, new CcScheduledThreadPoolExecutor(4));
        engine.start();
    }

    @Test
    public  void addTask() {
        BaseTask task = new BaseTask(10,Integer.MAX_VALUE) {
        };
        task.setTaskProcess(new TaskProcess() {
            @Override
            public Object process(Task task) throws Exception {
                Thread.sleep(15000);
                System.out.println(task.getTaskNum());
                return null;
            }

            @Override
            public void shutdown() throws InterruptedException {

            }
        });
        task.register();
        while (true){

        }

//        Map map= BaseTaskContainer.toMap();

//        System.out.println(map.size());
    }


}