package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertTest {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql="insert into orders(orderid,custid,bookid,saleprice,orderdate) "
				     + " values (11,1,3,20000,sysdate)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    Statement stmt = con.createStatement();
		    System.out.println("쿼리문: "+sql);
		    //입력처리 
		    int result = stmt.executeUpdate(sql);//리턴값은 정상처리된 행(row) 수
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
