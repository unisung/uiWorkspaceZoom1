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
		  //procedure실행객체 생성
		   CallableStatement cstmt = con.prepareCall("{call AveragePrice(?)}");// 출력 파라미터
		 //출력 파라미터를 받기-registerOutputParameter(바인딩변수 순번,Types.타입);
		   cstmt.registerOutParameter(1, Types.DOUBLE);
		   
			//callablestatement실행
		   cstmt.execute();
		   //값 얻기 cstmt.get타입(인덱스번호);
		   double result = cstmt.getDouble(1);
		   System.out.println("평균:"+result);
		   
		}catch(Exception e) {
			System.out.println("오류:"+e.getMessage());
		}
		
	}
}
