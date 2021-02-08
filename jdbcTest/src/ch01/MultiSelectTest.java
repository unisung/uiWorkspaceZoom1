package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/* ´ÙÁßÇà Á¶È¸ */
public class MultiSelectTest {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql = "select o.custid id,max(name) name, sum(saleprice) total"
				      + " from orders o, customer c "
				      + " where o.custid=c.custid "
				      + " group by o.custid ";
		try {
			    Class.forName("oracle.jdbc.driver.OracleDriver");
			    Connection con=DriverManager.getConnection(url, id, pwd);
			    Statement stmt = con.createStatement();
			    System.out.println("Äõ¸®¹®: "+sql);
			    ResultSet rs=stmt.executeQuery(sql);
			    while(rs.next()) {
			    	int custid=rs.getInt("id");//Ä®·³¸í
			    	String name=rs.getString("name");//Ä®·³¸í
			    	int total=rs.getInt(3);//Ä®·³ ¼ø¹ø
			    	System.out.println("id:"+custid+",ÀÌ¸§:"+name+",ÃÑ±¸¸Å:"+total);
			    }
		}catch(Exception e) {
			System.out.println("¿À·ù:"+e.getMessage());
		}
	}
}