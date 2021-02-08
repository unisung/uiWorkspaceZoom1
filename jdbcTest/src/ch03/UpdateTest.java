package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
/* commit모드 autoCommit */
public class UpdateTest {
	public static void main(String[] args) {
		//스캐너 생성
		Scanner scanner = new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String sql = "";
		try {
			System.out.println("수정할 고객번호: ");
			int custid= scanner.nextInt();
			System.out.println("수정할 전화번호:");
			String phone = scanner.next();
			System.out.println("수정할 email:");
			String email = scanner.next();
			sql="update customer set phone=?,email=? where custid=?";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
			//PreparedStatement
			PreparedStatement pstmt = con.prepareStatement(sql);
			//바인딩 변수 순서대로 값(변수) 대입
			pstmt.setString(1,phone);
			pstmt.setString(2,email);
			pstmt.setInt(3, custid);
			
			System.out.println("쿼리문: " + sql);
			
			int result = pstmt.executeUpdate();
			System.out.println("처리된 행(row)수: " + result);
			if(result>0)
				System.out.println("수정 성공");
			else
				System.out.println("수정 실패");
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
}
