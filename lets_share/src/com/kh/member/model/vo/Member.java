package com.kh.member.model.vo;

import java.sql.Date;

public class Member {

	private String mbId;
	private String mbpassword;
	private String mbnick;
	private String mbtel;
	private String mbemail;
	private int mbpoint;
	private String mblevel;
	private Date mbRegisterDate;
	private Date mbLeaveDate;
	private String mbName;
	
	
	public Member() {
		
	}

	
	public Member(String mbId, String mbpassword, String mbnick, String mbtel, String mbemail, int mbpoint,
			String mblevel, Date mbRegisterDate, Date mbLeaveDate, String mbName) {
		super();
		this.mbId = mbId;
		this.mbpassword = mbpassword;
		this.mbnick = mbnick;
		this.mbtel = mbtel;
		this.mbemail = mbemail;
		this.mbpoint = mbpoint;
		this.mblevel = mblevel;
		this.mbRegisterDate = mbRegisterDate;
		this.mbLeaveDate = mbLeaveDate;
		this.mbName = mbName;
	}
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public String getMbpassword() {
		return mbpassword;
	}
	public void setMbpassword(String mbpassword) {
		this.mbpassword = mbpassword;
	}
	public String getMbnick() {
		return mbnick;
	}
	public void setMbnick(String mbnick) {
		this.mbnick = mbnick;
	}
	public String getMbtel() {
		return mbtel;
	}
	public void setMbtel(String mbtel) {
		this.mbtel = mbtel;
	}
	public String getMbemail() {
		return mbemail;
	}
	public void setMbemail(String mbemail) {
		this.mbemail = mbemail;
	}
	public int getMbpoint() {
		return mbpoint;
	}
	public void setMbpoint(int mbpoint) {
		this.mbpoint = mbpoint;
	}
	public String getMblevel() {
		return mblevel;
	}
	public void setMblevel(String mblevel) {
		this.mblevel = mblevel;
	}
	public Date getMbRegisterDate() {
		return mbRegisterDate;
	}
	public void setMbRegisterDate(Date mbRegisterDate) {
		this.mbRegisterDate = mbRegisterDate;
	}
	public Date getMbLeaveDate() {
		return mbLeaveDate;
	}
	public void setMbLeaveDate(Date mbLeaveDate) {
		this.mbLeaveDate = mbLeaveDate;
	}
	public String getMbName() {
		return mbName;
	}
	public void setMbName(String mbName) {
		this.mbName = mbName;
	}


	@Override
	public String toString() {
		return "Member [mbId=" + mbId + ", mbpassword=" + mbpassword + ", mbnick=" + mbnick + ", mbtel=" + mbtel
				+ ", mbemail=" + mbemail + ", mbpoint=" + mbpoint + ", mblevel=" + mblevel + ", mbRegisterDate="
				+ mbRegisterDate + ", mbLeaveDate=" + mbLeaveDate + ", mbName=" + mbName + "]";
	}
	
	
}