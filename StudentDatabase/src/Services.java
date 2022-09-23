

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.Scanner;

public class Services {


public static final Connection con = null;

class StudentDao{
		Statement st=null;
		PreparedStatement pst=null;
		ResultSet rs=null;

		static{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/studentdb";
			String  user = "root";
			String password = "rps@12345";
			Connection con = DriverManager.getConnection(url, user, password);
			}

	public List<Student>  getAllStudents(){

		List<Student> students = new ArrayList<>();
	
	  st = con.createStatement();
	  rs = st.executeQuery("select * from students");
	  while(rs.next()){
			Student s = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			students.add(s);
	}
	}
	  

	public String insertStudent(Student s){
		String sql = "insert into students values(?,?,?,?)";
		pst = con.prepareStatement(sql);
		pst.setInt(1, s.getId());
		pst.setString(2, s.getName());
		pst.setInt(3, s.getMrk1());
		pst.setInt(4, s.getMrk2());
		int k = pst.executeUpdate();	
		if(k==1)
			return "record inserted";
		else
			return "record not inserted";

	}
	}
		

}



public class StudentService{

Student dao;


public StudentService(){
	dao= new StudentDao();

}
	
public List<Student> displayAllRecords(){

	return dao.getAllStudents();


}



public static void main(){


		StudentService service = new StudentService();

		List<Student> students = service.displayAllRecords();
		students.forEach(System.out::println);

}