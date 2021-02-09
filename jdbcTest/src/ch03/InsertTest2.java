package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertTest2 {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		String createTablesql="create table members( "
										+ "   id varchar2(20) primary key, "
										+ "   pwd varchar2(20) not null, "
										+ "   name varchar2(30) "
										+ "   ) ";
		String tableCountSql = "select COUNT(*) from user_tables where table_name='MEMBERS'";
		String memberCountSql="select count(*) from members where id =?";
		String insertMemberSql="insert into members values(?,?,?)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    //테이블 존재 여부확인
		    PreparedStatement pstmt = con.prepareStatement(tableCountSql);
		    ResultSet rs = pstmt.executeQuery();
		    if(rs.next()) {//테이블이 없으면 생성/있으면 skip
		    	if(rs.getInt(1)==0) {
		    		pstmt=con.prepareStatement(createTablesql);
		    		pstmt.executeQuery();//create문은 executeQuery()로 실행 
		    	}
		    }
		    //회원 중복여부확인
		   pstmt=con.prepareStatement(memberCountSql);
		   pstmt.setString(1,"hong");
		   rs=pstmt.executeQuery();
		   if(rs.next()) {
			   if(rs.getInt(1)==0) {//입력한 id에 해당하는 회원이 없으면 가입처리
				   pstmt=con.prepareStatement(insertMemberSql);
				   pstmt.setString(1, "hong");//정보를 바인딩변수(?)에 설정
				   pstmt.setString(2, "1234");
				   pstmt.setString(3,"홍길동");
				   int result=pstmt.executeUpdate();
				  if(result>0) 
					    System.out.println("가입성공!");
				  else
					   System.out.println("가입중 오류 발생!");
			   }else {//이미 존재하면 메세지 출력
				   System.out.println("이미 존재하는 아이디입니다.");
			   }
		   }
		}catch(Exception e) {
			System.out.println("오류발생: "+e.getMessage());
		}
	}
}
