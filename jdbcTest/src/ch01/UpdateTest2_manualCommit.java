package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
/* commit모드 autoCommit */
public class UpdateTest2_manualCommit {
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
			sql="update customer set phone='"+phone+"',email='" +email+"' where custid="+custid;
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url,id,pwd);
			
			//db commit 수동처리
			con.setAutoCommit(false);//수동처리 - java는 자동(true)이 default
			
			Statement stmt = con.createStatement();
			System.out.println("쿼리문: " + sql);
			
			int result = stmt.executeUpdate(sql);
			System.out.println("처리된 행(row)수: " + result);
			
			if(result>0) {
				System.out.println("수정 성공");//메모리상의 정상처리
				con.commit();//db에 반영처리
			}
			else {
				System.out.println("수정 실패");
				con.rollback();//되돌리기
			}			
			
		//원래모드(자동커밋)로 되돌리기
		  con.setAutoCommit(true);	
		}catch(Exception e) {
			e.getStackTrace();
		}	}
}
