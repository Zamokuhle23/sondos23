package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
	public Connection getConnection() {
		
		
		
		Connection connection = null;
		String host = "localhost";
		String port = "5432";
		String username = "postgres";
		String db_name = "sondos24";
		String password = "Sondos23";
		
		try {
			
	Class.forName("org.postgresql.Driver");
	connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Sondos23");
	
	if(connection !=null) {
		System.out.println("Connection is ok");
	}else
		System.out.println("Connection Failed");
	
} catch (Exception e) {
	System.out.println("Not Found");
}		
		return connection;	
	}

	public static void main(String[] args) {

    Main obj = new Main();
    System.out.println(obj.getConnection());

	}

}
