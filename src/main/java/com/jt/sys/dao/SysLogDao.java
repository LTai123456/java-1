package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entry.SysLog;
/**
 * @Param是mybatis中修饰参数中的一个注解，当dao方法中参数有多个参数时，建议使用此注解
 * 在映射文件中可以使用#{username}获取参数的值
 * 如果不使用此注解，在映射文件中要使用#{0}#{1}或者{param1}来获取参数
 * @author lt
 *
 */
public interface SysLogDao {
	List<SysLog> findPageObjects(
		@Param("username")String username,
		@Param("startIndex")Integer startIndex,
		@Param("pageSize")Integer pageSize
			);
	int getRowCount(@Param("username")String username);
	
	int deleteObjects(@Param("ids")Integer...ids);
	 
}
