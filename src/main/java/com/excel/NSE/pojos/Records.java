package com.excel.NSE.pojos;

import java.util.List;

public class Records {
	String timestamp;
    Double underlyingValue;
    List<DetailData> data;
    List<String> strikePrices;
    List<String> expiryDates;

    Records(){

    }
    public Records(String timestamp, Double underlyingValue, List<DetailData> data, List<String> strikePrices, List<String> expiryDates) {
        this.timestamp = timestamp;
        this.underlyingValue = underlyingValue;
        this.data = data;
        this.strikePrices = strikePrices;
        this.expiryDates = expiryDates;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Double getUnderlyingValue() {
        return underlyingValue;
    }

    public List<DetailData> getData() {
        return data;
    }

    public List<String> getStrikePrices() {
        return strikePrices;
    }

    public List<String> getExpiryDates() {
        return expiryDates;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setUnderlyingValue(Double underlyingValue) {
        this.underlyingValue = underlyingValue;
    }

    public void setData(List<DetailData> data) {
        this.data = data;
    }

    public void setStrikePrices(List<String> strikePrices) {
        this.strikePrices = strikePrices;
    }

    public void setExpiryDates(List<String> expiryDates) {
        this.expiryDates = expiryDates;
    }

}
