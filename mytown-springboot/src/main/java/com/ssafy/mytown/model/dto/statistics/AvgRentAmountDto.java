package com.ssafy.mytown.model.dto.statistics;

public class AvgRentAmountDto {
	private String address;
	private String avgDeposit;
	//월세는 보증금, 전세는 전세금
	
	private String avgRentAmount;
	//월세 계약의 월세, 전세는 없음

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvgDeposit() {
		return avgDeposit;
	}

	public void setAvgDeposit(String avgDeposit) {
		this.avgDeposit = avgDeposit;
	}

	public String getAvgRentAmount() {
		return avgRentAmount;
	}

	public void setAvgRentAmount(String avgRentAmount) {
		this.avgRentAmount = avgRentAmount;
	}

	@Override
	public String toString() {
		return "AvgRentAmountDto [address=" + address + ", avgDeposit=" + avgDeposit + ", avgRentAmount=" + avgRentAmount + "]";
	}
	
	
}
