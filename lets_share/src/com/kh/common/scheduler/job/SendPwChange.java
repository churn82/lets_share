package com.kh.common.scheduler.job;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.kh.common.scheduler.model.service.SchedulerService;
import com.kh.common.sms.SMS;
import com.kh.group.model.service.GroupService;
import com.kh.group.model.vo.GroupMatching;
import com.kh.member.model.service.MemberService;

//매일 밤 12시 0분 30초 에 실행(sms주석처리)
public class SendPwChange implements Job{

	MemberService memberService = new MemberService();
	SchedulerService schedulerService = new SchedulerService();
	GroupService groupService = new GroupService();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		//1. SH_MATCHING에서 EX_DATE가 TODAY-1 인 GROUP_ID를 중복 제거해서 가지고옴
		ArrayList<Integer> expirationGroupIds = schedulerService.getExpirationGroupIds();
		
		//1-1. 가져온 GROPU_ID의 GROUP장 아이디를 가져와 문자보내준다
		String phoneNum = "";
		String content = "Let's Share 입니다.\n이용 만기일이 지난 그룹원이 있습니다.\nPW를 변경하고 등록 해주세요";
		for (Integer groupId : expirationGroupIds) {
			phoneNum = memberService.selectMemberById(groupService.getGroup(groupId).getMemberId()).getMbtel();
			//new SMS().sendSMS(phoneNum, content);
			System.out.println(groupService.getGroup(groupId).getMemberId()+"의 번호 "+phoneNum+"로 문자 보내기 성공");
		}
		
		//2. SH_MATCHING에서 EX_DATE가 today-1 인 데이터 ArrayList<GroupMatching>으로 가져옴
		ArrayList<GroupMatching> Expiration = schedulerService.getExpiration();
		
		//2-1. 반복문 돌면서 ST_DATE, EX_DATE를 null로 바꿔준다
		for (GroupMatching groupMatching : Expiration) {
			schedulerService.updateStExDateNull(groupMatching.getGroupId(), groupMatching.getMemberId());
		}
	}
}
