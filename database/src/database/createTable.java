package database;

import java.sql.Connection;
import java.sql.Statement;

public class createTable {

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement = null;
		 Main obj = new Main();
		 connection = obj.getConnection();
		 
		 try {
			
			 String query = "create table pupils(st_no SERIAL primary key,name varchar(200),lastName varchar(200))";
			 statement = connection.createStatement();
			 statement.executeUpdate(query);
			 System.out.println("Finished");
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
