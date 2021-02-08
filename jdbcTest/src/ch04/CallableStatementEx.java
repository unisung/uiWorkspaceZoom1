package ch04;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/* stored procedure 실행
 * prepareCall("{call 프로시져명(매개변수1?,매개변수2?,...}") */
public class CallableStatementEx {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
		  //procedure실행객체 생성
			//SQL> exec insertbook(99,'야구의신','대한미디어',25000);
			CallableStatement cstmt = con.prepareCall("{call insertbook(?,?,?,?)}");
			//바인딩변수(?)에 값 설정
			cstmt.setInt(1,98);
			cstmt.setString(2,"야구의야신");
			cstmt.setString(3, "한미디어");
			cstmt.setInt(4, 25000);
           //프로시져 실행
			cstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("오류:"+e.getMessage());
		}
	}
}
