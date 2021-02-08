package ch04;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

// 함수 호출 후 결과 값 받기 {?= call function(?)}//첫번째? registerOutputParameter,두번째? setxxx
public class CallableStatment3Ex {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
			//함수실행 객체 생성
			CallableStatement cstmt = con.prepareCall("{?= call fnc_interest(?) }");
			
			//호출 - 값/타입 바인딩
			cstmt.registerOutParameter(1, Types.INTEGER);//(인덱스, 타입)
			cstmt.setInt(2, 50000);
			
			//프로시져 실행
			cstmt.execute();
			
			//프로시져(함수) 실행 후 결과 값 얻기
			int interest = cstmt.getInt(1);
			
			System.out.println("결과:"+interest);
			
		  //procedure실행객체 생성
		}catch(Exception e) {
			System.out.println("오류:"+e.getMessage());
			e.getStackTrace();
		}
	}
}
