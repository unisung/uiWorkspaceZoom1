package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InsertTest {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql="insert into orders(orderid,custid,bookid,saleprice,orderdate) "
				     + " values (?,?,?,?,sysdate)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    System.out.println("������: "+sql);
		    //�� ���ε� ó��
		    pstmt.setInt(1,15);
		    pstmt.setInt(2, 1);
		    pstmt.setInt(3, 3);
		    pstmt.setInt(4,20000);
		    
		    //�Է�ó�� 
		    int result = pstmt.executeUpdate();//���ϰ��� ����ó���� ��(row) ��
		    
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
