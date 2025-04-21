package database_creation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DropDataBase {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url ="jdbc:mysql://localhost:3306/";
	private static final String username ="root";
	private static final String password = "Chandu@768";
	private static PreparedStatement pmst;
	private static Connection conn;
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Enter Database Name");
			String sql = "drop database "+ sc.next();
		    pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if ( i == 0) {
				System.out.println("DataBase is  droped");
			}
			else {
				System.out.println("DataBase is  not droped");
			}
			pmst.close();
			conn.close();
			sc.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
