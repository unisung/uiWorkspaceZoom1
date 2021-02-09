package ch06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//JDBC 2.0 - scrollable
public class ScrollableEx2 {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "madang";
		String pwd = "madang";
		String sql="select bookid, bookname,publisher,price from book order by bookid";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd);
			//������ü ����
			PreparedStatement pstmt=con.prepareStatement(sql,//������
					         ResultSet.TYPE_SCROLL_SENSITIVE,//������ �ݿ�     
					         ResultSet.CONCUR_UPDATABLE);//��������
           //��� ó��
		   ResultSet rs = pstmt.executeQuery();
		   System.out.println(" ---------�ܹ��� next()---------------");
		   while(rs.next()) {
			   int i=0;
			   System.out.println(rs.getInt(++i)+","+rs.getString(++i)+","+rs.getString(++i)+","+rs.getInt(++i));
		   }
		   System.out.println(" ---------�ܹ��� previous()---------------");
		   while(rs.previous()) {
			   int i=0;
			   System.out.println(rs.getInt(++i)+","+rs.getString(++i)+","+rs.getString(++i)+","+rs.getInt(++i));
		   }
		   System.out.println(" ---------ù��°�� first()---------------");
		     rs.first();
			   int i=0;
			   System.out.println(rs.getInt(++i)+","+rs.getString(++i)+","+rs.getString(++i)+","+rs.getInt(++i));
		  
			   System.out.println(" ---------ù��°�� last()---------------");
			  rs.last();
			   i=0;
			 System.out.println(rs.getInt(++i)+","+rs.getString(++i)+","+rs.getString(++i)+","+rs.getInt(++i));
			
			 System.out.println(" ---------Ư���� absolute(���ȣ)---------------");
			 rs.absolute(3);
			 i=0;
			System.out.println(rs.getInt(++i)+","+rs.getString(++i)+","+rs.getString(++i)+","+rs.getInt(++i));
				
					   	   
		} catch (Exception e) {
			System.out.println("����:" + e.getMessage());
		}
	}

}
