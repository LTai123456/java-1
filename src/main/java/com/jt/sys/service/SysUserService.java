package com.jt.sys.service;

import java.util.List;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.sys.vo.SysUserDeptResult;

public interface SysUserService {
	PageObject<SysUserDeptResult> findPageObjects(
			String username,
			Integer pageCurrent);
	public int validById(Integer id,
			Integer valid,
			String modifiedUser);
}
