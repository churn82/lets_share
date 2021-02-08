package com.kh.common.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.kh.common.scheduler.job.SendExDate;
import com.kh.common.scheduler.job.SendPwChange;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

public class MainScheduler {

	public static void main(String[] args) throws SchedulerException{
		
		//1. Job 객체 -> 내가 어떤 시각에 동작할 '동작' 을  org.quartz.Job을 상속받은 클래스로 구현
		JobDetail sendExDate = JobBuilder.newJob(SendExDate.class).build(); //내가 지정한 Job 가져옴
		JobDetail sendPwChange = JobBuilder.newJob(SendPwChange.class).build(); //내가 지정한 Job 가져옴
		
		//2. Trigger 객체 -> 내가 어느 '시점'에 동작할지 지정하는 객체
		Trigger trigger = newTrigger() //새로운 트리거를 만듬
			    .withIdentity("trigger1", "group1") //지정된 이름 및 그룹의 트리거 키를 사용하여 트리거를 식별합니다. 라는데 뭔소리?
			    .withSchedule(cronSchedule("0 0 12 * * ?")) //cron 표현식 매일 낮 12시에 실행
			    .forJob(sendExDate) 
			    .build();
		
		Trigger trigger2 = newTrigger() //새로운 트리거를 만듬
			    .withIdentity("trigger1", "group1") //지정된 이름 및 그룹의 트리거 키를 사용하여 트리거를 식별합니다. 라는데 뭔소리?
			    .withSchedule(cronSchedule("30 0 0 * * ?")) //cron 표현식 매일 밤 12시 30초에 실행
			    .forJob(sendPwChange) 
			    .build();
		
		//3. 스케줄러 객체 -> 지정한 '동작' 과 '시점'의 데이터로 실행하는 객체
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		//scheduler.start();
		//scheduler.scheduleJob(sendExDate, trigger);
		
		Scheduler scheduler2 = StdSchedulerFactory.getDefaultScheduler();
		//scheduler2.start();
		//scheduler2.scheduleJob(sendPwChange, trigger2);
		
	}
}
