package com.kh.common.scheduler.job;

import java.sql.Date;
import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kh.common.scheduler.model.service.SchedulerService;
import com.kh.common.sms.SMS;
import com.kh.group.model.vo.GroupMatching;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

//매일 낮 12시에 실행(sms주석처리)
public class SendExDate implements Job{

	MemberService memberService = new MemberService();
	SchedulerService schedulerService = new SchedulerService();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		//1. SH_MATCHING에서 EX_DATE가 today + 3 인 데이터 ArrayList<GroupMatching>으로 가져옴
		ArrayList<GroupMatching> threeDaysLeft = schedulerService.getThreeDaysLeft();
		
		//2. SH_MEMBER 테이블 폰 번호를 가져와서 반복문 돌면서 문자 보내줌
		String phoneNum = "";
		String content = "Let's Share 입니다.\n이용하시는 서비스의 이용 만기일이\n3일 남았습니다.";
		for (GroupMatching groupMatching : threeDaysLeft) {
			phoneNum = memberService.selectMemberById(groupMatching.getMemberId()).getMbtel();
			//new SMS().sendSMS(phoneNum, content);
		}
	}
}
