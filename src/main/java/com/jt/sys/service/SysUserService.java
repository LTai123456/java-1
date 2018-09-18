package com.jt.sys.service;

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
