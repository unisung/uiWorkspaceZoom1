package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/* SQL문을 모를 경우 */
public class QueryExecuteMain {
	public static void main(String[] args) {
		Connection con=null;
		try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("드라이버 로드 성공");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "madang", "madang");
			    Statement stmt = con.createStatement();

			    String sql = "update customer set email='kim@naver.com' where custid=2";

			    boolean isResult = stmt.execute(sql);

			    if(isResult) {//select인 경우
			    	 ResultSet rs = stmt.getResultSet();
			    	 while(rs.next()) {
			    		  System.out.println("id:"+rs.getInt(1));
			    	 }
			    }else {//insert/update/delete인 경우
			    	int rowCount = stmt.getUpdateCount();//수정된 행의 수
			    	System.out.println("rowCount:"+ rowCount);
			    }
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			try {
			con.close();//3.연결해제
			}catch(Exception e) {}
		}
	}
}