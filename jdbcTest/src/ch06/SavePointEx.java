package ch06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

public class SavePointEx {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "madang";
		String pwd = "madang";
		String sql="update book set price=price*2 where bookid in (4,5,6) ";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd);
			//쿼리객체 생성
			PreparedStatement pstmt = con.prepareStatement(sql);
			con.setAutoCommit(false);//수동 commit처리
			
			Savepoint save1 = con.setSavepoint();//savePoint
			//update 처리
			pstmt.executeUpdate();
			//결과 조회 
			pstmt=con.prepareStatement("select bookid, price from book where bookid in ( 4,5,6)");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("savepoint1 이후");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getInt(2));
			}
			
			Savepoint save2 = con.setSavepoint();//savePoint
			//update 처리
			pstmt=con.prepareStatement("delete from book where bookid=4");
			pstmt.executeUpdate();
			//결과 조회 
			pstmt=con.prepareStatement("select bookid, price from book where bookid in ( 4,5,6)");
			 rs = pstmt.executeQuery();
			 System.out.println("savepoint2 이후");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getInt(2));
			}
			
			//savepoint까지 되돌리기
		 con.rollback(save2);
		 System.out.println("savepoint2 이전으로 되돌린 후");
			
		//결과 조회 
			pstmt=con.prepareStatement("select bookid, price from book where bookid in ( 4,5,6)");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getInt(2));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
