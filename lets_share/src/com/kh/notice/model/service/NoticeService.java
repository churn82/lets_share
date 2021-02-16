package com.kh.notice.model.service;


import java.sql.Connection;
import java.util.ArrayList;
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
	
	//이벤트 게시글 등록
	public int insertEventBoard(Notice notice) {
		int rs = 0;
		Connection conn = jdt.getConnection();
		
		try {
			rs = noticeDao.insertEventBoard(conn, notice);
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
	//게시글 수정전[이벤트용]
	public Notice beforeUpdateEvent(int noticeNo) {
		Connection conn = jdt.getConnection();
		Notice notice = null;
		try {
			notice = noticeDao.beforeUpdateEvent(conn, noticeNo);
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
	
	//공지 삭제
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
	
	//이벤트 삭제 기능
	public int deleteEventBoard(int noticeNo) {
		Connection conn = jdt.getConnection();
		int rs = 0;
		try {
			rs = noticeDao.deleteEventBoard(conn, noticeNo);
			jdt.commit(conn);
		}catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return rs;
	}
	
	//공지 상세페이지
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
	
	//이벤트 상세페이지
		public Notice selectEventDetail(int noticeNo){
			Connection conn = jdt.getConnection();
			Notice notice = null;
			try {
				notice = noticeDao.selectEventDetail(conn, noticeNo);
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
	
	//공지 전체 게시글 갯수
	public Notice getTotalPosts(Notice notice) {
		Connection conn = jdt.getConnection();
		try {
			notice = noticeDao.getTotalPosts(conn, notice);
			jdt.commit(conn);
			
		}catch(DataAccessException e){
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}		
		return notice;
	}
	
	//조회수
	public int hitCounter(int noticeNo) {
		Connection conn = jdt.getConnection();
		int rs = 0;
		try {
			rs = noticeDao.hitCounter(conn, noticeNo);
			jdt.commit(conn);
		}catch (DataAccessException e) {
			jdt.rollback(conn);
		}finally {
			jdt.close(conn);
		}
		return rs;
	}
	
	 //모든 신고 내역을 (페이징해서) 가져오는 메서드
	   public ArrayList<Notice> getNoticeList(int start, int end){
	      
	      Connection conn = jdt.getConnection();
	      ArrayList<Notice> noticeList = null;
	      try {
	         noticeList = noticeDao.getNoticeList(conn, start, end);
	         jdt.commit(conn);
	      } catch (DataAccessException e){
	         jdt.rollback(conn);
	         throw new ToAlertException(e.error, e);
	      }finally {
	         jdt.close(conn);
	      }
	      return noticeList;
	   }
	   
	   
	   //[검색]한 모든 신고내역 개수 가져오는 메서드
	   public int getNoticeCnt(String select, String searchText) {
	      Connection conn = jdt.getConnection();
	      int allNoticeCnt = 0;
	      try {
	         allNoticeCnt = noticeDao.getNoticeCnt(conn, select, searchText);
	         jdt.commit(conn);
	      } catch (DataAccessException e){
	         jdt.rollback(conn);
	         throw new ToAlertException(e.error, e);
	      }finally {
	         jdt.close(conn);
	      }
	      return allNoticeCnt;
	   }

	   //[검색]한 모든 신고 내역을 (페이징해서) 가져오는 메서드
	   public ArrayList<Notice> getNoticeList(int start, int end, String select, String searchText){
	      Connection conn = jdt.getConnection();
	      ArrayList<Notice> noticeList = null;
	      try {
	         noticeList = noticeDao.getNoticeList(conn, start, end, select, searchText);
	         jdt.commit(conn);
	      } catch (DataAccessException e){
	         jdt.rollback(conn);
	         throw new ToAlertException(e.error, e);
	      }finally {
	         jdt.close(conn);
	      }
	      return noticeList;
	   }

	//==================이벤트 ========================
	
		//이벤트 전체 게시글 갯수
		public Notice getEventTotalPosts(Notice notice) {
			Connection conn = jdt.getConnection();
			try {
				notice = noticeDao.getEventTotalPosts(conn, notice);
				jdt.commit(conn);
				
			}catch(DataAccessException e){
				throw new ToAlertException(e.error);
			}finally {
				jdt.close(conn);
			}		
			return notice;
		}
	   
	   
	 //모든 이벤트게시글 (페이징해서) 가져오는 메서드
	   public ArrayList<Notice> getEventList(int start, int end){
	      
	      Connection conn = jdt.getConnection();
	      ArrayList<Notice> noticeList = null;
	      
	      try {
	         noticeList = noticeDao.getEventList(conn, start, end);
	         jdt.commit(conn);
	      } catch (DataAccessException e){
	         jdt.rollback(conn);
	         throw new ToAlertException(e.error, e);
	      }finally {
	         jdt.close(conn);
	      }
	      return noticeList;
	   }
	   
	   //[검색]한 모든 이벤트게시글 개수 가져오는 메서드
	   public int getEventCnt() {
	      Connection conn = jdt.getConnection();
	      int allEventCnt = 0;
	      try {
	    	  allEventCnt = noticeDao.getEventCnt(conn);
	         jdt.commit(conn);
	      } catch (DataAccessException e){
	         jdt.rollback(conn);
	         throw new ToAlertException(e.error, e);
	      }finally {
	         jdt.close(conn);
	      }
	      return allEventCnt;
	   }
	   
	   
	   //[검색]한 모든 이벤트게시글 개수 가져오는 메서드
	   public int getEventCnt(String select, String searchText) {
	      Connection conn = jdt.getConnection();
	      int allEventCnt = 0;
	      try {
	    	  allEventCnt = noticeDao.getEventCnt(conn, select, searchText); 
	         jdt.commit(conn);
	      } catch (DataAccessException e){
	         jdt.rollback(conn);
	         throw new ToAlertException(e.error, e);
	      }finally {
	         jdt.close(conn);
	      }
	      return allEventCnt;
	   }
	   
	   

	   //[검색]한 모든 이벤트게시글 (페이징해서) 가져오는 메서드
	   public ArrayList<Notice> getEventList(int start, int end, String select, String searchText){
	      Connection conn = jdt.getConnection();
	      ArrayList<Notice> eventList = null;
	      try {
	    	  eventList = noticeDao.getEventList(conn, start, end, select, searchText);
	         jdt.commit(conn);
	      } catch (DataAccessException e){
	         jdt.rollback(conn);
	         throw new ToAlertException(e.error, e);
	      }finally {
	         jdt.close(conn);
	      }
	      return eventList;
	   }
}
