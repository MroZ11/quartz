package com.zs.demo.core.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by cloud on 2018/3/22.
 */
@Component
public class QuartzStart implements CommandLineRunner {

    public  static  StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();

    @Override
    public void run(String... strings) throws Exception {

        //创建Quartz的计划任务
        Scheduler sched = stdSchedulerFactory.getScheduler();

        //开启job
        JobDetail job = newJob(MyJob.class).withIdentity("myjob").build();

        //配置计划任务的定时器(触发器的作用应给job触发条件,同一个job可以添加多触发条件，不同的job也可以被相同的触发器触发)
        Trigger trigger = newTrigger().withIdentity("myjob").withSchedule(cronSchedule("*/30 * * * * ?")).build();
        System.out.print(trigger.getKey());

        Date date=sched.scheduleJob(job, trigger);//获得首次将要执行计划任务的时间，待会儿println出来


        sched.start();//开始执行

    }

}
