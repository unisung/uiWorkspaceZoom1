package ch05;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

//properties���Ϸ� ���� db���� ���� ���
public class PropertiesDBEx {
	public static void main(String[] args) {
		try(FileInputStream fis = 
			new FileInputStream("E:\\Users\\Administrator\\git\\uiWorkspaceZoom1\\jdbcTest\\src\\ch05\\jdbc.properties")) {
	            		Properties props = new Properties();
	            		//���Ϸκ��� Propeties��ü�� ���� ����
	            		props.load(fis);
	            		String driver=props.getProperty("driver");
	            		String url=props.getProperty("url");
	            		String user=props.getProperty("id");
	            		String password=props.getProperty("pwd");
	            		//
	            		System.out.println(driver);
	            		System.out.println(url);
	            		System.out.println(user);
	            		System.out.println(password);
	            		
	            		//Driver �ε�
	            		Class.forName(driver);
	            		//Connection ���
	            		Connection con=DriverManager.getConnection(url, user, password);
	            		if(con!=null)
	            			 System.out.println("���Ἲ��!");
	            		else
	            			System.out.println("�������!");
	            		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
