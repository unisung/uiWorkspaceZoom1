package ch05;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class PropertiesDBEx2 {
public static void main(String[] args) {
	try {
		    FileInputStream fis 
		      = new FileInputStream("E:\\Users\\Administrator\\git\\uiWorkspaceZoom1\\jdbcTest\\src\\ch05\\dbcon.properties");
		    Properties prop=new Properties();
		    prop.load(fis);
		    Class.forName(prop.getProperty("driver"));
		    Connection con
		      =DriverManager.getConnection(prop.getProperty("url"), 
		    		                                         prop.getProperty("user"), prop.getProperty("password"));
		   System.out.println(con!=null?"연결성공!":"연결실패!");
		    
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
