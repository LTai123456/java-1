package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.service.SysLogService;
@Controller
@RequestMapping("/log/")
public class SysLogController {
	@Autowired
	private  SysLogService sysLogService;
	@RequestMapping(value="doLogListtUI")
	public String doLogListtUI(
			String username,
			Integer pageCurrent
			){
		return "sys/log_list";
	}
	@RequestMapping(value="doFindPageObjects",produces="application/json; charset=UTF-8" )
	@ResponseBody
	public JsonResult doFindPageObjects(
			String username,
			Integer pageCurrent){
		return new JsonResult(sysLogService.findPageObjects(username, pageCurrent));
	}
	@RequestMapping(value="doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(Integer...ids){
		sysLogService.deleteObjects(ids);
		return new JsonResult("delete ok");
	}
}
