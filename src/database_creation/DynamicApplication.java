package database_creation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

public class DynamicApplication {
	private static final String driver ="com.mysql.cj.jdbc.Driver";
	private static final String username ="root";
	private static final String password = "Chandu@768";
	private static PreparedStatement pmst;
	private static Connection conn;
	public static void main(String[] args) {
		int choice;
		do {
			Scanner src = new Scanner(System.in);
		
			System.out.println("choose your choice");
			DisplayMenu();
			choice = Integer.parseInt(src.next());
			switch(choice) {
			case 1:
				createdatabase();
				break;
		    case 2:
				dropdatabase();
				break;
		    case 3:
				datainsertion();
				break;
		    case 4:
				deletebyid();
				break;
		    case 5:
				updatedata();
				break;
		    case 6:
				getbyid();
				break;
		    case 7:
				getall();
				break;
		    case 8:
		    	login();
		    	break;
		    case 9:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option");
			}	
		} while (choice>0);
	}

	private static void login() {
	       try {
	            Scanner src = new Scanner(System.in);
	            Class.forName(driver);
	            System.out.println("Enter database name:");
	            String url ="jdbc:mysql://localhost:3306/" + src.next();
	            conn = DriverManager.getConnection(url, username, password);
	            System.out.println("Enter table name:");
	            String table = src.next();
	            String sql = "select order_name from " + table + " where order_id = ? and order_address=?";
	            pmst = conn.prepareStatement(sql);
	            System.out.println("Enter order id:");
	            pmst.setInt(1, src.nextInt());
	            System.out.println("Enter order address:");
	            pmst.setString(2, src.next());
	            ResultSet rs = pmst.executeQuery();
	            if(rs.next()) {
	                    System.out.print(rs.getString("order_name"));
	                }
	            else {
	            	System.out.println("item not found");
	            }
	            System.out.println();
	            conn.close();
	            pmst.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		
	}

	private static void getall() {
        try {
            Scanner src = new Scanner(System.in);
            Class.forName(driver);
            System.out.println("Enter database name:");
            String url ="jdbc:mysql://localhost:3306/" + src.next();
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Enter table name:");
            String sql = "select * from " + src.next();
            pmst = conn.prepareStatement(sql);
            ResultSet rs = pmst.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberofcolumns = rsmd.getColumnCount();
            
            while (rs.next()) {
                for (int i = 1; i <= numberofcolumns; i++) {
                    System.out.print(rsmd.getColumnName(i) + ": " + rs.getString(i) + "  ");
                }
                System.out.println();
            }
            conn.close();
            pmst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
		

	private static void getbyid() {
        try {
            Scanner src = new Scanner(System.in);
            Class.forName(driver);
            System.out.println("Enter database name:");
            String url ="jdbc:mysql://localhost:3306/" + src.next();
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Enter table name:");
            String table = src.next();
            System.out.println("Enter order id:");
            long order_id = src.nextLong();
            String sql = "select * from " + table + " where order_id = ?";
            pmst = conn.prepareStatement(sql);
            pmst.setInt(1, (int) order_id);
            ResultSet rs = pmst.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberofcolumns  = rsmd.getColumnCount();
            
            if (rs.next()) {
                for (int i = 1; i <= numberofcolumns; i++) {
                    System.out.print(rsmd.getColumnName(i) + ": " + rs.getString(i) + "  ");
                }
                System.out.println();
            } else {
                System.out.println("No record found with the given email.");
            }
            
            conn.close();
            pmst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	private static void updatedata() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url ="jdbc:mysql://localhost:3306/"+ src.next();
			conn=DriverManager.getConnection(url,username,password);
			System.out.println("enter table name:");
			String sql = "update " + src.next() + " set order_name=?,order_pincode=?,order_address =? where order_id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter name");
			pmst.setString(1, src.next());
			System.out.println("enter pincode");
			pmst.setInt(2, src.nextInt());
			System.out.println("enter address");
			pmst.setString(3, src.next());
			System.out.println("enter order id");
			pmst.setLong(4, src.nextLong());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("table is updated sucessfully");
			}else {
				System.out.println("table is not updated"); 
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void deletebyid() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url ="jdbc:mysql://localhost:3306/"+ src.next();
			conn=DriverManager.getConnection(url,username,password);
			System.out.println("enter table name:");
			String sql = "delete from " + src.next() + " where order_id = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter order id");
			pmst.setLong(1, src.nextLong());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("data is deleted");
			}else {
				System.out.println("data is not deleted "); 
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void datainsertion() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("enter database name:");
			String url ="jdbc:mysql://localhost:3306/"+ src.next();
			conn=DriverManager.getConnection(url,username,password);
			System.out.println("enter table name:");
			String sql = "insert into " + src.next() + " (order_id,order_name,order_pincode,order_address)values(?,?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter order id");
			pmst.setLong(1, src.nextLong());
			System.out.println("enter order name");
			pmst.setString(2, src.next());
			System.out.println("enter pincode");
			pmst.setInt(3, src.nextInt());
			System.out.println("enter address");
			pmst.setString(4, src.next());
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("data is inserted sucessfully");
			}else {
				System.out.println("data is  not inserted"); 
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void dropdatabase() {
		try {
			Class.forName(driver);
			String url ="jdbc:mysql://localhost:3306/";
			conn=DriverManager.getConnection(url,username,password);
			System.out.println("enter database name:");
			Scanner src = new Scanner(System.in);
			String sql = "drop database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i == 0) {
				System.out.println("database is  dropped");
			}else {
				System.out.println("database is not dropped"); 
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void createdatabase() {
		try {
			Class.forName(driver);
			String url ="jdbc:mysql://localhost:3306/";
			conn=DriverManager.getConnection(url,username,password);
			System.out.println("enter database name:");
			Scanner src = new Scanner(System.in);
			String sql = "create database " + src.next();
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				System.out.println("database is  created sucessfully");
			}else {
				System.out.println("database is not created"); 
			}
			conn.close();
			pmst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void DisplayMenu() {
		System.out.println("\t1.create database");
		System.out.println("\t2.drop database");
		System.out.println("\t3.data insertion");
		System.out.println("\t4.data deletion by id");
		System.out.println("\t5.update  data");
		System.out.println("\t6.get by id");
		System.out.println("\t7.get all ");
		System.out.println("\t8.login");
		System.out.println("\t9.exit");
		
	}

}
