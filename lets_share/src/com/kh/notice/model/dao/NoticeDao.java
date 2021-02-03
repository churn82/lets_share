package com.kh.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.DataAccessException;
import com.kh.common.template.JDBCTemplate;
import com.kh.notice.model.vo.Notice;



public class NoticeDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	//공지테이블에 공지게시글 등록
	public int insertNoticeBoard(Connection conn, Notice notice) {
		
		int res = 0;
		
		String sql = "insert into sh_notice "
				+ "(NOTICE_NO,NOTICE_TITLE,NOTICE_CONTENT,NOTICE_DATE,NOTICE_VIEW,NOTICE_TYPE,MB_ID) "
				+ "values(sc_notice_no.nextval,?,?,?,?,?,?)";
		
		PreparedStatement pstm = null;
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, notice.getNoticeTitle());
			pstm.setString(2, notice.getNoticeContent());
			pstm.setDate(3, notice.getNoticeDate());
			pstm.setInt(4, notice.getNoticeView());
			pstm.setString(5, notice.getNoticeType());
			pstm.setString(6, notice.getMbId());
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IB01, e);
		}finally {
			jdt.close(pstm);
		}
		
		return res;
		
	}
	
	//게시글을 수정하면 조회수가 0이 되는지 거기서 +1인지 	이어서 아닐까,,,
	//기본키 기준으로
	//수정게시판
	public int updateNoticeBoard(Connection conn, Notice notice) {
		
		int rs = 0;
		PreparedStatement pstm = null;
	
		try {
			String query = "update sh_notice set "
					+"notice_title=?, "
					+"notice_content=?, "
					+"notice_date=?, "
					+"notice_view=? "
					+"mb_id=? "
					+"where notice_no = ? ";
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, notice.getNoticeTitle());
			pstm.setString(2, notice.getNoticeContent());
			pstm.setDate(3, notice.getNoticeDate());
			pstm.setInt(4, notice.getNoticeView());
			pstm.setString(5, notice.getNoticeType());
			pstm.setString(6, notice.getMbId());
			rs = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UB01, e);
		}

		return rs;
		
	}
	
	
	//삭제게시판
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
	

	//공지 테이블 상세페이지 조회 만드는 중
	public ArrayList<Notice> selectNoticeDetail(Connection conn, int noticeNo){
		ArrayList<Notice> noticeList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from sh_notice where notice_no = ? ";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, noticeNo);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rs.getInt(1));
				notice.setNoticeTitle(rs.getString(2));
				notice.setNoticeContent(rs.getString(3));
				notice.setNoticeDate(rs.getDate(4));
				notice.setNoticeView(rs.getInt(5));
				notice.setNoticeType(rs.getString(6));
				notice.setMbId(rs.getString(7));
				noticeList.add(notice);
			}			
			
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SB01, e);
		}finally {
			jdt.close(rs,pstm);
		}

		return noticeList;
		
	}
	
	
	//공지테이블 목록 조회(번호,제목,작성자,작성날짜,조회수)
	public List<Notice> selectNoticeList(Connection conn, String noticeNo) {
		
		List<Notice> noticeList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from sh_notice";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setMbId(rs.getString("mb_id"));
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
	
	
	//페이지 개수 제한
	
	

	
	
	
	
	
	
	
}
