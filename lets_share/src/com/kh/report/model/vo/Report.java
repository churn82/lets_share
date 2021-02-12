package com.kh.report.model.vo;

import java.sql.Date;

public class Report {
	
	private int reportIdx;
	private String memberId;
	private int groupId;
	private String content;
	private Date regdate;
	private String reply;
	private int clear;
	private String title;
	
	public Report() {
		
	}
	
	
	public int getReportIdx() {
		return reportIdx;
	}
	public void setReportIdx(int reportIdx) {
		this.reportIdx = reportIdx;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public int getClear() {
		return clear;
	}
	public void setClear(int clear) {
		this.clear = clear;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Report [reportIdx=" + reportIdx + ", memberId=" + memberId + ", groupId=" + groupId + ", content="
				+ content + ", regdate=" + regdate + ", reply=" + reply + ", clear=" + clear + ", title=" + title + "]";
	}
	
	
	
	
}
