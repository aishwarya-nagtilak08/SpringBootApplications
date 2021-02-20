package com.schoolattendance.commoncontroller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.schoolattendance.models.Role;
import com.schoolattendance.models.User;
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

	public String register(JsonNode node) {
		JsonNode userName = node.get("userName");
		JsonNode firstName = node.get("firstName");
		JsonNode lastName = node.get("lastName");
		JsonNode email = node.get("email");
		JsonNode department = node.get("department");
		JsonNode roleId = node.get("roleId");

		User user = new User();
		user.setUserName(userName.asText());
		user.setFirstName(firstName.asText());
		user.setLastName(lastName.asText());
		user.setEmail(email.asText());
		user.setDepartment(department.asText());

		Role role = roleService.findById(roleId.asLong());

		user.setRole(role);
		userService.save(user);

		return "dashboard";
	}

	public String update(JsonNode node) {
		JsonNode userName = node.get("userName");
		JsonNode firstName = node.get("firstName");
		JsonNode lastName = node.get("lastName");
		JsonNode email = node.get("email");
		JsonNode department = node.get("department");
		JsonNode roleId = node.get("roleId");
		JsonNode userId = node.get("userId");

		User user = userService.findById(userId.asLong());

		user.setUserName(userName.asText());
		user.setFirstName(firstName.asText());
		user.setLastName(lastName.asText());
		user.setEmail(email.asText());
		user.setDepartment(department.asText());

		Role role = roleService.findById(roleId.asLong());
		if (!user.getRole().equals(role)) {
			roleService.delete(role.getId());
		}
		user.setRole(role);

		userService.save(user);
		return "dashboard";
	}

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		String message = "timepass";
		model.put("message", message);
		return "welcome";
	}

}
