package ch06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//JDBC 2.0 - scrollable
public class ScrollableEx {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "madang";
		String pwd = "madang";
		String sql="select * from customer order by custid";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd);
			//������ü ����
			PreparedStatement pstmt=con.prepareStatement(sql,//������
					         ResultSet.TYPE_SCROLL_SENSITIVE,//������ �ݿ�     
					         ResultSet.CONCUR_UPDATABLE);//��������
           //��� ó��
		   ResultSet rs = pstmt.executeQuery();
		   while(rs.next()) {
			   System.out.println("name:"+rs.getString("name")+",address:"+rs.getString("address"));
		   }
		   System.out.println("---- ������ ��� -------");
		   //������ ���
		   while(rs.previous()) {
			   System.out.println("name:"+rs.getString("name")+",address:"+rs.getString("address"));
		   }
		   // ��°���� ������ ������ �̵�
		   System.out.println("---- ���������� ��� �� -----");
		   rs.last();
		   System.out.println("name:"+rs.getString("name")+",address:"+rs.getString("address"));
		   
		   //��°���� ù������ �̵�
		   System.out.println("---- ù���� ��� �� -----");
		   rs.first();
		   System.out.println("name:"+rs.getString("name")+",address:"+rs.getString("address"));
		   //��°���� Ư�� ������ �̵�
		   System.out.println("---- 3���� ��� �� -----");
		   rs.absolute(3);
		   System.out.println("name:"+rs.getString("name")+",address:"+rs.getString("address"));
		   
		} catch (Exception e) {
			System.out.println("����:" + e.getMessage());
		}
	}

}
