package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

// 한건 만 조회 
public class SingleSelectTest2 {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql="select name,address,phone from customer where custid=1";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    Statement stmt = con.createStatement();
		    System.out.println("쿼리문: "+sql);
		    ResultSet rs=stmt.executeQuery(sql);
			//한건 추출한 결과 처리
		    //메타정보 추출 getMetaData();
		    ResultSetMetaData rsmd = rs.getMetaData();
		    for(int i=1;i<=rsmd.getColumnCount();i++) {
			    System.out.print(rsmd.getColumnName(i)+"\t");
		    }
		    System.out.println("\n--------------------------------------");
		    
		   if(rs.next()) {
		    	String name=rs.getString("name");
		    	String address=rs.getString("address");
		    	String phone=rs.getString("phone");
		    	System.out.println(name+"|"+address+"|"+phone);
		   }
		}catch(Exception e) {
			System.out.println("오류:"+e.getMessage());
		}

	}

}
