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
			//callable객체 생성
			CallableStatement cstmt=con.prepareCall("{call AveragePrice2(?,?)}");
		    //바인딩변수 설정
			cstmt.registerOutParameter(1, Types.DOUBLE);//출력용 설정
			cstmt.setString(2, "대한미디어");
			//프로시져 실행
			cstmt.execute();
			//결과 값 얻기
			double avg = cstmt.getDouble(1);
			
			System.out.println("평균:"+avg);
			
		}catch(Exception e) {
			System.out.println("오류:"+e.getMessage());
		}
	}
}
