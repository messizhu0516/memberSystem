package com.zhuqifeng.model.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zhuqifeng.commons.utils.base.JsonUtils;
import com.zhuqifeng.model.base.BaseEntity;

/**
 * @author ZhuQiFeng
 * @addDate 2017年6月26日下午3:24:40
 * @description 数据字典实体
 */
@TableName("sys_dict")
public class Dict extends BaseEntity {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;

	/** 上级ID **/
	@TableField("parent_id")
	private Long parentId;

	/** 字典名称 **/
	private String name;

	/** 字典值 **/
	private String value;

	/** 字典标志 **/
	@TableField("mark_key")
	private String markKey;
	/** 字典排序 **/
	private Integer sort;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMarkKey() {
		return markKey;
	}

	public void setMarkKey(String markKey) {
		this.markKey = markKey;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
