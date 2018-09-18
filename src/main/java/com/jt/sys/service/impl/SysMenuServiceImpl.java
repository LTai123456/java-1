package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.Node;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.entry.SysMenu;
import com.jt.sys.service.SysMenuService;
@Service
public class SysMenuServiceImpl  implements SysMenuService{

	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public List<Map<String, Object>> findObjects() {
		return sysMenuDao.findObjects();
	}
	@Override
	public int deleteObject(Integer id) {
		// TODO Auto-generated method stub
		if(id==null||id<1){
			throw new IllegalArgumentException("参数无效");
		}
		int count=sysMenuDao.getChildCount(id);
		if(count>0){
			throw new ServiceException("请先删除子菜单");
		}
		int rows=sysMenuDao.deleteObject(id);
		if(rows==0){
			throw new ServiceException("该记录不存在");
		}
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		return rows;
	}
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}
	@Override
	public int saveObject(SysMenu entity) {
		// TODO Auto-generated method stub
		if(entity==null){
			throw new IllegalArgumentException("保存对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getPermission())){
			throw new IllegalArgumentException("权限标识对象不能为空");
		}
		int rows=0;
		try{
			rows=sysMenuDao.insertObject(entity);
			}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("保存失败");
			}
		
		return rows;
	}
	@Override
	public int updateObject(SysMenu entity) {
		// TODO Auto-generated method stub
		if(entity==null){
			throw new IllegalArgumentException("修改对象不能为空");
		}
		if(StringUtils.isEmpty(entity.getPermission())){
			throw new IllegalArgumentException("权限标识对象不能为空");
		}
		int rows=0;
		try{
			rows=sysMenuDao.updateObject(entity);
			}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("修改失败");
			}
		return rows;
	}
}
