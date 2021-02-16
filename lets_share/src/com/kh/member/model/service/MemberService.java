package com.kh.member.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.common.code.Code;
import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.mail.MailSender;
import com.kh.common.template.JDBCTemplate;
import com.kh.common.util.Util;
import com.kh.group.model.vo.Group;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	MemberDao memberDao = new MemberDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	//로그인
	public Member memberAuthenticate(String mbId, String mbpassword){	
		Member member = null;
		Connection conn = jdt.getConnection();	
		try {
			member = memberDao.memberAuthenticate(conn, mbId, mbpassword);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return member;
	}

	//회원등급 가져오기
	public Member selectMemberBylevel(String mbId){	
		Connection conn = jdt.getConnection();
	      Member member = null;
	      try {
	         member = memberDao.selectMemberById(conn, mbId);
	      } finally {
	         jdt.close(conn);
	      }     
	      return member;
	   }
	
	//멤버정보 가져오기(ID)
	public Member selectMemberById(String mbId){	
		Member member = null;
		Connection conn = jdt.getConnection();	
		try {
			member = memberDao.selectMemberById(conn, mbId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return member;
	}

	
	
	//닉네임 체크
	public Member selectMemberBynick(String mbnick){
		Member member = null;
		Connection conn = jdt.getConnection();	
		try {
			member = memberDao.selectMemberBynick(conn, mbnick);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return member;
	}
	
	//가입일 조회	
	public List<Member> selectMemberByRegdate(Date begin, Date end){
		  Connection conn = jdt.getConnection();
	      List<Member> memberList = null;
	      
	      try {
	         memberList = memberDao.selectMemberByRegdate(conn, begin, end);
	      }finally {
	         jdt.close(conn);
	      }
	      return memberList;
	   }
	//이메일 인증 
	 public void Emailsend(Member member) {
		 
	      String subject = "회원가입을 완료해주세요!";
	      String htmlText = "";
	      
	      Util http = new Util();
	      String url = Code.DOMAIN+"/mail";
	     
	      //header 저장
	      Map<String,String> headers = new HashMap<>();
	      headers.put("content-type", "application/x-www-form-urlencoded; charset=utf-8");
	      
	      //parameter 저장
	      Map<String,String> params = new HashMap<>();
	      params.put("mailTemplate", "temp_join");
	      params.put("mbId", member.getMbId());
	      
	      htmlText = http.post(url, headers, http.urlEncodedForm(params));
	      new MailSender().sendEmail(member.getMbemail(),subject,htmlText); 
	  
	   }

	
	// 테이블에 멤버정보 INSERT하는 메서드(정상)
	public int insertMember(Member member){
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			 res = memberDao.insertMember(conn, member);
			 jdt.commit(conn);
		}catch(DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return res;	
	}
	// 테이블에 멤버정보 UPDATE
	public int updateMember(Member member){
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			res = memberDao.updateMember(conn, member);
			jdt.commit(conn);
		}catch(DataAccessException e) {
			jdt.rollback(conn);
			e.printStackTrace();
			
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
	//테이블에 멤버정보 삭제
	public int deleteMember(String mbId){
		Connection conn = jdt.getConnection();
		
		int res = 0;
		try {
			res = memberDao.deleteMember(conn, mbId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	
	//회원등급에 맞는 코드명 가져오기
	public String getGradeName(String memberRank) {
		Connection conn = jdt.getConnection();
		String gradeName = "";
		try {
			gradeName = memberDao.getGradeName(conn, memberRank);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return gradeName;
	}
	
	//비밀번호, 닉네임 수정
	public int updatePwNick(String memberId, String updatePw, String updateNickName) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = memberDao.updatePwNick(conn, memberId, updatePw, updateNickName);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	//회원 탈퇴
	public int updateLeaveDate(String memberId) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = memberDao.updateLeaveDate(conn, memberId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return res;
	}

	public ArrayList<Member> memberList() {
		return null;
		//return memberDao.selectMemberList();
	}
	
	//멤버 개수 가져오는 함수
	public int getMemberCnt() {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = memberDao.getMemberCnt(conn);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	//검색한 멤버 개수 가져오는 함수
	public int getMemberCnt(String id) { 
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = memberDao.getMemberCnt(conn, id);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	//페이징해서 memberList를 가져온다 
	public ArrayList<Member> getMemberList(int start, int end){
		Connection conn = jdt.getConnection();
		ArrayList<Member> memberList = null;
		try {
			memberList = memberDao.getMemberList(conn, start, end);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return memberList;
	}
	
	//[검색]한 애들을 페이징해서 memberList를 가져온다 
	public ArrayList<Member> getMemberList(String id, int start, int end){
		Connection conn = jdt.getConnection();
		ArrayList<Member> memberList = null;
		try {
			memberList = memberDao.getMemberList(conn, id, start, end); 
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return memberList;
	}
	
	//관리자 회원 탈퇴 시키기
	public int adminUpdate(String memberId) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = memberDao.adminUpdate(conn, memberId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
	
	public ArrayList<Member> getLeavelist(){
		Connection conn = jdt.getConnection();
		ArrayList<Member> memberList = null;
		try {
			memberList = memberDao.getLeavelist(conn);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return memberList;
	}
}

