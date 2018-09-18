package com.jt.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class PageController {
	/**
	 * 
	 * @return
	 */
	@RequestMapping("doIndexUI")
	public String doIndexUI(){
		return "starter";//starter.html
	}//自己写视图解析器
	/*
	 * 提取分页工具
	 */
	@RequestMapping("doPageUI")
	public String doPageUI(){
		return "common/page";
	}
}




