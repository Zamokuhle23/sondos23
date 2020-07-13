package jdbc;
import java.sql.*;
public class Main {

	public static void main(String[] args) throws ClassNotFoundException {

		
	   String URL = "jdbc:postgresql://localhost:5432/postgres";
	   String userName = "postgres";
	   String password = "Sondos23";
	   String query = "select * from public.students where id = 3";
	   
	   try {
	   
       Class.forName("org.postgresql.Driver");
      
		Connection con = DriverManager.getConnection(URL,userName,password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		rs.next();
		
		String name = rs.getString("name");
		System.out.println(name);
		st.close();
		con.close();
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}

	}

}
