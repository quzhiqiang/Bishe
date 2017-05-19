package com.qzq.bean;

public class Page {
	
	private Long totalSize;
	private Long pageNum;
	private Long pageSize;
	private Long pageTotal;
	private Long start;
	
	public Long getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}
	public Long getPageNum() {
		return pageNum;
	}
	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}
	
	

}
