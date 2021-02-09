package ch06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//JDBC 2.0 - scrollable
public class ScrollableEx2 {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "madang";
		String pwd = "madang";
		String sql="select bookid, bookname,publisher,price from book order by bookid";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd);
			//쿼리객체 생성
			PreparedStatement pstmt=con.prepareStatement(sql,//쿼리문
					         ResultSet.TYPE_SCROLL_SENSITIVE,//수정분 반영     
					         ResultSet.CONCUR_UPDATABLE);//수정가능
           //결과 처리
		   ResultSet rs = pstmt.executeQuery();
		   System.out.println(" ---------단방향 next()---------------");
		   while(rs.next()) {
			   int i=0;
			   System.out.println(rs.getInt(++i)+","+rs.getString(++i)+","+rs.getString(++i)+","+rs.getInt(++i));
		   }
		   System.out.println(" ---------단방향 previous()---------------");
		   while(rs.previous()) {
			   int i=0;
			   System.out.println(rs.getInt(++i)+","+rs.getString(++i)+","+rs.getString(++i)+","+rs.getInt(++i));
		   }
		   System.out.println(" ---------첫번째행 first()---------------");
		     rs.first();
			   int i=0;
			   System.out.println(rs.getInt(++i)+","+rs.getString(++i)+","+rs.getString(++i)+","+rs.getInt(++i));
		  
			   System.out.println(" ---------첫번째행 last()---------------");
			  rs.last();
			   i=0;
			 System.out.println(rs.getInt(++i)+","+rs.getString(++i)+","+rs.getString(++i)+","+rs.getInt(++i));
			
			 System.out.println(" ---------특정행 absolute(행번호)---------------");
			 rs.absolute(3);
			 i=0;
			System.out.println(rs.getInt(++i)+","+rs.getString(++i)+","+rs.getString(++i)+","+rs.getInt(++i));
				
					   	   
		} catch (Exception e) {
			System.out.println("오류:" + e.getMessage());
		}
	}

}
