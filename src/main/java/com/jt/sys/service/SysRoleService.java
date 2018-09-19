package com.jt.sys.service;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.sys.entry.SysRole;
import com.jt.sys.vo.SysRoleMenuReult;

public interface SysRoleService {
	PageObject<SysRole> findPageObjects(
			String name,
			Integer pageCurrent
			);
	int deleteObject(Integer id);
	int saveObject(SysRole entity,Integer[] menuIds);
	/*Map<String,Object> findObjectById(Integer id);*/
	SysRoleMenuReult findObjectById(Integer id);
	int updateObject(SysRole entity,Integer[] menuIds);
	int checkRoleByName(String name);
	List<CheckBox> findObjects() ;
    

}
