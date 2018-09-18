package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.service.SysUserService;
import com.jt.sys.vo.SysUserDeptResult;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

   @Override
	public PageObject<SysUserDeptResult> findPageObjects(String username, 
			Integer pageCurrent) {
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
		throw new ServiceException("参数不合法");
//2.依据条件获取总记录数
		int rowCount=sysUserDao.getRowCount(username);
        if(rowCount==0)
		throw new ServiceException("记录不存在");
		//3.计算startIndex的值
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		//4.依据条件获取当前页数据
		List<SysUserDeptResult> records=
			sysUserDao.findPageObjects(
		username, startIndex, pageSize);
		//5.封装数据
		PageObject<SysUserDeptResult> pageObject=new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount-1)/pageSize+1);
		return pageObject;
	}
   @Override
	public int validById(Integer id,Integer valid,String modifiedUser) {
		if(id==null||id<=0)
		throw new ServiceException("参数不合法,id="+id);
		if(valid!=1&&valid!=0)
		throw new ServiceException("参数不合法,valie="+valid);
		if(StringUtils.isEmpty(modifiedUser))
		throw new ServiceException("修改用户不能为空");
		int rows=0;
		try{
	    rows=sysUserDao.validById(id, valid, modifiedUser);
		}catch(Throwable e){
		e.printStackTrace();
		throw new ServiceException("底层正在维护");
		}
		if(rows==0)
		throw new ServiceException("此记录可能已经不存在");
		return rows;
	}
}
