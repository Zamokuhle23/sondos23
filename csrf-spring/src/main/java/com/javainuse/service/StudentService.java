package com.javainuse.service;

import java.util.List;

import com.javainuse.model.Student;

public interface StudentService {
	void insertStudent(Student stud);
	void insertStudents(List<Student> students);
	List<Student> getAllStudents();
	void getStudentById(String stud);
}