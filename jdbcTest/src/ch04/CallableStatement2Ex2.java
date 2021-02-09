package ch04;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class CallableStatement2Ex2 {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
			//callable��ü ����
			CallableStatement cstmt=con.prepareCall("{call AveragePrice2(?,?)}");
		    //���ε����� ����
			cstmt.registerOutParameter(1, Types.DOUBLE);//��¿� ����
			cstmt.setString(2, "���ѹ̵��");
			//���ν��� ����
			cstmt.execute();
			//��� �� ���
			double avg = cstmt.getDouble(1);
			
			System.out.println("���:"+avg);
			
		}catch(Exception e) {
			System.out.println("����:"+e.getMessage());
		}
	}
}
