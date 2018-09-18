package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.Node;
import com.jt.sys.entry.SysMenu;

public interface SysMenuDao {

	/*
	 * 查询所有菜单以及菜单对应的上级菜单信息
	 */
	List<Map<String,Object>> findObjects();
	/*
	 * 统计此菜单对应的子菜单的个数
	 */
	int getChildCount(@Param("id")Integer id);
	/**
	 * 基于菜单ID删除
	 * @param id
	 * @return
	 */
	int deleteObject(@Param("id")Integer id);
	
	List<Node> findZtreeMenuNodes();
	int insertObject(SysMenu entity);
	int updateObject(SysMenu entity);
}
