package ch05;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

//properties파일로 부터 db접속 정보 얻기
public class PropertiesDBEx {
	public static void main(String[] args) {
		try(FileInputStream fis = 
			new FileInputStream("E:\\Users\\Administrator\\git\\uiWorkspaceZoom1\\jdbcTest\\src\\ch05\\jdbc.properties")) {
	            		Properties props = new Properties();
	            		//파일로부터 Propeties객체로 정보 저장
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
	            		
	            		//Driver 로딩
	            		Class.forName(driver);
	            		//Connection 얻기
	            		Connection con=DriverManager.getConnection(url, user, password);
	            		if(con!=null)
	            			 System.out.println("연결성공!");
	            		else
	            			System.out.println("연결실패!");
	            		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
