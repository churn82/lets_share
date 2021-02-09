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
			System.out.println(res);
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
	
	
	
	/*
	
	//공지게시판 삭제 기능
	public int deleteNoticeBoard(Connection conn, int noticeNo) {
		
		int res = 0;
		PreparedStatement pstm = null;
		
		try {
			//쿼리 수정해야 함
			String sql = "delete form sh_notice where notice_no = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, noticeNo);
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DB01, e);
		}finally {
			jdt.close(pstm);
		}

		return res;
		
	}
	*/
	
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
			String sql = "select * from sh_notice where notice_type='notice' order by notice_no desc";
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
	
	
	/*
	
	//등급테이블에서 MB_LEVEL가 관리자와 개발자인 ID를 가져오는
	public String getManagerId(Connection conn, String mbId) {
		String managerId = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "SELECT MB_ID FROM SH_MEMBER WHERE MB_LEVEL IN('MB10','MB11') = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1,mbId);
			rs = pstm.executeQuery();
			while(rs.next()) {
				managerId = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.MI02, e);
		}
		
		return managerId;
	}
	
*/
	//첫번째 : 요청온 페이지가 왔을 때 아래쪽 페이지 번호들이 출력되는 페이지 목록의 범위를 지정
	//두번째 : 페이지 번호를 눌렀을 때 해당 페이지의 속하는 게시물의 목록을 뽑아내야 한다 (이 부분은 db 쿼리로 수행)
		
	//전체 페이지 수 = (전체 게시물 수 / 한 페이지에 출력할 수 ) + 1 (나머지가 있을 경우)	
	
	//한 화면에 보여줄 리스트 갯 수
	public static final int pageList = 10;
	//페이징 범위의 갯수
	public static final int pagingCount = 10;
	
	
	//페이지 범위 구하기
	//"select count(*) from sh_notice";
	public int[] paging(int page) {
		int totalContent = 0;
		int totalPage = 0;
		int pageArr[] = null;
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select count(*) from sh_notice where notice_type='notice' ";
		
		try {
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				totalContent += rs.getInt(1);
			}
			
			if(totalContent == 0) {
				return null;
			}
			
			//totalPage = totalContent / noticeList.
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return pageArr;
		
	}
	
	//공지 게시글의 전체 수를 리턴하는 메서드
	public int getAllCount(Connection conn) {
		int count = 0;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from sh_notice where notice_type='notice' ";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SB01, e);
		}
		return count;
	}
	
	
	
	
	
	
	
}
