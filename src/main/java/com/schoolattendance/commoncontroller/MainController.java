package com.schoolattendance.commoncontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolattendance.repository.DivisionRepository;
import com.schoolattendance.service.AttendanceService;
import com.schoolattendance.service.AttendanceServiceImpl;
import com.schoolattendance.service.ClassServiceImpl;
import com.schoolattendance.service.DivisionServiceImpl;
import com.schoolattendance.service.RoleServiceImpl;
import com.schoolattendance.service.UserServiceImpl;

@RestController
@RequestMapping(value = "/attendance")
public class MainController {

	@Autowired
	AttendanceServiceImpl attendanceService;

	@Autowired
	ClassServiceImpl classService;

	@Autowired
	DivisionServiceImpl divisionService;

	@Autowired
	RoleServiceImpl roleService;

	@Autowired
	UserServiceImpl userService;

	public String login(HttpSession session) {

		String email = session.getAttribute("email").toString();
		String password = session.getAttribute("password").toString();

		userService.login(email, password);
		session.setAttribute("email", email);
		session.setAttribute("password", password);

		return "index";
	}

}
