package ch02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatementVSPreparedStatementEx {
   public static void main(String[] args) {
      Connection con = null;
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");// 드라이버 경로
         System.out.println("드라이버 로드 성공");
         con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                                    "madang", "madang");// url,id,pwd
         //Statement문
         Statement stmt = con.createStatement();
         String publisher="대한미디어";
         String sql="select bookid,bookname,price from book where publisher='"+publisher+"'";
         ResultSet rs = stmt.executeQuery(sql);
         while(rs.next()) {
            System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getInt(3));
         }
         System.out.println();
         //PreparedStatement문 -쿼리 속도 향상
         //publisher="굿스포츠"
         sql="select bookid,bookname,price from book where publisher=?";//값이 처리될 위치에 바인딩
         PreparedStatement pstmt = con.prepareStatement(sql);//con.prepareStatment(쿼리문);
         //sql문에 값 지정 -> ?변수에 값 세팅 setString(?의 순서, 세팅할 값)
         pstmt.setString(1, publisher);
         //쿼리문 실행
         rs = pstmt.executeQuery();//pstmt.executeQuery();
         while(rs.next()) {
            System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getInt(3));
         }
         
      } catch (Exception e) {
         System.out.println("오류발생:" + e.getMessage());
      } finally {
         try {
            con.close();// 3.연결해제
         } catch (Exception e) {
         }
      }

   }

}

