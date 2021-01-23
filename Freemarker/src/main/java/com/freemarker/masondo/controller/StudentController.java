package com.freemarker.masondo.controller;


import com.freemarker.masondo.model.Student;
import com.freemarker.masondo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

@Autowired
    private StudentRepository studentRepository;

@GetMapping("/")
    public String home(Model model){
    return "home";
}

    @GetMapping("/form")
    public String index() {
        return "form";
    }

    @PostMapping("/add")
public String add(@Valid Student student) {
    studentRepository.save(new Student(student.getFirstName(), student.getLastName(), student.getEmail()));

 return "redirect:/showStudents";
}

@GetMapping("/showStudents")
    public ModelAndView showStudents(){
        List<Student> students = studentRepository.findAll();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("students",students);

        return new ModelAndView("showStudents",params);
            }



}
