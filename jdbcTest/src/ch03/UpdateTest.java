package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
/* commit��� autoCommit */
public class UpdateTest {
	public static void main(String[] args) {
		//��ĳ�� ����
		Scanner scanner = new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql = "";
		try {
			System.out.println("������ ����ȣ: ");
			int custid= scanner.nextInt();
			System.out.println("������ ��ȭ��ȣ:");
			String phone = scanner.next();
			System.out.println("������ email:");
			String email = scanner.next();
			sql="update customer set phone=?,email=? where custid=?";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
			//PreparedStatement
			PreparedStatement pstmt = con.prepareStatement(sql);
			//���ε� ���� ������� ��(����) ����
			pstmt.setString(1,phone);
			pstmt.setString(2,email);
			pstmt.setInt(3, custid);
			
			System.out.println("������: " + sql);
			
			int result = pstmt.executeUpdate();
			System.out.println("ó���� ��(row)��: " + result);
			if(result>0)
				System.out.println("���� ����");
			else
				System.out.println("���� ����");
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
}
