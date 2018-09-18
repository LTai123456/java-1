package com.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysLogDao;
import com.jt.sys.entry.SysLog;
import com.jt.sys.service.SysLogService;


public class TestSysDao extends TestBase{
	

	@Test
	public void doMethod01() {
		
		SysLogDao obj = ctx.getBean("sysLogDao", SysLogDao.class);
		System.out.println("--------------"+obj.getRowCount("admin"));
		List<SysLog> list = obj.findPageObjects("admin",0,10);
		
		for (SysLog sysLog : list) {
			System.out.println(sysLog);
		}
		obj.deleteObjects(42,44);
	}
	@Test
	public void doMethod02(){
		SysLogService obj = ctx.getBean("sysLogServiceImpl", SysLogService.class);
		PageObject<SysLog> findPageObjects = obj.findPageObjects("admin", 1);
		System.out.println(findPageObjects);
	}
}
