package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entry.SysRole;
import com.jt.sys.vo.SysRoleMenuReult;

public interface SysRoleDao {
	/**
	 * 
	 * 
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysRole> findPageObjects(
				@Param("name")String name,
				@Param("startIndex")Integer startIndex,
				@Param("pageSize")Integer pageSize);
	int getRowCount(@Param("name")String name);
	
	int deleteObject(Integer id);
	int insertObject(SysRole entity);
	SysRoleMenuReult findObjectById(Integer id);
	int updateObject(SysRole entity);
	int checkRoleByName(String name);
}
