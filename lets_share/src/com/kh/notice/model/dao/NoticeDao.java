package com.kh.notice.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.notice.model.vo.Notice;
import com.kh.report.model.vo.Report;

public class NoticeDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public NoticeDao() {
		// TODO Auto-generated constructor stub
	}
	
	//공지게시글 등록
	public int insertNoticeBoard(Connection conn, Notice notice) {
		int res = 0;
		String sql = "insert into sh_notice "
				+ "(NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,NOTICE_DATE,NOTICE_TYPE,NOTICE_ALL_COUNT) "
				+ "values(sc_notice_no.nextval,?,?,sysdate,'notice',sc_notice_all_count.nextval)";
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
	
	//"select * from sh_notice where notice_type='event' and notice_delete is null and notice_title like '%'?'%' order by notice_no desc";
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
	public Notice getTotalPosts(Connection conn, Notice notice) {
		PreparedStatement pstm = null; //pstm필요없지만 일반 stmt는 close메소드 없으니까 그냥 pstm으로
		ResultSet rs = null;
		String sql = "select notice_total_posts from (select notice_total_posts from sh_notice where notice_type='notice' "
				+ "and notice_delete is null and notice_total_posts is not null order by notice_total_posts desc) where rownum = 1";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				notice = new Notice();
				notice.setNoticeTotalPosts(rs.getInt("notice_total_posts"));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.PB01, e);
		}finally {
			jdt.close(rs, pstm);
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
	
    //모든 post들을 (페이징해서) 가져오는 메서드
    public ArrayList<Notice> getNoticeList(Connection conn, int start, int end){
       ArrayList<Notice> noticetList = new ArrayList<Notice>();
       PreparedStatement pstm = null;
       ResultSet rset = null;
       String query = 
               "SELECT * FROM ("
             + "    SELECT ROWNUM NUM, R.*"
             + "        FROM (SELECT * FROM SH_NOTICE ORDER BY NOTICE_DATE DESC) R"
             + ") WHERE NUM BETWEEN ? AND ?";
       try {
          pstm = conn.prepareStatement(query);
          pstm.setInt(1, start);
          pstm.setInt(2, end);
          rset = pstm.executeQuery();
          while(rset.next()) {
             Notice notice = new Notice();
             notice.setNoticeNo(rset.getInt("NOTICE_NO"));
             notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
             notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
             notice.setNoticeDate(rset.getDate("NOTICE_DATE"));
             notice.setNoticeView(rset.getInt("NOTICE_VIEW"));
             notice.setNoticeType(rset.getString("NOTICE_TYPE"));
             notice.setNoticeDelete(rset.getDate("NOTICE_DELETE"));
             noticetList.add(notice);
          }
       } catch (SQLException e) {
          throw new DataAccessException(ErrorCode.PB01, e);
       } finally {
          jdt.close(rset, pstm);
       }
       return noticetList;
    }
 
 
     //[검색]한 모든 내역 개수 가져오는 메서드
    public int getNoticeCnt(Connection conn, String select, String searchText) {
       int allNoticeCnt = 0;
       PreparedStatement pstm = null;
       ResultSet rset = null;
       String query = "SELECT COUNT(*) FROM SH_NOTICE WHERE "+select+" LIKE ? AND NOTICE_TYPE='notice' AND NOTICE_DELETE IS NULL;";
       
       try {
          pstm = conn.prepareStatement(query);
          pstm.setString(1, searchText);
          rset = pstm.executeQuery();
          if(rset.next()) {
             allNoticeCnt = rset.getInt(1);
          }
       } catch (SQLException e) {
          throw new DataAccessException(ErrorCode.PB01, e);
       }
       return allNoticeCnt;
    }
 
    
    //[검색]한 모든 내역을 (페이징해서) 가져오는 메서드
    public ArrayList<Notice> getNoticeList(Connection conn, int start, int end, String select, String searchText){
       ArrayList<Notice> noticeList = new ArrayList<Notice>();
       PreparedStatement pstm = null;
       ResultSet rset = null;
       String query = 
               "SELECT * FROM ("
             + "    SELECT ROWNUM NUM, R.*"
             + "        FROM (SELECT * FROM SH_NOTICE WHERE "+select+" LIKE '?' AND NOTICE_TYPE='notice' AND NOTICE_DELETE IS NULL ORDER BY NOTICE_DATE DESC) R"
             + ") WHERE NUM BETWEEN ? AND ?";
       try {
          pstm = conn.prepareStatement(query);
          pstm.setString(1, searchText);
          pstm.setInt(2, start);
          pstm.setInt(3, end);
          rset = pstm.executeQuery();
          while(rset.next()) {
             Notice notice = new Notice();
             notice.setNoticeNo(rset.getInt("NOTICE_NO"));
             notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
             notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
             notice.setNoticeDate(rset.getDate("NOTICE_DATE"));
             notice.setNoticeView(rset.getInt("NOTICE_VIEW"));
             notice.setNoticeType(rset.getString("NOTICE_TYPE"));
             notice.setNoticeDelete(rset.getDate("NOTICE_DELETE"));
             noticeList.add(notice);
          }
       } catch (SQLException e) {
          throw new DataAccessException(ErrorCode.PB01, e);
       } finally {
          jdt.close(rset, pstm);
       }
       return noticeList;
    }

	
		
	
}
