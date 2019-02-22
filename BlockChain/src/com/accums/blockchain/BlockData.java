package com.accums.blockchain;

public class BlockData {

	private String memberId;
	private String planId;
	private Integer utalizedUnits;
	private Integer remainingUnits;
	private String item;

	public BlockData(String memberId, String planId, Integer utalizedUnits, Integer remainingUnits, String item) {
		super();
		this.memberId = memberId;
		this.planId = planId;
		this.utalizedUnits = utalizedUnits;
		this.remainingUnits = remainingUnits;
		this.item = item;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public Integer getUtalizedUnits() {
		return utalizedUnits;
	}
	public void setUtalizedUnits(Integer utalizedUnits) {
		this.utalizedUnits = utalizedUnits;
	}
	public Integer getRemainingUnits() {
		return remainingUnits;
	}
	public void setRemainingUnits(Integer remainingUnits) {
		this.remainingUnits = remainingUnits;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "BlockData [memberId=" + memberId + ", planId=" + planId + ", utalizedUnits=" + utalizedUnits
				+ ", remainingUnits=" + remainingUnits + ", item=" + item + "]";
	}



}
