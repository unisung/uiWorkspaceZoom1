package ch07;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetExample {

	public static void main(String[] args) throws SQLException {
		//RowSetFactory객체 생성
		RowSetFactory rowSetFactory = RowSetProvider.newFactory(); 
		//JdbcRowSet객체 생성
		//JdbcRowSet jrs = rowSetFactory.createJdbcRowSet();
		//CachedRowSet객체 생성
		CachedRowSet jrs = rowSetFactory.createCachedRowSet();
		//연결 및 쿼리문 설정, Connection, PreparedStatement, ResultSet 없이 생성
		jrs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		jrs.setUsername("madang");
		jrs.setPassword("madang");
		jrs.setCommand("select * from customer");
		//실행
		jrs.execute();
		
		//결과 처리 
		while(jrs.next()) {
			System.out.println(jrs.getInt(1)+","+jrs.getString(2));
		}
		while(jrs.previous()) {
			System.out.println(jrs.getInt(1)+","+jrs.getString(2));
		}
		//scrollable
		jrs.absolute(3);//조회결과의 절대위치 3행으로 이동
		System.out.println(jrs.getInt(1)+","+jrs.getString(2));
		jrs.first();//조회결과의 첫번째 행
		System.out.println(jrs.getInt(1)+","+jrs.getString(2));
		jrs.last();//조회결과의 마지막 행
		System.out.println(jrs.getInt(1)+","+jrs.getString(2));
	}
}
