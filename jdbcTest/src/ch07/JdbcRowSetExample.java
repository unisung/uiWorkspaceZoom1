package ch07;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSetExample {

	public static void main(String[] args) throws SQLException {
		//RowSetFactory��ü ����
		RowSetFactory rowSetFactory = RowSetProvider.newFactory(); 
		//JdbcRowSet��ü ����
		//JdbcRowSet jrs = rowSetFactory.createJdbcRowSet();
		//CachedRowSet��ü ����
		CachedRowSet jrs = rowSetFactory.createCachedRowSet();
		//���� �� ������ ����, Connection, PreparedStatement, ResultSet ���� ����
		jrs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		jrs.setUsername("madang");
		jrs.setPassword("madang");
		jrs.setCommand("select * from customer");
		//����
		jrs.execute();
		
		//��� ó�� 
		while(jrs.next()) {
			System.out.println(jrs.getInt(1)+","+jrs.getString(2));
		}
		while(jrs.previous()) {
			System.out.println(jrs.getInt(1)+","+jrs.getString(2));
		}
		//scrollable
		jrs.absolute(3);//��ȸ����� ������ġ 3������ �̵�
		System.out.println(jrs.getInt(1)+","+jrs.getString(2));
		jrs.first();//��ȸ����� ù��° ��
		System.out.println(jrs.getInt(1)+","+jrs.getString(2));
		jrs.last();//��ȸ����� ������ ��
		System.out.println(jrs.getInt(1)+","+jrs.getString(2));
	}
}
