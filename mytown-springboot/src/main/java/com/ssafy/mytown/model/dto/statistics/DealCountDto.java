package com.ssafy.mytown.model.dto.statistics;

public class DealCountDto {
	private String dongCode;
	private String count;
	private String addr;
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "DealCountDto [dongCode=" + dongCode + ", count=" + count + ", addr=" + addr + "]";
	}
}
