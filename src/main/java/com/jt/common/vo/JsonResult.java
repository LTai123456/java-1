package com.jt.common.vo;

/**
 * 此对象封装控制层数据
 * @author lt
 *
 */
public class JsonResult {

	private int state=1;
	private String message="ok";
	//正确数据
	private Object data;	
	public JsonResult() {
	}
	public JsonResult(Object data) {
		this.data = data;
	}
	public JsonResult(String message) {
		this.message = message;
	}
	public JsonResult(Throwable e) {
		this.state=0;
		this.message = e.getMessage();
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
