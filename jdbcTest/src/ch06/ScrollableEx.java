package ch06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//JDBC 2.0 - scrollable
public class ScrollableEx {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "madang";
		String pwd = "madang";
		String sql="select * from customer order by custid";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd);
			//쿼리객체 생성
			PreparedStatement pstmt=con.prepareStatement(sql,//쿼리문
					         ResultSet.TYPE_SCROLL_SENSITIVE,//수정분 반영     
					         ResultSet.CONCUR_UPDATABLE);//수정가능
           //결과 처리
		   ResultSet rs = pstmt.executeQuery();
		   while(rs.next()) {
			   System.out.println("name:"+rs.getString("name")+",address:"+rs.getString("address"));
		   }
		   System.out.println("---- 역으로 출력 -------");
		   //역으로 출력
		   while(rs.previous()) {
			   System.out.println("name:"+rs.getString("name")+",address:"+rs.getString("address"));
		   }
		   // 출력결과의 마지막 행으로 이동
		   System.out.println("---- 마지막행의 출력 값 -----");
		   rs.last();
		   System.out.println("name:"+rs.getString("name")+",address:"+rs.getString("address"));
		   
		   //출력결과의 첫행으로 이동
		   System.out.println("---- 첫행의 출력 값 -----");
		   rs.first();
		   System.out.println("name:"+rs.getString("name")+",address:"+rs.getString("address"));
		   //출력결과의 특정 행으로 이동
		   System.out.println("---- 3행의 출력 값 -----");
		   rs.absolute(3);
		   System.out.println("name:"+rs.getString("name")+",address:"+rs.getString("address"));
		   
		} catch (Exception e) {
			System.out.println("오류:" + e.getMessage());
		}
	}

}
