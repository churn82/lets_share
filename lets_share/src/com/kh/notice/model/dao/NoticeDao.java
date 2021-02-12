package com.kh.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.notice.model.vo.Notice;

public class NoticeDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public NoticeDao() {
		// TODO Auto-generated constructor stub
	}
	
	//공지게시글 등록
	public int insertNoticeBoard(Connection conn, Notice notice) {
		int res = 0;
		String sql = "insert into sh_notice "
				+ "(NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,NOTICE_DATE,NOTICE_TYPE) "
				+ "values(sc_notice_no.nextval,?,?,sysdate,'notice')";
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, notice.getNoticeTitle());
			pstm.setString(2, notice.getNoticeContent());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IB01, e);
		}finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	
	//이벤트게시글 등록
	public int insertEventBoard(Connection conn, Notice notice) {
		int res = 0;
		String sql = "insert into sh_notice "
				+ "(NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,NOTICE_DATE,NOTICE_TYPE) "
				+ "values(sc_notice_no.nextval,?,?,sysdate,'event')";
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, notice.getNoticeTitle());
			pstm.setString(2, notice.getNoticeContent());
			res = pstm.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IB01, e);
		}finally {
			jdt.close(pstm);
		}
		return res;
	}
	
	//수정전
	public Notice beforeUpdate(Connection conn, int noticeNo){
		Notice notice = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from sh_notice where notice_no=? and notice_type='notice' ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, noticeNo);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				notice = new Notice();
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setNoticeDate(rs.getDate("notice_date"));
				notice.setNoticeView(rs.getInt("notice_view"));
			}			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UB01, e);
		}finally {
			jdt.close(rs,pstm);
		}
		return notice;
	}
	
	//게시글 수정 요청
	public int updateRequest(Connection conn, Notice notice) {
		
		int res = 0;
		PreparedStatement pstm = null;
		String query = "update sh_notice set "
				+"notice_title=?, "
				+"notice_content=?, "
				+"notice_date=sysdate "
				+"where notice_no=?";
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, notice.getNoticeTitle());
			pstm.setString(2, notice.getNoticeContent());
			pstm.setInt(3, notice.getNoticeNo());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UB01, e);
		}finally {
			jdt.close(pstm);
		}
		return res;
		
	}
	

	//공지게시판 삭제 기능
	public int deleteNoticeBoard(Connection conn, int noticeNo) {
		int res = 0;
		PreparedStatement pstm = null;
		String sql = "update sh_notice set notice_delete=sysdate where notice_no = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, noticeNo);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DB01, e);
		}finally {
			jdt.close(pstm,conn);
		}

		return res;
		
	}
	
	  
	//이벤트 게시판 삭제 기능
		public int deleteEventBoard(Connection conn, int noticeNo) {
			int res = 0;
			PreparedStatement pstm = null;
			String sql = "update sh_notice set notice_delete=sysdate where notice_no = ?";
			
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, noticeNo);
				res = pstm.executeUpdate();
			} catch (SQLException e) {
				throw new DataAccessException(ErrorCode.DB01, e);
			}finally {
				jdt.close(pstm,conn);
			}

			return res;
			
		}
		

	//공지 테이블 상세페이지
	public Notice selectNoticeDetail(Connection conn, int noticeNo){
		Notice notice = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from sh_notice where notice_no=? and notice_type='notice' ";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, noticeNo);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				notice = new Notice();
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setNoticeDate(rs.getDate("notice_date"));
				notice.setNoticeView(rs.getInt("notice_view"));
				
				
				
			}			
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SB01, e);
		}finally {
			jdt.close(rs,pstm);
		}

		return notice;
		
	}
	
	
	//공지테이블 목록 조회(번호,제목,작성자,작성날짜,조회수)
	public ArrayList<Notice> selectNoticeList(Connection conn) {
		
		ArrayList<Notice> noticeList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from sh_notice where notice_type='notice' and notice_delete is null order by notice_no desc";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setNoticeDate(rs.getDate("notice_date"));
				notice.setNoticeView(rs.getInt("notice_view"));
				noticeList.add(notice);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SB01, e);
		}finally {
			jdt.close(rs,pstm);
		}
	
		return noticeList;
	}
	
	//이벤트 상세페이지
	public Notice selectEventDetail(Connection conn, int noticeNo){
		Notice notice = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from sh_notice where notice_no=? and notice_type='event' ";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, noticeNo);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				notice = new Notice();
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setNoticeDate(rs.getDate("notice_date"));
				notice.setNoticeView(rs.getInt("notice_view"));	
			}			
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SB01, e);
		}finally {
			jdt.close(rs,pstm);
		}

		return notice;
		
	}
	
	//이벤트 목록 조회(번호,제목,작성자,작성날짜,조회수)
		public ArrayList<Notice> selectEventList(Connection conn) {
			
			ArrayList<Notice> noticeList = new ArrayList<>();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			
			try {
				String sql = "select * from sh_notice where notice_type='event' and notice_delete is null order by notice_no desc";
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				
				while(rs.next()) {
					Notice notice = new Notice();
					notice.setNoticeNo(rs.getInt("notice_no"));
					notice.setNoticeTitle(rs.getString("notice_title"));
					notice.setNoticeDate(rs.getDate("notice_date"));
					notice.setNoticeView(rs.getInt("notice_view"));
					noticeList.add(notice);
				}
				
			} catch (SQLException e) {
				throw new DataAccessException(ErrorCode.SB01, e);
			}finally {
				jdt.close(rs,pstm);
			}
		
			return noticeList;
		}
	
	//공지 게시글의 전체 수를 리턴하는 메서드
	public Notice getAllCount(Connection conn) {
		Notice notice = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from sh_notice where notice_type='notice' and notice_delete is null ";
		
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			notice = new Notice();
			notice.setNoticeAllCount(rs.getInt("notice_all_count"));
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SB01, e);
		}finally {
			jdt.close(pstm);
			jdt.close(rs);
		}
		return notice;
	}
	
		//조회수
		public int hitCounter(Connection conn, int noticeNo) {
			int rs = 0;
			PreparedStatement pstm = null;
			String sql = "update sh_notice set notice_view = notice_view+1 where notice_no = ?";
			
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, noticeNo);
				rs = pstm.executeUpdate();
			} catch (SQLException e) {
				throw new DataAccessException(ErrorCode.DB01, e);
			}finally {
				jdt.close(pstm);
			}
			return rs;
		}
	
	
	
	
	
	
	
}
