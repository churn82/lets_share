package com.kh.notice.model.service;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.template.JDBCTemplate;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	NoticeDao noticeDao = new NoticeDao();
	
	//공지게시글 등록
	public int insertNoticeBoard(Notice notice) {
		int rs = 0;
		Connection conn = jdt.getConnection();
		
		try {
			rs = noticeDao.insertNoticeBoard(conn, notice);
			jdt.commit(conn);
			
		}catch(DataAccessException e){
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}		
		return rs;
	}
	
	//게시글 수정전
	public Notice beforeUpdate(int noticeNo) {

		Connection conn = jdt.getConnection();
		Notice notice = null;
		
		try {
			notice = noticeDao.beforeUpdate(conn,noticeNo);
			jdt.commit(conn);
		}catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}
		finally {
			jdt.close(conn);
		}
		
		return notice;
	}
	
	//게시글 수정요청
	public int updateRequest(Notice notice) {
		int res = 0;
		Connection conn = jdt.getConnection();
		
		try {
			res = noticeDao.updateRequest(conn, notice);
			jdt.commit(conn);
			
		}catch(DataAccessException e){
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}		
		return res;
	}
	
	/*
	public int deleteNoticeBoard(int noticeNo) {
		Connection conn = jdt.getConnection();
		int rs = 0;
		
		try {
			rs = noticeDao.deleteNoticeBoard(conn, noticeNo);
			jdt.commit(conn);
		}catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		
		return rs;
	}
	*/
	//상세페이지
	public Notice selectNoticeDetail(int noticeNo){
		Connection conn = jdt.getConnection();
		Notice notice = null;
		
		try {
			notice = noticeDao.selectNoticeDetail(conn, noticeNo);
		}finally {
			jdt.close(conn);
		}
		
		return notice;
	}
	
	//공지사항 목록
	public ArrayList<Notice> selectNoticeList(){
		
		Connection conn = jdt.getConnection();
		ArrayList<Notice> noticeList = null;
		
		try {
			noticeList = noticeDao.selectNoticeList(conn);
			jdt.commit(conn);
		}catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}
		finally {
			jdt.close(conn);
		}
		
		return noticeList;
	}
	
	
	
	//이벤트 목록
	public ArrayList<Notice> selectEventList(){
		
		Connection conn = jdt.getConnection();
		ArrayList<Notice> noticeList = null;
		
		try {
			noticeList = noticeDao.selectEventList(conn);
			jdt.commit(conn);
		}catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}
		finally {
			jdt.close(conn);
		}
		
		return noticeList;
	}
	
	
	
	/*
	//SH_GRADE 테이블에서 MB_LEVEL를 가져오는 함수
	public String getLevel(String mbId) {
		String level = null;
		Connection conn = jdt.getConnection();
		try {
			
		}catch(DataAccessException e){
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return level;
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
}
