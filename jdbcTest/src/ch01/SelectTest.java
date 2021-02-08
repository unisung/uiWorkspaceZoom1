package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/* 1.연결 -> 2. 쿼리 실행 ->3. 결과 처리 ->4. 자원해제 */
public class SelectTest {
	public static void main(String[] args) {
	 //연결객체 생성
	Connection con=null;
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String id="madang";
	String pwd="madang";
	String sql="select bookid, bookname, publisher, price from book";
	try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");//1.로딩
		    con=DriverManager.getConnection(url, id, pwd);//2.연결객체 얻기
		    //3.쿼리객체 생성
		    Statement stmt = con.createStatement();
		    //4.쿼리실행 후 결과 얻기
		    ResultSet rs = stmt.executeQuery(sql);//select는 executeQuery
		    //5.결과 처리 
		    while(rs.next()) {
		    	int bookid = rs.getInt(1);
		    	String bookName=rs.getString(2);
		    	String publisher=rs.getString(3);
		    	int price = rs.getInt(4);
		    	System.out.println(bookid+"|"+bookName+"|"+publisher+"|"+price);
		    }
	}catch(Exception e) {
		System.out.println("오류발생: "+e.getMessage());
	}finally {
		try {
			con.close();//6.자원해제
		}catch(Exception e) {}
	}
	}
}
