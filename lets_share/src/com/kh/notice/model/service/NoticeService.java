package com.kh.notice.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kh.common.template.JDBCTemplate;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {
	
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	NoticeDao noticeDao = new NoticeDao();
	
	public void insertNoticeBoard(String userId, HttpServletRequest request) {
		Connection conn = jdt.getConnection();
		
		
		
		Notice notice = new Notice();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
