package ch06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BatchUpdate {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "madang";
		String pwd = "madang";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd);
			//쿼리객체 생성
			Statement stmt = con.createStatement();
			con.setAutoCommit(false);//수동 commit처리
			//입력 작업-쿼리객체에 저장
			stmt.addBatch("update book set price=price*1.1 where bookid=1");
			stmt.addBatch("update book set price=price*1.1 where bookid=2");
			stmt.addBatch("update book set price=price*1.1 where bookid=3");
			stmt.addBatch("update book set price=price*1.1 where bookid=4");
			
			//쿼리실행
			int[] updateCount = stmt.executeBatch();//[1][1][1][1]-2번째 쿼리가 실패시[1][0][1][1]
			boolean isComplete=true;
			for(int i=0;i<updateCount.length;i++) {
				    if(updateCount[i]==0) {
				    	isComplete=false;
				    	break;//실행된 4개의 쿼리중 오류가 있는 시점에 반복정지, 
				    }
			}
			
			if(isComplete) 
				con.commit();//모두 성공하면 commit;
			else
				con.rollback();//하나라도 실패하면 rollback()
			
			//자동commit으로 되돌리기
				 con.setAutoCommit(true);
			
		 System.out.println("배치작업 완료");
		} catch (Exception e) {
			System.out.println("오류:" + e.getMessage());
		}

	}

}
