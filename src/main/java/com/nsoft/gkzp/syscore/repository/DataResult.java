package com.nsoft.gkzp.syscore.repository;

import java.util.List;
import com.nsoft.gkzp.syscore.repository.AjaxDataEntity;

/**
 * 针对返回列表查询的多行数据
 * @author zdyang
 * @date 2019.08.29
 */
public class DataResult extends AjaxDataEntity
{
	private long total = 0;// 总行数
	private List rows = null;// 查询数据
	private List footer = null;// 汇总数据
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public List getFooter() {
		return footer;
	}
	public void setFooter(List footer) {
		this.footer = footer;
	}
}
