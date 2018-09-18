package com.jt.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entry.SysRole;
import com.jt.sys.service.SysRoleService;

@Controller
@RequestMapping("/role/")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("doRoleListUI")
	public String doRoleListUI() {
		return "sys/role_list";
	}

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindObjectById(String name, Integer pageCurrent) {
		PageObject<SysRole> findPageObjects = sysRoleService.findPageObjects(name, pageCurrent);
		return new JsonResult(findPageObjects);
	}
 
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		
		return new JsonResult(sysRoleService.findObjectById(id));
	}

	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		sysRoleService.deleteObject(id);
		return new JsonResult("delete Ok");
	}

	@RequestMapping("doRoleEditUI")
	public String doRoleEditUI() {
		return "sys/role_edit";
	}

	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysRole entity, Integer[] menuIds) {

		int saveObject = sysRoleService.saveObject(entity, menuIds);
		System.out.println(saveObject);
		return new JsonResult("save ok");
	}

	@RequestMapping("doUpdateObject")
	 @ResponseBody
	 public JsonResult doUpdateObject(SysRole entity,
			  Integer[] menuIds){
		  System.out.println(Arrays.toString(menuIds));
		  sysRoleService.updateObject(entity, menuIds);
	 return new JsonResult("update ok");
	 }
	 @RequestMapping("doCheckRoleByName")
	 @ResponseBody
	 public JsonResult doCheckRoleByName(String name){
		  sysRoleService.checkRoleByName(name);
	 return new JsonResult("check ok");
	 }
	
}
