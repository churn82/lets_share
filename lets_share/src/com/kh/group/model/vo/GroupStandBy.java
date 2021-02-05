package com.kh.group.model.vo;

public class GroupStandBy {

	private int groupId;
	private String memberId;
	private String approval;
	
	public GroupStandBy() {
		
	}
	
	public GroupStandBy(int groupId, String memberId, String approval) {
		super();
		this.groupId = groupId;
		this.memberId = memberId;
		this.approval = approval;
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
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	@Override
	public String toString() {
		return "GroupStandBy [groupId=" + groupId + ", memberId=" + memberId + ", approval=" + approval + "]";
	}
	
}
