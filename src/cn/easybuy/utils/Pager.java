package cn.easybuy.utils;

import java.io.Serializable;

public class Pager implements Serializable{
	private int total;
	private int rowPerPage;
	private int currentPage;
	private int pageCount; 
	private String url; 
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	
	public Pager(int total, int rowPerPage, int currentPage) {
		this.total = total;
		this.rowPerPage = rowPerPage;
		if (this.total%this.rowPerPage==0) {
			this.pageCount=this.total/this.rowPerPage;
		}else if (this.total%this.rowPerPage>0) {
			this.pageCount=this.total/this.rowPerPage+1;
		}else {
			this.pageCount=0;
		}
		if (currentPage<1) {
			this.currentPage = 1;
		}else if(currentPage<=pageCount){
			this.currentPage=currentPage;
		}else{
			this.currentPage=pageCount;
		}
		
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
