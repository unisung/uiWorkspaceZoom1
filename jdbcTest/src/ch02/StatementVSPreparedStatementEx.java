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
         Class.forName("oracle.jdbc.driver.OracleDriver");// ����̹� ���
         System.out.println("����̹� �ε� ����");
         con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                                    "madang", "madang");// url,id,pwd
         //Statement��
         Statement stmt = con.createStatement();
         String publisher="���ѹ̵��";
         String sql="select bookid,bookname,price from book where publisher='"+publisher+"'";
         ResultSet rs = stmt.executeQuery(sql);
         while(rs.next()) {
            System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getInt(3));
         }
         System.out.println();
         //PreparedStatement�� -���� �ӵ� ���
         //publisher="�½�����"
         sql="select bookid,bookname,price from book where publisher=?";//���� ó���� ��ġ�� ���ε�
         PreparedStatement pstmt = con.prepareStatement(sql);//con.prepareStatment(������);
         //sql���� �� ���� -> ?������ �� ���� setString(?�� ����, ������ ��)
         pstmt.setString(1, publisher);
         //������ ����
         rs = pstmt.executeQuery();//pstmt.executeQuery();
         while(rs.next()) {
            System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getInt(3));
         }
         
      } catch (Exception e) {
         System.out.println("�����߻�:" + e.getMessage());
      } finally {
         try {
            con.close();// 3.��������
         } catch (Exception e) {
         }
      }

   }

}

