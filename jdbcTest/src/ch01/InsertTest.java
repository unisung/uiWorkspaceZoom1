package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertTest {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql="insert into orders(orderid,custid,bookid,saleprice,orderdate) "
				     + " values (11,1,3,20000,sysdate)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    Statement stmt = con.createStatement();
		    System.out.println("������: "+sql);
		    //�Է�ó�� 
		    int result = stmt.executeUpdate(sql);//���ϰ��� ����ó���� ��(row) ��
		    System.out.println("ó���� ��(row)��:"+result);
		    if(result>0) 
		    	System.out.println("�Է¿Ϸ�!");
		    else
		    	System.out.println("�Է½���!");
		}catch(Exception e) {
			System.out.println("�����߻�: "+e.getMessage());
		}
	}
}
