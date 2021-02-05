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
	public String getMbId() {
		return mbId;
	}
	public void setMbId(String mbId) {
		this.mbId = mbId;
	}
	public String getMbPassword() {
		return mbpassword;
	}
	public void setMbPassword(String mbPassword) {
		this.mbpassword = mbPassword;
	}
	public String getMbNick() {
		return mbnick;
	}
	public void setMbNick(String mbnick) {
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
	@Override
	public String toString() {
		return "Member [mbId=" + mbId + ", mbpassword=" + mbpassword + ", mbnick=" + mbnick + ", mbtel=" + mbtel
				+ ", mbemail=" + mbemail + ", mbpoint=" + mbpoint + ", mblevel=" + mblevel + ", mbRegisterDate="
				+ mbRegisterDate + ", mbLeaveDate=" + mbLeaveDate + "]";
	}
	
}
