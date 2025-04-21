package database_creation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Update {
	private static final String driver ="com.mysql.cj.jdbc.Driver";
	private static final String url ="jdbc:mysql://localhost:3306/amazon";
	private static final String username ="root";
	private static final String password = "Chandu@768";
	private static PreparedStatement pmst;
	private static Connection conn;
	public static void main(String[] args) {
		try {
			Scanner sc= new Scanner(System.in);
			Class.forName(driver);
			conn=DriverManager.getConnection(url,username,password);
			String sql = "update login set id=?,name=?,email=? where id=?";
			pmst=conn.prepareStatement(sql);
			System.out.println("Enter  update  id:");
			pmst.setInt(1, sc.nextInt());
			System.out.println("enter  name:");
			pmst.setString(2, sc.next());
			System.out.println("enter email:");
			pmst.setString(3, sc.next());
			System.out.println("Enter   id:");
			pmst.setInt(4, sc.nextInt());
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