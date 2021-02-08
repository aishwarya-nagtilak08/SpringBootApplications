package com.excel.NSE.pojos;

public class InternalData {

	Long totalSellQuantity;
	Long totalBuyQuantity;
	Long strikePrice;
	Long askQty;
	Long lastPrice;
	Long bidQty;
	Long askPrice;
	Long pChange;
	Long pchangeinOpenInterest;
	String expiryDate;
	String underlying;
	Long changeinOpenInterest;
	Long totalTradedVolume;
	Double underlyingValue;
	Long impliedVolatility;
	Double bidprice;
	Long openInterest;
	String identifier;
	Long change;

	InternalData() {

	}

	public InternalData(Long totalSellQuantity, Long totalBuyQuantity, Long strikePrice, Long askQty, Long lastPrice,
			Long bidQty, Long askPrice, Long pChange, Long pchangeinOpenInterest, String expiryDate, String underlying,
			Long changeinOpenInterest, Long totalTradedVolume, Double underlyingValue, Long impliedVolatility,
			Double bidprice, Long openInterest, String identifier, Long change) {
		this.totalSellQuantity = totalSellQuantity;
		this.totalBuyQuantity = totalBuyQuantity;
		this.strikePrice = strikePrice;
		this.askQty = askQty;
		this.lastPrice = lastPrice;
		this.bidQty = bidQty;
		this.askPrice = askPrice;
		this.pChange = pChange;
		this.pchangeinOpenInterest = pchangeinOpenInterest;
		this.expiryDate = expiryDate;
		this.underlying = underlying;
		this.changeinOpenInterest = changeinOpenInterest;
		this.totalTradedVolume = totalTradedVolume;
		this.underlyingValue = underlyingValue;
		this.impliedVolatility = impliedVolatility;
		this.bidprice = bidprice;
		this.openInterest = openInterest;
		this.identifier = identifier;
		this.change = change;
	}

	public Long getTotalSellQuantity() {
		return totalSellQuantity;
	}

	public Long getTotalBuyQuantity() {
		return totalBuyQuantity;
	}

	public Long getStrikePrice() {
		return strikePrice;
	}

	public Long getAskQty() {
		return askQty;
	}

	public Long getLastPrice() {
		return lastPrice;
	}

	public Long getBidQty() {
		return bidQty;
	}

	public Long getAskPrice() {
		return askPrice;
	}

	public Long getpChange() {
		return pChange;
	}

	public Long getPchangeinOpenInterest() {
		return pchangeinOpenInterest;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public String getUnderlying() {
		return underlying;
	}

	public Long getChangeinOpenInterest() {
		return changeinOpenInterest;
	}

	public Long getTotalTradedVolume() {
		return totalTradedVolume;
	}

	public Double getUnderlyingValue() {
		return underlyingValue;
	}

	public Long getImpliedVolatility() {
		return impliedVolatility;
	}

	public Double getBidprice() {
		return bidprice;
	}

	public Long getOpenInterest() {
		return openInterest;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Long getChange() {
		return change;
	}

	public void setTotalSellQuantity(Long totalSellQuantity) {
		this.totalSellQuantity = totalSellQuantity;
	}

	public void setTotalBuyQuantity(Long totalBuyQuantity) {
		this.totalBuyQuantity = totalBuyQuantity;
	}

	public void setStrikePrice(Long strikePrice) {
		this.strikePrice = strikePrice;
	}

	public void setAskQty(Long askQty) {
		this.askQty = askQty;
	}

	public void setLastPrice(Long lastPrice) {
		this.lastPrice = lastPrice;
	}

	public void setBidQty(Long bidQty) {
		this.bidQty = bidQty;
	}

	public void setAskPrice(Long askPrice) {
		this.askPrice = askPrice;
	}

	public void setpChange(Long pChange) {
		this.pChange = pChange;
	}

	public void setPchangeinOpenInterest(Long pchangeinOpenInterest) {
		this.pchangeinOpenInterest = pchangeinOpenInterest;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}

	public void setChangeinOpenInterest(Long changeinOpenInterest) {
		this.changeinOpenInterest = changeinOpenInterest;
	}

	public void setTotalTradedVolume(Long totalTradedVolume) {
		this.totalTradedVolume = totalTradedVolume;
	}

	public void setUnderlyingValue(Double underlyingValue) {
		this.underlyingValue = underlyingValue;
	}

	public void setImpliedVolatility(Long impliedVolatility) {
		this.impliedVolatility = impliedVolatility;
	}

	public void setBidprice(Double bidprice) {
		this.bidprice = bidprice;
	}

	public void setOpenInterest(Long openInterest) {
		this.openInterest = openInterest;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setChange(Long change) {
		this.change = change;
	}
}
