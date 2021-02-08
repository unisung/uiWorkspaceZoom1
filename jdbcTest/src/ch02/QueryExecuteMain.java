package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/* SQL���� �� ��� */
public class QueryExecuteMain {
	public static void main(String[] args) {
		Connection con=null;
		try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("����̹� �ε� ����");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "madang", "madang");
			    Statement stmt = con.createStatement();

			    String sql = "update customer set email='kim@naver.com' where custid=2";

			    boolean isResult = stmt.execute(sql);

			    if(isResult) {//select�� ���
			    	 ResultSet rs = stmt.getResultSet();
			    	 while(rs.next()) {
			    		  System.out.println("id:"+rs.getInt(1));
			    	 }
			    }else {//insert/update/delete�� ���
			    	int rowCount = stmt.getUpdateCount();//������ ���� ��
			    	System.out.println("rowCount:"+ rowCount);
			    }
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			try {
			con.close();//3.��������
			}catch(Exception e) {}
		}
	}
}