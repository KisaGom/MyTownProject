package com.ssafy.mytown.model.dto.statistics;

public class AvgDealAmountDto {
	private String address;
	private String avgDealAmount;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAvgDealAmount() {
		return avgDealAmount;
	}
	public void setAvgDealAmount(String avgDealAmount) {
		this.avgDealAmount = avgDealAmount;
	}
	@Override
	public String toString() {
		return "AvgDealAmountDto [address=" + address + ", avgDealAmount=" + avgDealAmount + "]";
	}
	
	
}
