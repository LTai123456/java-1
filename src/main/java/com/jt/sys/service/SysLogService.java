package com.jt.sys.service;

import com.jt.common.vo.PageObject;
import com.jt.sys.entry.SysLog;

public interface SysLogService {
	PageObject<SysLog> findPageObjects(
			String username,
			Integer pageCurrent
			);
	int deleteObjects(Integer...ids);
}
