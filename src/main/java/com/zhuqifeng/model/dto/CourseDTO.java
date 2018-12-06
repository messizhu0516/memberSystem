/**  
 * Project Name:FFJRManagement  
 * File Name:TeacherDTO.java  
 * Package Name:com.ffjr.model.dto  
 * Date:2018年5月7日下午3:59:21  
 * Copyright (c) 2018, zz621@126.com All Rights Reserved.  
 *  
*/

package com.zhuqifeng.model.dto;

import java.util.Date;
import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * ClassName:TeacherDTO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年5月7日 下午3:59:21 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 * @see
 */
@ExcelTarget("courseEntity")
public class CourseDTO implements java.io.Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private String id;
	/** 课程名称 */
	@Excel(name = "课程名称", orderNum = "1", width = 25)
	private String name;
	/** 老师主键 */
	@ExcelEntity(id = "absent")
	private TeacherDTO mathTeacher;

	@ExcelCollection(name = "学生", orderNum = "4")
	private List<StudentDTO> students;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TeacherDTO getMathTeacher() {
		return mathTeacher;
	}

	public void setMathTeacher(TeacherDTO mathTeacher) {
		this.mathTeacher = mathTeacher;
	}

	public List<StudentDTO> getStudents() {
		return students;
	}

	public void setStudents(List<StudentDTO> students) {
		this.students = students;
	}

	/**
	 * ClassName:s <br/>
	 * Function: TODO ADD FUNCTION. <br/>
	 * Reason: TODO ADD REASON. <br/>
	 * Date: 2018年4月26日 下午1:16:24 <br/>
	 * 
	 * @author QiFeng.Zhu
	 * @version 1.0
	 * @since JDK 1.8
	 * @see
	 */
	public static class StudentDTO implements java.io.Serializable {
		/**
		 * id
		 */
		private String id;
		/**
		 * 学生姓名
		 */
		@Excel(name = "学生姓名", height = 20, width = 30, isImportField = "true_st")
		private String name;
		/**
		 * 学生性别
		 */
		@Excel(name = "学生性别", replace = { "男_1", "女_2" }, suffix = "生", isImportField = "true_st")
		private int sex;

		@Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true_st", width = 20)
		private Date birthday;

		@Excel(name = "进校日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd")
		private Date registrationDate;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public Date getRegistrationDate() {
			return registrationDate;
		}

		public void setRegistrationDate(Date registrationDate) {
			this.registrationDate = registrationDate;
		}

	}

	@ExcelTarget("teacherEntity")
	public static class TeacherDTO implements java.io.Serializable {
		private String id;
		/** name */
		@Excel(name = "主讲老师_major,代课老师_absent", orderNum = "1", isImportField = "true_major,true_absent")
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
