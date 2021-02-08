package ch01;

import java.sql.Connection;
import java.sql.DriverManager;

/* java와 oracle dbms와 연결 테스트 프로그램 */
public class ConnectionTest {
	public static void main(String[] args) {
		//dbms연결객체 선언
		Connection con=null;
		try {
			    //1.ojdbc6.jar에서 Oracle연결드라이버 를 로딩
			    Class.forName("oracle.jdbc.driver.OracleDriver");//드라이버 경로
			    System.out.println("드라이버 로드 성공");
			    //2.dbms와 연결하기
			    //프로토콜:서브프로토콜:드라이버종류:서버경로:port번호:SID 
			    //jdbc:oracle:thin:@localhost:1521:xe
			    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
			    		                                              "madang", "madang");//url,id,pwd
			    if(con!=null)
			    	 System.out.println("연결성공!");
			    else
			    	System.out.println("연결실패!");
		}catch(Exception e) {
			System.out.println("오류 발생: "+e.getMessage());
		}finally {
			try {
			con.close();//3.연결해제
			}catch(Exception e) {}
		}
	}
}
