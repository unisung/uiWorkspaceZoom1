package ch01;

import java.sql.Connection;
import java.sql.DriverManager;

/* java�� oracle dbms�� ���� �׽�Ʈ ���α׷� */
public class ConnectionTest {
	public static void main(String[] args) {
		//dbms���ᰴü ����
		Connection con=null;
		try {
			    //1.ojdbc6.jar���� Oracle�������̹� �� �ε�
			    Class.forName("oracle.jdbc.driver.OracleDriver");//����̹� ���
			    System.out.println("����̹� �ε� ����");
			    //2.dbms�� �����ϱ�
			    //��������:������������:����̹�����:�������:port��ȣ:SID 
			    //jdbc:oracle:thin:@localhost:1521:xe
			    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
			    		                                              "madang", "madang");//url,id,pwd
			    if(con!=null)
			    	 System.out.println("���Ἲ��!");
			    else
			    	System.out.println("�������!");
		}catch(Exception e) {
			System.out.println("���� �߻�: "+e.getMessage());
		}finally {
			try {
			con.close();//3.��������
			}catch(Exception e) {}
		}
	}
}
