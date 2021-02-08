package com.excel.NSE.pojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Filtered {
	List<DetailData> data;
	@JsonProperty("CE")
	CE CE;
	@JsonProperty("PE")
	PE PE;

	Filtered() {

	}

	public Filtered(List<DetailData> data, CE CE, PE PE) {
		this.data = data;
		this.CE = CE;
		this.PE = PE;
	}

	public List<DetailData> getData() {
		return data;
	}

	public CE getCE() {
		return CE;
	}

	public PE getPE() {
		return PE;
	}

	public void setData(List<DetailData> data) {
		this.data = data;
	}

	public void setCE(CE CE) {
		this.CE = CE;
	}

	public void setPE(PE PE) {
		this.PE = PE;
	}
}
