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
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	MemberDao memberDao = new MemberDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public Member memberAuthenticate(String mbId, String mbpassword){	
	    Connection conn = jdt.getConnection();
	      Member member = null;
	      try {
	         member = memberDao.memberAuthenticate(conn, mbId, mbpassword);
	      } finally {
	         jdt.close(conn);
	      }
	      
	      return member;
	   }
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
	public Member selectMemberById(String mbId){	
		Connection conn = jdt.getConnection();
	      Member member = null;
	      try {
	         member = memberDao.selectMemberById(conn, mbId);
	      } finally {
	         jdt.close(conn);
	      }     
	      return member;
	   }
	public Member selectMemberBynick(String mbnick){	
		Connection conn = jdt.getConnection();
	      Member member = null;
	      try {
	         member = memberDao.selectMemberBynick(conn, mbnick);
	      } finally {
	         jdt.close(conn);
	      }     
	      return member;
	   }
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
	
	 public void Emailsend(Member member) {
		  //POST방식으로 통신해보기
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

	
	public int insertMember(Member member){
		//Transaction관리를 Service단에서 처리하기 위해 Connection을 
		//Service의 메서드에서 생성
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
}

