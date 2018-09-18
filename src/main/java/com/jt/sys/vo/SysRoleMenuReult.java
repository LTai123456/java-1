package com.jt.sys.vo;

import java.util.List;

import com.jt.sys.entry.SysRole;

public class SysRoleMenuReult {

	@Override
	public String toString() {
		return "SysRoleMenuReult [role=" + role + ", menuIds=" + menuIds + "]";
	}
	private SysRole role;
	private List<Integer> menuIds;
	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}
	public List<Integer> getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}
}
