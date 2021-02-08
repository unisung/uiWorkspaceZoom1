package ch04;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

// �Լ� ȣ�� �� ��� �� �ޱ� {?= call function(?)}//ù��°? registerOutputParameter,�ι�°? setxxx
public class CallableStatment3Ex {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
			//�Լ����� ��ü ����
			CallableStatement cstmt = con.prepareCall("{?= call fnc_interest(?) }");
			
			//ȣ�� - ��/Ÿ�� ���ε�
			cstmt.registerOutParameter(1, Types.INTEGER);//(�ε���, Ÿ��)
			cstmt.setInt(2, 50000);
			
			//���ν��� ����
			cstmt.execute();
			
			//���ν���(�Լ�) ���� �� ��� �� ���
			int interest = cstmt.getInt(1);
			
			System.out.println("���:"+interest);
			
		  //procedure���ఴü ����
		}catch(Exception e) {
			System.out.println("����:"+e.getMessage());
			e.getStackTrace();
		}
	}
}
