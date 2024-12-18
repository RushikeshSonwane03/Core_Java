import java.sql.*;
import java.util.Scanner;

public class Snapchat_CommandLine_App {

	public static void main(String[] args) throws Exception{

		System.out.print("\n |-|-|-|-|-|-|-| SNAPCHAT |-|-|-|-|-|-|-| \n");
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con;
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jdbc_database_xampp",
				"root",
				"");

		System.out.print("\n ------- DataBase Connected Successfully ------- \n");
		
		Scanner s1 = new Scanner(System.in);
		
		int choice, ans;
		boolean user=true;
		do {
			
			System.out.print("\n 1. User Registration \n 2. User Login \n 3. Forgot Password \n 4. Exit \n");
			System.out.print("Enter Your Choice :- ");
			choice = s1.nextInt();
			
			switch(choice) {
			case 1: 
				System.out.print("\n ------------ User Registration ------------ \n");
				String username, user_email, user_password, user_c_password;			
				System.out.print("Enter Username :- ");
				username = s1.next();
				System.out.print("Enter Email :- ");
				user_email = s1.next();
				System.out.print("Enter Password :- ");
				user_password = s1.next();
				System.out.print("Confirm Password:- ");
				user_c_password = s1.next();
					
				if(user_password.equals(user_c_password)) {
					PreparedStatement ps = con.prepareStatement(
							"INSERT INTO Snapchat (sname, semail, spass, c_spass) "
								+ "values ('"+ username +"','"+ user_email +"','"+ user_password +"','"+ user_c_password +"');");
						ps.executeUpdate();
						
					System.out.print("\n ------------ User Record Saved ------------ \n");	
				}
				else {
					System.out.println("Confirm Password Does not match Password.");	
				}
				System.out.print("\n ----- Do you want to continue (1. Yes , 2. No) ? ----- \n");
				ans = s1.nextInt();
				if (ans == 0) { user = false; }
				break;
				
			case 2:
				System.out.print("\n ------------ User Login ------------ \n");
				
				String ip_username, ip_user_password;
						
				System.out.print("Enter Username :- ");
				ip_username = s1.next();
				System.out.print("Enter Password :- ");
				ip_user_password = s1.next();
				
	//			System.out.print(ip_username +","+ ip_user_password );
				
				PreparedStatement ps1 = con.prepareStatement("SELECT * FROM Snapchat WHERE sname = '"+ ip_username +"' AND spass = '"+ ip_user_password +"';");
				ResultSet rs = ps1.executeQuery();
						
				if(rs.next()) {
					System.out.print("\n ------------ Log In Successfull ------------ \n");
					System.out.println("User Id -> " + rs.getInt(1));
					System.out.println("UserName -> " + rs.getString(2));
					System.out.println("User Email -> " + rs.getString(3));
					System.out.println("User Password -> " + rs.getString(4));
					System.out.println("User Confirmed Password -> " + rs.getString(5));
				}
				else {
					System.out.print("\n ------------ Log In Failed (Wrong Credentials) ------------ \n\n");
				}
				System.out.print("\n ----- Do you want to continue (1. Yes , 2. No) ? ----- \n");
				ans = s1.nextInt();
				if (ans == 0) { user = false; }
				break;
			
			case 3: 
				System.out.print("\n ------------ Forgot Password ------------ \n");
				
				String forgot_username, forgot_email, new_password, new_c_password;
						
				System.out.print("Enter Username :- ");
				forgot_username = s1.next();
				System.out.print("Enter User Email :- ");
				forgot_email = s1.next();
				
//				System.out.print(forgot_username +","+ forgot_email );
				
				PreparedStatement ps2 = con.prepareStatement("SELECT * FROM Snapchat WHERE sname = '"+ forgot_username +"' AND semail = '"+ forgot_email +"';");
				ResultSet rs1 = ps2.executeQuery();
						
				if(rs1.next()) {
					System.out.print("\n ------------ User Exists ------------ \n");
					System.out.print("Enter New Password :- ");
					new_password = s1.next();
					System.out.print("Confirm New Password :- ");
					new_c_password = s1.next();
					
//					System.out.print(new_password +","+ new_c_password );
					
					PreparedStatement ps3 = con.prepareStatement("UPDATE Snapchat SET "
							+ "spass = '"+ new_password +"' , c_spass = '"+ new_c_password 
							+ "' WHERE sname = '"+ forgot_username +"' AND semail = '"+ forgot_email +"';");
					ps3.executeUpdate();
					PreparedStatement ps4 = con.prepareStatement("SELECT * FROM Snapchat WHERE "
							+ "sname = '"+ forgot_username +"' AND semail = '"+ forgot_email +"';");
					ResultSet rs2 = ps4.executeQuery();
					
					while(rs2.next()) {
						System.out.println("User Id -> " + rs2.getInt(1));
						System.out.println("UserName -> " + rs2.getString(2));
						System.out.println("User Email -> " + rs2.getString(3));
						System.out.println("New User Password -> " + rs2.getString(4));
						System.out.println("New User Confirmed Password -> " + rs2.getString(5));
					}
				}
				else {
					System.out.print("\n ------------ Failed : User Not Exists ------------ \n");
				}
				System.out.print("\n ----- Do you want to continue (1. Yes , 2. No) ? ----- \n");
				ans = s1.nextInt();
				if (ans == 0) { user = false; }
				break;
				
			case 4: user = false; break; 
			default : System.out.print("\n ------------ Wrong Choice ------------ \n");
			System.out.print("\n ----- Do you want to continue (1. Yes , 2. No) ? ----- \n");
			ans = s1.nextInt();
			if (ans == 0) { user = false; }
						
			}
			
		}while(user);

		System.out.print("\n ------------ Thank You !!! ------------ \n");			
		s1.close();
	}
}








//Create Table Snapchat(sid int AUTO_INCREMENT PRIMARY KEY, sname varchar(100), semail varchar(100), spass varchar(10), c_spass varchar(10));