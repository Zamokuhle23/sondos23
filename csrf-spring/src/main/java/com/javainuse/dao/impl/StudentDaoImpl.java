package com.javainuse.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.javainuse.dao.StudentDao;
import com.javainuse.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends JdbcDaoSupport implements StudentDao {
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public void insertStudent(Student stud) {
		String sql = "INSERT INTO student " +
				"(studId, studName) VALUES (?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{
				stud.getStudId(), stud.getStudName()
		});
	}



	@Override
	public void insertStudents(final List<Student> students) {
		String sql = "INSERT INTO student " + "(studId, studName) VALUES (?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Student student = students.get(i);
				ps.setString(1, student.getStudId());
				ps.setString(2, student.getStudName());
			}
			
			public int getBatchSize() {
				return students.size();
			}
		});

	}
	@Override
	public List<Student> getAllStudents(){
		String sql = "SELECT * FROM student";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<Student> result = new ArrayList<Student>();
		for(Map<String, Object> row:rows){
			Student stud = new Student();
			stud.setStudId((String)row.get("studId"));
			stud.setStudName((String)row.get("studName"));
			result.add(stud);
		}
		
		return result;
	}

	@Override
	public Student getStudentById(String studId) {
		String sql = "SELECT * FROM student WHERE studId = ?";
		return (Student)getJdbcTemplate().queryForObject(sql, new Object[]{studId}, new RowMapper<Student>(){
			@Override
			public Student mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Student stud = new Student();
				stud.setStudId(rs.getString("studId"));
				stud.setStudName(rs.getString("studName"));
				return stud;
			}
		});
	}
}