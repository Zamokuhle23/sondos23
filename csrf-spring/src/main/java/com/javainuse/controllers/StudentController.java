package com.javainuse.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javainuse.model.Student;
import com.javainuse.model.UserRegistration;
import com.javainuse.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@RequestMapping("/welcome")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}

	@RequestMapping(value = "/addNewStudent", method = RequestMethod.GET)
	public ModelAndView show() {
		return new ModelAndView("addStudent", "emp", new Student());
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("registration", "user", new UserRegistration());
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegister(@ModelAttribute("user") UserRegistration userRegistrationObject) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		User user = new User(userRegistrationObject.getUsername(), userRegistrationObject.getPassword(), authorities);
		jdbcUserDetailsManager.createUser(user);
		return new ModelAndView("redirect:/welcome");
	}

	@RequestMapping(value = "/addNewStudent", method = RequestMethod.POST)
	public ModelAndView processRequest(@ModelAttribute("emp") Student stud) {

		studentService.insertStudent(stud);
		List<Student> students = studentService.getAllStudents();
		ModelAndView model = new ModelAndView("getStudents");
		model.addObject("students", students);
		return model;
	}

	@RequestMapping("/getStudents")
	public ModelAndView getStudents() {
		List<Student> students = studentService.getAllStudents();
		ModelAndView model = new ModelAndView("getStudents");
		model.addObject("students", students);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}

}
