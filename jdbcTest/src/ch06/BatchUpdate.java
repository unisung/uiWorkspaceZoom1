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
			//������ü ����
			Statement stmt = con.createStatement();
			con.setAutoCommit(false);//���� commitó��
			//�Է� �۾�-������ü�� ����
			stmt.addBatch("update book set price=price*1.1 where bookid=1");
			stmt.addBatch("update book set price=price*1.1 where bookid=2");
			stmt.addBatch("update book set price=price*1.1 where bookid=3");
			stmt.addBatch("update book set price=price*1.1 where bookid=4");
			
			//��������
			int[] updateCount = stmt.executeBatch();//[1][1][1][1]-2��° ������ ���н�[1][0][1][1]
			boolean isComplete=true;
			for(int i=0;i<updateCount.length;i++) {
				    if(updateCount[i]==0) {
				    	isComplete=false;
				    	break;//����� 4���� ������ ������ �ִ� ������ �ݺ�����, 
				    }
			}
			
			if(isComplete) 
				con.commit();//��� �����ϸ� commit;
			else
				con.rollback();//�ϳ��� �����ϸ� rollback()
			
			//�ڵ�commit���� �ǵ�����
				 con.setAutoCommit(true);
			
		 System.out.println("��ġ�۾� �Ϸ�");
		} catch (Exception e) {
			System.out.println("����:" + e.getMessage());
		}

	}

}
