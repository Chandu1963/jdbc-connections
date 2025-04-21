package database_creation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Insertion {
	private static final String driver ="com.mysql.cj.jdbc.Driver";
	private static final String url ="jdbc:mysql://localhost:3306/movies";
	private static final String username ="root";
	private static final String password = "Chandu@768";
	private static PreparedStatement pmst;
	private static Connection conn;
	public static void main(String[] args) {
		try {
			Scanner sc= new Scanner(System.in);
			Class.forName(driver);
			conn=DriverManager.getConnection(url,username,password);
			String sql = "insert into actors (actor_id,name,dob,gender,nationality,debut_year) values(?,?,?,?,?,?) ";
			pmst=conn.prepareStatement(sql);
			System.out.println("Enter actor_id:");
			pmst.setInt(1, sc.nextInt());
			System.out.println("enter name:");
			pmst.setString(2, sc.next());
			System.out.println("enter dob:");
			pmst.setString(3, sc.next());
			System.out.println("enter gender:");
			pmst.setString(4, sc.next());
			System.out.println("enter nationality:");
			pmst.setString(5, sc.next());
			System.out.println("enter debut year");
			pmst.setInt(6, sc.nextInt());
			int i= pmst.executeUpdate();
			if (i>0) {
				System.out.println("data insterted");
			}else {
				System.out.println("not inserted");
			}
			pmst.close();
			conn.close();
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}

}
