package sondos;

import java.sql.*;
import java.util.*;

public class StudentRep implements StudentsRepository {
	
	private static final String SQL_SELECT_BY_ID = "select * from student where id = ";
	private static final String SQL_SELECT_BY_AGE = "select * from student where age = ";
	
	
	private Connection connection;

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findById(Long id) {
Statement st = null;
ResultSet rs = null;
 
    try {
    	st = connection.createStatement();
		rs = st.executeQuery(SQL_SELECT_BY_ID + id);
		
		if(rs.next()) {
			return new Student(
					rs.getLong("id"),
					rs.getString("firstName"),
					rs.getString("lastname"),
					rs.getInt("age"),
					rs.getInt("groupNumber")
					);
			
		} else return null;
	} catch (SQLException e) {
		throw new IllegalArgumentException(e);
	} finally {
		if(rs != null) {
		try {
			rs.close();
		} catch (SQLException e) {}
		}
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	}
	

	@Override
	public void save(Student entity) {
		PreparedStatement pst = null;
		connection = null;
		
		
		try {
			pst = connection.prepareStatement("insert into student (firt_name,last_name,age,group_number,id,mentor)" +
			                                   "values(?,?,?,?,?");
			pst.setString(1, entity.getFirstName());
			pst.setString(2, entity.getLastName());
			pst.setInt(3, entity.getAge());
			pst.setInt(4, entity.getGroup());
			pst.setLong(5, entity.getId());
			pst.setArray(6, (Array) entity.getMentors());
			
			
			pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(Student entity) {
		PreparedStatement st = null;
	
		connection = null;
		 
		try {
			
			st =  connection.prepareStatement("update student set first_name = ?,last_name = ? ,age = ?, group_number = ? where id = ?");
			st.setString(1, entity.getFirstName());
			st.setString(2, entity.getLastName());
			st.setInt(3,entity.getAge());
			st.setInt(4, entity.getGroup());
			st.setLong(5, entity.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Student> findByAge(int age) {
		List<Student> student = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.createStatement();
			rs = st.executeQuery(SQL_SELECT_BY_AGE + age);
			if(rs.next()) {
				student.add(new Student(rs.getLong("id"), 
						                rs.getString("firstName"),
                                        rs.getString("lastName"),
                                        rs.getInt("age"),
                                        rs.getInt("groupNumber")));
				return student;
			} else return null;
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		} finally {
			
		       if(rs != null) {
		    	   try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       }
		       if(st != null) {
		    	   try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       }
		}
		
		
		
	}

}
