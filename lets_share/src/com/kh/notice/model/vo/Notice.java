package com.kh.notice.model.vo;

import java.sql.Date;

public class Notice {
//테이블명: SH_NOTICE
//NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE, NOTICE_VIEW, NOTICE_TYPE, MB_ID
	private int noticeNo;			//글번호
	private String noticeTitle;		//제목
	private String noticeContent;	//내용
	private Date noticeDate;		//날짜
	private int noticeView;			//조회수
	private String noticeType;		//타입(notice/event)
	private Date noticeDelete;
	private int noticeAllCount;
	private int noticeCategory;
	private String noticeKeyword;
	
	public Notice() {
		
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public int getNoticeView() {
		return noticeView;
	}

	public void setNoticeView(int noticeView) {
		this.noticeView = noticeView;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public Date getNoticeDelete() {
		return noticeDelete;
	}

	public void setNoticeDelete(Date noticeDelete) {
		this.noticeDelete = noticeDelete;
	}

	public int getNoticeAllCount() {
		return noticeAllCount;
	}

	public void setNoticeAllCount(int noticeAllCount) {
		this.noticeAllCount = noticeAllCount;
	}

	public int getNoticeCategory() {
		return noticeCategory;
	}

	public void setNoticeCategory(int noticeCategory) {
		this.noticeCategory = noticeCategory;
	}

	public String getNoticeKeyword() {
		return noticeKeyword;
	}

	public void setNoticeKeyword(String noticeKeyword) {
		this.noticeKeyword = noticeKeyword;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeDate=" + noticeDate + ", noticeView=" + noticeView + ", noticeType=" + noticeType
				+ ", noticeDelete=" + noticeDelete + ", noticeAllCount=" + noticeAllCount + ", noticeCategory="
				+ noticeCategory + ", noticeKeyword=" + noticeKeyword + "]";
	}

	

	

	
	

	

	
	
	
	
	
	
	
}
