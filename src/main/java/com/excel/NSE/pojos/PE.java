package com.excel.NSE.pojos;

public class PE {
	Long totOI;
	Long totVol;

	PE() {

	}

	public PE(Long totOI, Long totVol) {
		this.totOI = totOI;
		this.totVol = totVol;
	}

	public Long getTotOI() {
		return totOI;
	}

	public Long getTotVol() {
		return totVol;
	}

	public void setTotOI(Long totOI) {
		this.totOI = totOI;
	}

	public void setTotVol(Long totVol) {
		this.totVol = totVol;
	}
}
