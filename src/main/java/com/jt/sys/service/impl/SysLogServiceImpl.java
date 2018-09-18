package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entry.SysLog;
import com.jt.sys.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;
	public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
		//合法性校验
		//查询总记录数
		//查询当前
		//封装数据
		//返回数据
		if (pageCurrent == null || pageCurrent < 1) {
			throw new ServiceException("当前页码不合格");
		}
		int rowCount = sysLogDao.getRowCount(username);
		if(rowCount==0){
			throw new ServiceException("没有该用户的记录");
		}
		
		int pageSize = 5;
		int startindex = (pageCurrent - 1) * pageSize;
		// 2.
		List<SysLog> records = sysLogDao.findPageObjects(username, startindex, pageSize);
		PageObject<SysLog> pgvo = new PageObject<>();
		pgvo.setRecords(records);
		pgvo.setPageSize(pageSize);
		pgvo.setRowCount(rowCount);
		pgvo.setPageCurrent(pageCurrent);
		pgvo.setPageCount((rowCount-1)/pageSize+1);
		return pgvo;
	}
	@Override
	public int deleteObjects(Integer... ids) {
		if(ids==null||ids.length==0){
			throw new IllegalArgumentException("请先选中记录");
		}
		int rows=0;
		try{
			rows=sysLogDao.deleteObjects(ids);
		}catch(Throwable e){
			e.printStackTrace();
			throw new ServiceException(e);
		}
		if(rows==0){
			throw new ServiceException("记录可能已经不存在");
		}
		return rows;	
	}
}
