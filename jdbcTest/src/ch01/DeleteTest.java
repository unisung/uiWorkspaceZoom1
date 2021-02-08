package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql = "";
		
	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
			
			con.setAutoCommit(false);//수동처리 - java는 자동(true)이 default
			
			sql = "select * from orders order by orderid";
			Statement stmt = con.createStatement();
			System.out.println("쿼리문: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			//매출리스트 보여주기
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"|"+rs.getInt(2)+"|"
			    +rs.getInt(3)+"|"+rs.getInt(4)+"|"+rs.getString(5));
			}
			//삭제할 번호 입력받기
			System.out.println("삭제할거래번호를 입력하세요>");
			int orderid=scanner.nextInt();
			sql ="delete from orders where orderid="+orderid;
			System.out.println("쿼리문:"+sql);
		    
			int result = stmt.executeUpdate(sql);
			System.out.println("처리된 행(row)수: " + result);
			if(result>0) {
				con.commit();
				System.out.println("삭제완료!");
			}else {
				con.rollback();
				System.out.println("삭제실패!");
			}
			//자동반영처리
			con.setAutoCommit(true);
		}catch(Exception e) {
			System.out.println("오류:"+e.getMessage());
		}
		
	}

}
