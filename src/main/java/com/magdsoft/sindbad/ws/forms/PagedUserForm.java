package com.magdsoft.sindbad.ws.forms;

public class PagedUserForm {
	private Integer id;
	private Integer page;
	
	public Integer getId() {
		return id;
	}
	public Integer getPage() {
		if (page == null || page == 0) {
			return 1;
		}
		return page;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
}
