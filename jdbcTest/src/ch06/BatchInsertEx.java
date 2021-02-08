package ch06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//JDBC 2.0-batch ó��( �� Ʈ����ǿ� ���� �۾��� ��� ó��)
public class BatchInsertEx {
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
			stmt.addBatch("insert into book values(21,'������ ����','�½�����',9900)");
			stmt.addBatch("insert into book values(22,'�����ƴ� ����','�½�����',9900)");
			stmt.addBatch("insert into book values(23,'������ ����','�½�����',9900)");
			stmt.addBatch("insert into book values(24,'������ �߾�','�½�����',9900)");
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
