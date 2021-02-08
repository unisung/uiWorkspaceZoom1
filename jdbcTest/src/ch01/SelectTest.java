package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/* 1.���� -> 2. ���� ���� ->3. ��� ó�� ->4. �ڿ����� */
public class SelectTest {
	public static void main(String[] args) {
	 //���ᰴü ����
	Connection con=null;
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String id="madang";
	String pwd="madang";
	String sql="select bookid, bookname, publisher, price from book";
	try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");//1.�ε�
		    con=DriverManager.getConnection(url, id, pwd);//2.���ᰴü ���
		    //3.������ü ����
		    Statement stmt = con.createStatement();
		    //4.�������� �� ��� ���
		    ResultSet rs = stmt.executeQuery(sql);//select�� executeQuery
		    //5.��� ó�� 
		    while(rs.next()) {
		    	int bookid = rs.getInt(1);
		    	String bookName=rs.getString(2);
		    	String publisher=rs.getString(3);
		    	int price = rs.getInt(4);
		    	System.out.println(bookid+"|"+bookName+"|"+publisher+"|"+price);
		    }
	}catch(Exception e) {
		System.out.println("�����߻�: "+e.getMessage());
	}finally {
		try {
			con.close();//6.�ڿ�����
		}catch(Exception e) {}
	}
	}
}
