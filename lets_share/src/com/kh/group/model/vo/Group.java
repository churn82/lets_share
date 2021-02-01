package com.kh.group.model.vo;

import java.sql.Date;

public class Group {

	private int groupId;
	private String memberId;
	private int memberCnt;
	private int groupPayDate;
	private String accountInfo;
	private String shareId;
	private String sharePw;
	private Date date;
	private char autoYN;
	private String serviceCode;
	
	public Group() {
		
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getMemberCnt() {
		return memberCnt;
	}

	public void setMemberCnt(int memberCnt) {
		this.memberCnt = memberCnt;
	}

	public int getGroupPayDate() {
		return groupPayDate;
	}

	public void setGroupPayDate(int groupPayDate) {
		this.groupPayDate = groupPayDate;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public String getSharePw() {
		return sharePw;
	}

	public void setSharePw(String sharePw) {
		this.sharePw = sharePw;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public char getAutoYN() {
		return autoYN;
	}

	public void setAutoYN(char autoYN) {
		this.autoYN = autoYN;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", memberId=" + memberId + ", memberCnt=" + memberCnt + ", groupPayDate="
				+ groupPayDate + ", accountInfo=" + accountInfo + ", shareId=" + shareId + ", sharePw=" + sharePw
				+ ", date=" + date + ", autoYN=" + autoYN + ", serviceCode=" + serviceCode + "]";
	}
	
}
