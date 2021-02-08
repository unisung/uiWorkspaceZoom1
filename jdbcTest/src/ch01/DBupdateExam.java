package ch01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class DBupdateExam {
	static {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) { e.getStackTrace();}
	}
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql = "";
  try(Connection con=DriverManager.getConnection(url, id, pwd);
		Statement stmt = con.createStatement();
		  Scanner scanner = new Scanner(System.in);
		){
	    //리스트 출력
	  ResultSet rs=stmt.executeQuery("select * from customer");
	  while(rs.next()) {
		  System.out.println(rs.getInt(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5));
	  }
	  con.setAutoCommit(false);
	  //수정
	  System.out.println("수정할 고객번호>");
	  int custid=scanner.nextInt();
	  System.out.println("수정할 고객주소> ");
	  String address=scanner.next();
	  System.out.println("수정할 email> ");
	  String email=scanner.next();
	  
	  int result = stmt.executeUpdate("update customer set address='"+address
			                                          +"', email='"+email+"' where custid="+custid); 
	 if(result>0) con.commit();
	 else con.rollback();
	 
	 //결과 출력
	 rs =stmt.executeQuery("select * from customer where custid ="+custid);
	 if(rs.next()) {
		 System.out.println(rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(5));
	 }
	 
	 //자동commit으로 되돌리기
	con.setAutoCommit(true);
  }catch(Exception e) {
	  e.getStackTrace();
  }
	}
}
