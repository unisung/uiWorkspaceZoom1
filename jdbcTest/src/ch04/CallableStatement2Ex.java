package ch04;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class CallableStatement2Ex {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
		  //procedure���ఴü ����
		   CallableStatement cstmt = con.prepareCall("{call AveragePrice(?)}");// ��� �Ķ����
		 //��� �Ķ���͸� �ޱ�-registerOutputParameter(���ε����� ����,Types.Ÿ��);
		   cstmt.registerOutParameter(1, Types.DOUBLE);
		   
			//callablestatement����
		   cstmt.execute();
		   //�� ��� cstmt.getŸ��(�ε�����ȣ);
		   double result = cstmt.getDouble(1);
		   System.out.println("���:"+result);
		   
		}catch(Exception e) {
			System.out.println("����:"+e.getMessage());
		}
		
	}
}
