package com.excel.NSE.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailData {
	Long strikePrice;
	String expiryDate;

	@JsonProperty("PE")
	InternalData PE;
	@JsonProperty("CE")
	InternalData CE;

	DetailData() {

	}

	public DetailData(Long strikePrice, String expiryDate, InternalData PE, InternalData CE) {
		this.strikePrice = strikePrice;
		this.expiryDate = expiryDate;
		this.PE = PE;
		this.CE = CE;
	}

	public Long getStrikePrice() {
		return strikePrice;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	@JsonProperty("PE")
	public InternalData getPE() {
		return this.PE;
	}

	@JsonProperty("CE")
	public InternalData getCE() {
		return this.CE;
	}

	public void setStrikePrice(Long strikePrice) {
		this.strikePrice = strikePrice;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setPE(InternalData PE) {
		this.PE = PE;
	}

	public void setCE(InternalData CE) {
		this.CE = CE;
	}
}
