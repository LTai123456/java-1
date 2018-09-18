package com.jt.common.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 负责封装业务层信息
 * @author lt
 *
 * @param <T>
 */
public class PageObject<T> implements Serializable{
	private static final long serialVersionUID = 3361603153415782373L;
	private List<T> records;
	private int rowCount;
	private int pageCount;
	private int pageSize=3;
	private int pageCurrent=1;
	public List<T> getRecords(){
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	@Override
	public String toString() {
		return "PageObject [records=" + records + ", rowCount=" + rowCount + ", pageCount=" + pageCount + ", pageSize="
				+ pageSize + ", pageCurrent=" + pageCurrent + "]";
	}
	
}
