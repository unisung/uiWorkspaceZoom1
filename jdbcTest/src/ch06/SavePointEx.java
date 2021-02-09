package ch06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

public class SavePointEx {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "madang";
		String pwd = "madang";
		String sql="update book set price=price*2 where bookid in (4,5,6) ";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd);
			//������ü ����
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);//���� commitó��
			
			Savepoint save1 = con.setSavepoint();//savePoint
			//update ó��
			pstmt.executeUpdate();
			//��� ��ȸ 
			pstmt=con.prepareStatement("select bookid, price from book where bookid in ( 4,5,6)");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("savepoint1 ����");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getInt(2));
			}
			
			Savepoint save2 = con.setSavepoint();//savePoint
			//update ó��
			pstmt=con.prepareStatement("delete from book where bookid=4");
			pstmt.executeUpdate();
			//��� ��ȸ 
			pstmt=con.prepareStatement("select bookid, price from book where bookid in ( 4,5,6)");
			 rs = pstmt.executeQuery();
			 System.out.println("savepoint2 ����");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getInt(2));
			}
			
			//savepoint���� �ǵ�����
		 con.rollback(save2);
		 System.out.println("savepoint2 �������� �ǵ��� ��");
			
		//��� ��ȸ 
			pstmt=con.prepareStatement("select bookid, price from book where bookid in ( 4,5,6)");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getInt(2));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
