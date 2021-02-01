
package com.excel.pojos;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "page", "per_page", "total", "total_pages", "data", "support" })
public class MainClass {

	@JsonProperty("page")
	private Integer page;
	@JsonProperty("per_page")
	private Integer perPage;
	@JsonProperty("total")
	private Integer total;
	@JsonProperty("total_pages")
	private Integer totalPages;
	@JsonProperty("data")
	private List<Datum> data = null;
	@JsonProperty("support")
	private Support support;

	@JsonProperty("page")
	public Integer getPage() {
		return page;
	}

	@JsonProperty("page")
	public void setPage(Integer page) {
		this.page = page;
	}

	@JsonProperty("per_page")
	public Integer getPerPage() {
		return perPage;
	}

	@JsonProperty("per_page")
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	@JsonProperty("total_pages")
	public Integer getTotalPages() {
		return totalPages;
	}

	@JsonProperty("total_pages")
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	@JsonProperty("data")
	public List<Datum> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(List<Datum> data) {
		this.data = data;
	}

	@JsonProperty("support")
	public Support getSupport() {
		return support;
	}

	@JsonProperty("support")
	public void setSupport(Support support) {
		this.support = support;
	}

}
