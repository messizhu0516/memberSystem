package com.zhuqifeng.controller.common;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhuqifeng.commons.shiro.captcha.FlySkyCaptcha;
import com.zhuqifeng.model.dto.CourseDTO;
import com.zhuqifeng.model.dto.CourseDTO.StudentDTO;
import com.zhuqifeng.model.dto.CourseDTO.TeacherDTO;

import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 * ClassName: IgnoreController <br/>
 * Function: 处理非拦截URL的请求 <br/>
 * Reason: TODO <br/>
 * date: 2018年4月24日 上午10:08:54 <br/>
 * 
 * @author QiFeng.Zhu
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/ignore")
public class IgnoreController extends BaseController {
	@Autowired
	private FlySkyCaptcha flySkyCaptcha;

	@GetMapping("/test")
	public void index(HttpServletResponse response) throws Exception {
		List<CourseDTO> list = new LinkedList<CourseDTO>();
		for (int j = 0; j < 100; j++) {
			CourseDTO e = new CourseDTO();
			e.setId("1");
			e.setName("ddd");
			Date d = new Date();
			List<StudentDTO> l = new LinkedList<StudentDTO>();
			for (int i = 0; i < 10; i++) {
				StudentDTO s = new StudentDTO();
				s.setBirthday(d);
				s.setName("" + i);
				s.setSex(0);
				s.setRegistrationDate(d);
				l.add(s);
			}
			e.setStudents(l);
			TeacherDTO mathTeacher = new TeacherDTO();
			mathTeacher.setName("asdfasdf");
			e.setMathTeacher(mathTeacher);
			list.add(e);
		}
		super.exportExcel(new ExportParams("标题名称", "sheet名称"), CourseDTO.class, list, response);
	}

	/**
	 * 图标页
	 */
	@GetMapping("icons.html")
	public String icons() {
		return "icons";
	}

	/**
	 * 图形验证码
	 */
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletRequest request, HttpServletResponse response) {
		this.flySkyCaptcha.generate(request, response);
	}

	@GetMapping("ipnotAllow")
	public String ipnotAllow(HttpServletRequest request, HttpServletResponse response) {
		return "error/ipnotAllow";
	}

	@GetMapping("thirdOauth")
	public String thirdOauth(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	@GetMapping("/interview/main")
	public String interviewMain() {
		return "interview/main";
	}
	
	@GetMapping("/interview/{category}/{pagenum}")
	public String interviewSub(@PathVariable("category") String category, @PathVariable("pagenum") String pagenum) {
		return "interview/" + category + "/" + pagenum;
	}

}
