package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/* ������ ��ȸ */
public class MultiSelectTest {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql = "select o.custid id,max(name) name, sum(saleprice) total"
				      + " from orders o, customer c "
				      + " where o.custid=c.custid "
				      + " group by o.custid ";
		try {
			    Class.forName("oracle.jdbc.driver.OracleDriver");
			    Connection con=DriverManager.getConnection(url, id, pwd);
			    Statement stmt = con.createStatement();
			    System.out.println("������: "+sql);
			    ResultSet rs=stmt.executeQuery(sql);
			    while(rs.next()) {
			    	int custid=rs.getInt("id");//Į����
			    	String name=rs.getString("name");//Į����
			    	int total=rs.getInt(3);//Į�� ����
			    	System.out.println("id:"+custid+",�̸�:"+name+",�ѱ���:"+total);
			    }
		}catch(Exception e) {
			System.out.println("����:"+e.getMessage());
		}
	}
}