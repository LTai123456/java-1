package com.jt.sys.service.impl;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysRoleDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entry.SysRole;
import com.jt.sys.service.SysRoleService;
import com.jt.sys.vo.SysRoleMenuReult;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		// TODO Auto-generated method stub
		// 参数验证
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("当前页参数不能小于1");
		}
		// 查询总记录数
		int rowCount = sysRoleDao.getRowCount(name);
		if (rowCount == 0) {
			throw new ServiceException("没有该用户");
		}
		// 查询当前页数数据
		int pageSize = 3;
		int startIndex = (pageCurrent - 1) * pageSize;

		List<SysRole> findPageObjects = sysRoleDao.findPageObjects(name, startIndex, pageSize);
		// 封装数据
		PageObject<SysRole> pageObject = new PageObject<SysRole>();
		pageObject.setRecords(findPageObjects);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);
		// 返回数据
		return pageObject;
	}
	@Override
	public int deleteObject(Integer id) {
		// TODO Auto-generated method stub
		if (id == null || id < 1)
			throw new ServiceException("id的值不正确");
		int rows = sysRoleDao.deleteObject(id);
		if (rows == 0)
			throw new ServiceException("数据可能已经不存在");
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		return rows;
	}
	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		if (entity == null)
			throw new ServiceException("保存数据不能为空");
		if (StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名不能为空");
		if (StringUtils.isEmpty(menuIds))
			throw new ServiceException("必须为角色赋予权限");
		int rows = sysRoleDao.insertObject(entity);
		System.out.println(entity.getId());
		try {
			sysRoleMenuDao.insertObject(entity.getId(), menuIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public SysRoleMenuReult findObjectById(Integer id) {

		if (id == null || id <= 0)
			throw new ServiceException("id的值不合法");

		SysRoleMenuReult role=null;
		try {
			role=sysRoleDao.findObjectById(id);
			System.out.println(role);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if (role == null)
			throw new ServiceException("此记录已经不存在");
		return role;
	}

	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		System.out.println(Arrays.toString(menuIds));
		if (entity == null)
			throw new ServiceException("更新的对象不能为空");
		if (entity.getId() == null)
			throw new ServiceException("id的值不能为空");
		if (StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名不能为空");
		if (menuIds == null || menuIds.length == 0)
			throw new ServiceException("必须为角色指定一个权限");
		int rows = sysRoleDao.updateObject(entity);
		if (rows == 0)
			throw new ServiceException("对象可能已经不存在");
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		sysRoleMenuDao.insertObject(entity.getId(), menuIds);
		return rows;
	}
	@Override
	public int checkRoleByName(String name) {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name)){
			throw new IllegalArgumentException("名称不能为空");
		}
		int counts = sysRoleDao.checkRoleByName(name);
		if(counts!=0){
			throw new ServiceException("名称重复");
		}
		return counts;
	}
	@Override
    public List<CheckBox> findObjects() {
     	return sysRoleDao.findObjects();
    }

}
