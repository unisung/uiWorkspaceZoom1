package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
			sql="update customer set phone='"+phone+"',email='" +email+"' where custid="+custid;
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
			Statement stmt = con.createStatement();
			System.out.println("������: " + sql);
			
			int result = stmt.executeUpdate(sql);
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
