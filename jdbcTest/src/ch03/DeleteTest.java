package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql = "";
		
	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
			
			con.setAutoCommit(false);//����ó�� - java�� �ڵ�(true)�� default
			
			sql = "select * from orders order by orderid";
			Statement stmt = con.createStatement();
			System.out.println("������: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			//���⸮��Ʈ �����ֱ�
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"|"+rs.getInt(2)+"|"
			    +rs.getInt(3)+"|"+rs.getInt(4)+"|"+rs.getString(5));
			}
			//������ ��ȣ �Է¹ޱ�
			System.out.println("�����Ұŷ���ȣ�� �Է��ϼ���>");
			int orderid=scanner.nextInt();
			sql ="delete from orders where orderid=?";
			System.out.println("������:"+sql);
		    //PreparedStatement��ü ����
			PreparedStatement pstmt = con.prepareStatement(sql);
			//�� ���ε� ó��
			pstmt.setInt(1, orderid);
			//����
			int result = pstmt.executeUpdate();
			System.out.println("ó���� ��(row)��: " + result);
			if(result>0) {
				con.commit();
				System.out.println("�����Ϸ�!");
			}else {
				con.rollback();
				System.out.println("��������!");
			}
			//�ڵ��ݿ�ó��
			con.setAutoCommit(true);
		}catch(Exception e) {
			System.out.println("����:"+e.getMessage());
		}
		
	}

}
