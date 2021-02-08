package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InsertTest {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql="insert into orders(orderid,custid,bookid,saleprice,orderdate) "
				     + " values (?,?,?,?,sysdate)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    System.out.println("쿼리문: "+sql);
		    //값 바인딩 처리
		    pstmt.setInt(1,15);
		    pstmt.setInt(2, 1);
		    pstmt.setInt(3, 3);
		    pstmt.setInt(4,20000);
		    
		    //입력처리 
		    int result = pstmt.executeUpdate();//리턴값은 정상처리된 행(row) 수
		    
		    System.out.println("처리된 행(row)수:"+result);
		    if(result>0) 
		    	System.out.println("입력완료!");
		    else
		    	System.out.println("입력실패!");
		}catch(Exception e) {
			System.out.println("오류발생: "+e.getMessage());
		}
	}
}
