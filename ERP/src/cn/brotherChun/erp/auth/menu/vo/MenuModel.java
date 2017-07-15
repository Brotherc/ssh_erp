package cn.brotherChun.erp.auth.menu.vo;

import java.util.Set;

public class MenuModel {
	
	public static final Long MENU_SYSTEM_MENU_UUID=1L;
	
	private Long uuid;
	private String name;
	private String url;
	private MenuModel parent;
	
	private Set<MenuModel> children;
	
	public MenuModel() {}

	public MenuModel(Long uuid, String name, String url, MenuModel parent) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.url = url;
		this.parent = parent;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MenuModel getParent() {
		return parent;
	}

	public void setParent(MenuModel parent) {
		this.parent = parent;
	}

	public Set<MenuModel> getChildren() {
		return children;
	}

	public void setChildren(Set<MenuModel> children) {
		this.children = children;
	}

}
