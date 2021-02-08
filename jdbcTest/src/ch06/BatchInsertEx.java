package ch06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//JDBC 2.0-batch 처리( 한 트랜잭션에 같은 작업을 묶어서 처리)
public class BatchInsertEx {
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
			stmt.addBatch("insert into book values(21,'골프의 역사','굿스포츠',9900)");
			stmt.addBatch("insert into book values(22,'골프아는 여자','굿스포츠',9900)");
			stmt.addBatch("insert into book values(23,'골프의 이해','굿스포츠',9900)");
			stmt.addBatch("insert into book values(24,'골프의 추억','굿스포츠',9900)");
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
