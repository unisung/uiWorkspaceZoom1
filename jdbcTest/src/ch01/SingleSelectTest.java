package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// �Ѱ� �� ��ȸ 
public class SingleSelectTest {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql="select name,address,phone from customer where custid=1";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    Statement stmt = con.createStatement();
		    System.out.println("������: "+sql);
		    ResultSet rs=stmt.executeQuery(sql);
			//�Ѱ� ������ ��� ó��
		   if(rs.next()) {
		    	String name=rs.getString("name");
		    	String address=rs.getString("address");
		    	String phone=rs.getString("phone");
		    	System.out.println("�̸�: "+name+",�ּ�:"+address+",����ó:"+phone);
		   }
		}catch(Exception e) {
			System.out.println("����:"+e.getMessage());
		}

	}

}
