package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/* preparedStatement */
public class PreParedStatementEx {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql="select c.custid id, name, orderid,b.bookname, saleprice, publisher "
				+	" from orders o, customer c, book b "
					+" where o.custid=c.custid "
					+" and o.bookid=b.bookid "
					+" and c.name like ? "
					+" and b.bookname like ? " 
					+" and o.saleprice<? ";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    //PreparedStatement객체 생성
		    PreparedStatement pstmt = con.prepareStatement(sql);//prepareStatement(쿼리문)
		    System.out.println("쿼리문:"+sql);
		    String name="박%";
		    String bookname="축구%";
		    int saleprice=15000;
		    
		    //바인딩(?) 변수에 값 설정하기 
		    pstmt.setString(1,name);
		    pstmt.setString(2, bookname);
		    pstmt.setInt(3, saleprice);
		    
		    ResultSet rs = pstmt.executeQuery();//executeQuery()
		    while(rs.next()) {
		    	System.out.println(rs.getInt(1)+","+rs.getString(2)+","
		                                  +rs.getInt(3)+","+rs.getString(4)+","+rs.getInt(5)+","+rs.getString(6));
		    }
		    
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
