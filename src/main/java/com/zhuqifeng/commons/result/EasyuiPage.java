package com.zhuqifeng.commons.result;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ClassName: EasyuiPage <br/>
 * Function: easyui的分页实体对象，因为字段命名与后端mybatis-plugs不同，为了方便处理字段，用了此桥接对象 <br/>
 * Reason: TODO <br/>
 * date: 2018年5月29日 下午1:16:12 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @param <T>
 * @since JDK 1.8
 */
public class EasyuiPage<T> implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;

	private final static int PAGESIZE = 10; // 默认显示的记录数

	private Integer total; // 总记录
	private List<T> rows; // 显示的记录
	@JsonIgnore
	private Integer from;
	@JsonIgnore
	private Integer size;
	@JsonIgnore
	private Integer nowpage; // 当前页
	@JsonIgnore
	private Integer pagesize;// 每页显示的记录数
	@JsonIgnore
	private String sortName;// 排序字段
	@JsonIgnore
	private String order;// asc，desc mybatis Order 关键字

	public EasyuiPage() {
	}

	// 构造方法
	public EasyuiPage(int nowpage, int pagesize) {
		// 计算当前页
		if (nowpage < 0) {
			this.nowpage = 1;
		} else {
			// 当前页
			this.nowpage = nowpage;
		}
		// 记录每页显示的记录数
		if (pagesize < 0) {
			this.pagesize = PAGESIZE;
		} else {
			this.pagesize = pagesize;
		}
		// 计算开始的记录和结束的记录
		this.from = (this.nowpage - 1) * this.pagesize;
		this.size = this.pagesize;
	}

	// 构造方法
	public EasyuiPage(int nowpage, int pagesize, String sort, String order) {
		// 计算当前页
		if (nowpage < 0) {
			this.nowpage = 1;
		} else {
			// 当前页
			this.nowpage = nowpage;
		}
		// 记录每页显示的记录数
		if (pagesize < 0) {
			this.pagesize = PAGESIZE;
		} else {
			this.pagesize = pagesize;
		}
		// 计算开始的记录和结束的记录
		this.from = (this.nowpage - 1) * this.pagesize;
		this.size = this.pagesize;
		// 排序字段，正序还是反序
		this.sortName = sort;
		this.order = order;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getNowpage() {
		if (nowpage == null || nowpage.intValue() == 0) {
			nowpage = 1;
		}
		return nowpage;
	}

	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}

	public Integer getPagesize() {
		if (pagesize == null || pagesize.intValue() == 0) {
			pagesize = PAGESIZE;
		}
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
