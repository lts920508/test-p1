package cn.easybuy.params;

import cn.easybuy.entity.News;

public class NewsParams extends News {
	private boolean isPage=false;
	private int startIndex;
	private int pageSize;
	private String keyWords;
	private String sort;
	private Integer categorId;

	public boolean isPage() {
		return isPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getCategorId() {
		return categorId;
	}

	public void setCategorId(Integer categorId) {
		this.categorId = categorId;
	}

	public void setPage(boolean isPage) {
		this.isPage = isPage;
	}

	public void openPage(Integer startIndex,Integer pageSize){
		this.isPage=true;
		
			this.startIndex=startIndex;
			this.pageSize=pageSize;
		
		
	}
}
