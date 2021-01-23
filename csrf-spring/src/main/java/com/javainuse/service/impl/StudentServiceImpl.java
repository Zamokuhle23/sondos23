package com.javainuse.service.impl;

import java.util.List;

import com.javainuse.dao.StudentDao;
import com.javainuse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.model.Student;


@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDao studentDao;

	@Override
	public void insertStudent(Student student) {
		studentDao.insertStudent(student);
	}

	@Override
	public void insertStudents(List<Student> students) {
		studentDao.insertStudents(students);
	}

	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Override
	public void getStudentById(String empId) {
		Student student = studentDao.getStudentById(empId);
		System.out.println(student);
	}

}