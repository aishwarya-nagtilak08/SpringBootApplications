package com.excel.NSE.pojos;

public class NSEObject {
	Records records;
	Filtered filtered;

	NSEObject() {

	}

	public NSEObject(Records records, Filtered filtered) {
		this.records = records;
		this.filtered = filtered;
	}

	public Records getRecords() {
		return records;
	}

	public Filtered getFiltered() {
		return filtered;
	}

	public void setRecords(Records records) {
		this.records = records;
	}

	public void setFiltered(Filtered filtered) {
		this.filtered = filtered;
	}
}
