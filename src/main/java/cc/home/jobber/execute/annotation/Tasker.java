package cc.home.jobber.execute.annotation;

import java.lang.annotation.*;

/**
 * Created by cheng on 2017/1/13 0013.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Tasker {

    String startTime() default "yyyy-MM-dd HH:mm:ss";

    Tasker.Period period() default @Period();

    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface Period {

        PeriodBase base() default PeriodBase.HOUR;

        int multiple() default  1;

    }
}
