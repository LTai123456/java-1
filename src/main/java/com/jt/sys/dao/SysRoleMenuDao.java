package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuDao {

	int deleteObjectsByMenuId(@Param("menuId")Integer menuId);
	int deleteObjectsByRoleId(Integer roleId);
	int insertObject(
			@Param("roleId")Integer roleId, 
			@Param("menuIds")Integer[] menuIds);
	List<Integer> findMenuIdsByRoleId(Integer roleId);
	
}
