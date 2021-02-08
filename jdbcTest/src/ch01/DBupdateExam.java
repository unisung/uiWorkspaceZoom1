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
	    //����Ʈ ���
	  ResultSet rs=stmt.executeQuery("select * from customer");
	  while(rs.next()) {
		  System.out.println(rs.getInt(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5));
	  }
	  con.setAutoCommit(false);
	  //����
	  System.out.println("������ ����ȣ>");
	  int custid=scanner.nextInt();
	  System.out.println("������ ���ּ�> ");
	  String address=scanner.next();
	  System.out.println("������ email> ");
	  String email=scanner.next();
	  
	  int result = stmt.executeUpdate("update customer set address='"+address
			                                          +"', email='"+email+"' where custid="+custid); 
	 if(result>0) con.commit();
	 else con.rollback();
	 
	 //��� ���
	 rs =stmt.executeQuery("select * from customer where custid ="+custid);
	 if(rs.next()) {
		 System.out.println(rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(5));
	 }
	 
	 //�ڵ�commit���� �ǵ�����
	con.setAutoCommit(true);
  }catch(Exception e) {
	  e.getStackTrace();
  }
	}
}
