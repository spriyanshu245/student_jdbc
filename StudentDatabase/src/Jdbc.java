
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;
		int id;
		String name;
		String strname;
		int marks1;
		int marks2;
		int marks3;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "rps@12345");
			System.out.println("Connected !!");

			Statement st = con.createStatement();
			do {
				System.out.println("\n");
				System.out.println("***** STUDENTS RECORD MENU *****");
				System.out.println("1] INSERT STUDENT RECORD");
				System.out.println("2] UPDATE STUDENT RECORD");
				System.out.println("3] DELETE STUDENT RECORD");
				System.out.println("4] VIEW STUDENT RECORDS");
				System.out.println("5] EXIT");
				System.out.println("ENTER YOUR CHOICE");
				
				choice = sc.nextInt();
				switch(choice)
				{
				case 1:
					System.out.println("ENTER STUDENT	ID	NAME	MARKS1	MARKS2	MARKS3");
					id = sc.nextInt();
					ResultSet rc = st.executeQuery("select * from studentInfo");
					while(rc.next())
					{
						int checkid = rc.getInt(1);
						while(checkid == id)
						{
							System.out.println("STUDENT ID :"+id+" already present, please use new ID");
							id = sc.nextInt();
						}
					}
					name = sc.next();
					sc.nextLine();
					marks1 = sc.nextInt();
					marks2 = sc.nextInt();
					marks3 = sc.nextInt();					
					strname = "'"+name+"'";
					st.executeUpdate("insert into studentInfo value(" +id +","+ strname +","+ marks1 +","+ marks2 +","+ marks3+")");
					System.out.println("RECORD SUCCESSFULLY ADDED !");
					break;
					
				case 2:
					System.out.println("ENTER STUDENT ID TO UPDATE");
					id = sc.nextInt();
					ResultSet ru = st.executeQuery("select * from studentInfo");
					int[] checkid = new int[1000];
					while(ru.next())
					{
						for(int i=0; i < checkid.length; i++) {
							checkid[i] = ru.getInt(1);
						}
					}
					
					for(int i=0; i < checkid.length; i++) {
						if(checkid[i] != id)
						{
							System.out.println("STUDENT ID :"+id+" is not present, please use existing ID");
							id = sc.nextInt();
						}
					}
					System.out.println("UPDATE STUDENT	NAME	MARKS1	MARKS2	MARKS3");
					name = sc.next();
					sc.nextLine();
					marks1 = sc.nextInt();
					marks2 = sc.nextInt();
					marks3 = sc.nextInt();					
					strname = "'"+name+"'";
					st.executeUpdate("update studentInfo set name="+ strname +", marks1="+ marks1 +", marks2="+ marks2 +", marks3="+ marks3+" "+"where id="+id);
					System.out.println("RECORD SUCCESSFULLY UPDATED !");
					break;
					
				case 3:
					System.out.println("ENTER STUDENT ID TO REMOVE ");
					id = sc.nextInt();
					st.executeUpdate("delete from studentInfo where id ="+id);
					System.out.println("RECORD SUCCESSFULLY REMOVED !");
					break;
					
				case 4:
					ResultSet r = st.executeQuery("select * from studentInfo");
					System.out.println("ID\t"+"NAME\t"+"MARKS 1\t"+"MARKS 2\t"+"MARKS 3");
					while(r.next())
					{
						System.out.println(r.getInt(1)+"\t"+ r.getString(2)+"\t"+ r.getInt(3)+"\t"+ r.getInt(4)+"\t"+ r.getInt(5));
					}
					break;
				}
				
			}
			while (choice != 5);
			{
				System.out.println(" CLOSED !");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}