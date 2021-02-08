package ch04;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/* stored procedure ����
 * prepareCall("{call ���ν�����(�Ű�����1?,�Ű�����2?,...}") */
public class CallableStatementEx {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
		  //procedure���ఴü ����
			//SQL> exec insertbook(99,'�߱��ǽ�','���ѹ̵��',25000);
			CallableStatement cstmt = con.prepareCall("{call insertbook(?,?,?,?)}");
			//���ε�����(?)�� �� ����
			cstmt.setInt(1,98);
			cstmt.setString(2,"�߱��Ǿ߽�");
			cstmt.setString(3, "�ѹ̵��");
			cstmt.setInt(4, 25000);
           //���ν��� ����
			cstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("����:"+e.getMessage());
		}
	}
}
