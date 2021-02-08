package ch03;

//PreParedStatement
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// �Ѱ� �� ��ȸ 
public class SingleSelectTest2 {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		//prepareStatement�� ����ϱ� ���� ���� ���ԵǴ� �κп� ?�� ��ġ
		String sql="select orderid, o.custid, c.name, o.bookid, b.bookname "
				     + "  from orders o, customer c, book b "
				     + " where o.custid=c.custid "
				     + "    and o.bookid=b.bookid "
				     + "    and name=?"
				     + "    and o.bookid=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    //PreparedStatement�� ó��
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    System.out.println("������: "+sql);
		    //���ε� ������ �� ����
		    pstmt.setString(1,"������");
		    pstmt.setInt(2, 2);
		    ResultSet rs=pstmt.executeQuery();
			//�Ѱ� ������ ��� ó��
		   if(rs.next()) {
		    	int orderid=rs.getInt(1);
		    	int custid=rs.getInt(2);
		    	String name=rs.getString(3);
		    	int bookid=rs.getInt(4);
		    	String bookname=rs.getString(5);
		    	System.out.println(orderid+":"+custid+":"+name+"|"+bookid+"|"+bookname);
		   }
		}catch(Exception e) {
			System.out.println("����:"+e.getMessage());
		}
	}

}
