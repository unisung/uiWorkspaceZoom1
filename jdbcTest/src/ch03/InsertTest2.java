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
		    //���̺� ���� ����Ȯ��
		    PreparedStatement pstmt = con.prepareStatement(tableCountSql);
		    ResultSet rs = pstmt.executeQuery();
		    if(rs.next()) {//���̺��� ������ ����/������ skip
		    	if(rs.getInt(1)==0) {
		    		pstmt=con.prepareStatement(createTablesql);
		    		pstmt.executeQuery();//create���� executeQuery()�� ���� 
		    	}
		    }
		    //ȸ�� �ߺ�����Ȯ��
		   pstmt=con.prepareStatement(memberCountSql);
		   pstmt.setString(1,"hong");
		   rs=pstmt.executeQuery();
		   if(rs.next()) {
			   if(rs.getInt(1)==0) {//�Է��� id�� �ش��ϴ� ȸ���� ������ ����ó��
				   pstmt=con.prepareStatement(insertMemberSql);
				   pstmt.setString(1, "hong");//������ ���ε�����(?)�� ����
				   pstmt.setString(2, "1234");
				   pstmt.setString(3,"ȫ�浿");
				   int result=pstmt.executeUpdate();
				  if(result>0) 
					    System.out.println("���Լ���!");
				  else
					   System.out.println("������ ���� �߻�!");
			   }else {//�̹� �����ϸ� �޼��� ���
				   System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
			   }
		   }
		}catch(Exception e) {
			System.out.println("�����߻�: "+e.getMessage());
		}
	}
}
