package com.ssafy.mytown.model.dto.house;


public class BaseAddrDto {
	private String sidoCode;
	private String gugunCode;
	private String dongCode;
	
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	public BaseAddrDto(String sidoCode, String gugunCode, String dongCode, String sidoName, String gugunName, String dongName) {
		super();
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.dongCode = dongCode;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
	}
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	@Override
	public String toString() {
		return "BaseAddrDto [sidoCode=" + sidoCode + ", gugunCode=" + gugunCode + ", dongCode=" + dongCode + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName=" + dongName + "]";
	}
	
	
}
