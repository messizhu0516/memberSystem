package com.zhuqifeng.model.base;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * @author ZhuQiFeng
 * @addDate 2017年6月26日下午3:24:40
 * @description 表字段实体映射基类
 */
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -1802638623864168448L;
	/**
	 * 数据创建者
	 */
	private Long creator;
	/**
	 * 数据修改者
	 */
	private Long modifier;
	/** 数据创建时间 **/
	@TableField(value = "create_time")
	private Date createTime;
	/** 数据修改日期 **/
	@TableField(value = "modify_time")
	private Date modifyTime;

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Long getModifier() {
		return modifier;
	}

	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
