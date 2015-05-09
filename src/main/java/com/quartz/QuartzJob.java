package com.quartz;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzJob implements Job{ 
	public static void main(String[] args) throws SchedulerException {  
		QuartzJob simple=new QuartzJob();
		simple.Cron();
	}


	public void Cron(){  

		try    {  
			SchedulerFactory schedFact  =   new  org.quartz.impl.StdSchedulerFactory();  
			Scheduler sched  =  schedFact.getScheduler();  
			sched.start();  
			JobDetail jobDetail  =   new  JobDetail( " Income Report " ,  
					" Report Generation " , QuartzJob.class );  
			jobDetail.getJobDataMap().put( " type " ,  " FULL " );  
			CronTrigger trigger  =   new  CronTrigger( " Income Report " ,  " Report Generation " );  
			/**/ /*  每1分钟执行一次  */   
			trigger.setCronExpression( "0/1 * * * * ? " );  
			sched.scheduleJob(jobDetail, trigger);  
		}   catch  (Exception e)   {  
			e.printStackTrace();  
		}   
	}  
	public void Simple() throws SchedulerException
	{
		SchedulerFactory	schedulerFactory=new StdSchedulerFactory();
		Scheduler	scheduler=schedulerFactory.getScheduler();
		long	ctime=System.currentTimeMillis();
		

		
		JobDetail jobDetail = new JobDetail("jobDetail-s1", "jobDetailGroup-s1", QuartzJob.class);

		SimpleTrigger simpleTrigger = 	new SimpleTrigger("simpleTrigger", "triggerGroup-s1");

		simpleTrigger.setStartTime(new Date(ctime));

		simpleTrigger.setRepeatInterval(10000);

		simpleTrigger.setRepeatCount(100);

		scheduler.scheduleJob(jobDetail, simpleTrigger);

		scheduler.start();
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("In SimpleQuartzJob - executing its JOB at " 
				+ new Date() + " by " + context.getTrigger().getName());

	}
}