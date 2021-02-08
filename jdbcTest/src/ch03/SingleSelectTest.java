package ch03;

//PreParedStatement
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// �Ѱ� �� ��ȸ 
public class SingleSelectTest {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		//prepareStatement�� ����ϱ� ���� ���� ���ԵǴ� �κп� ?�� ��ġ
		String sql="select name,address,phone from customer where custid=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    //PreparedStatement�� ó��
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    System.out.println("������: "+sql);
		    //���ε� ������ �� ����
		    pstmt.setInt(1, 1);//setInt(�ε�����ȣ(?�� ����),������ ��)
		    ResultSet rs=pstmt.executeQuery();
			//�Ѱ� ������ ��� ó��
		   if(rs.next()) {
		    	String name=rs.getString("name");
		    	String address=rs.getString("address");
		    	String phone=rs.getString("phone");
		    	System.out.println("�̸�: "+name+",�ּ�:"+address+",����ó:"+phone);
		   }
		}catch(Exception e) {
			System.out.println("����:"+e.getMessage());
		}
	}

}
