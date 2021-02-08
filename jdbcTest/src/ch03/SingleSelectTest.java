package ch03;

//PreParedStatement
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 한건 만 조회 
public class SingleSelectTest {
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String id="madang";
		String pwd="madang";
		//prepareStatement로 사용하기 위해 값이 대입되는 부분에 ?를 배치
		String sql="select name,address,phone from customer where custid=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con=DriverManager.getConnection(url, id, pwd);
		    //PreparedStatement로 처리
		    PreparedStatement pstmt = con.prepareStatement(sql);
		    System.out.println("쿼리문: "+sql);
		    //바인딩 변수와 값 세팅
		    pstmt.setInt(1, 1);//setInt(인덱스번호(?의 순번),세팅할 값)
		    ResultSet rs=pstmt.executeQuery();
			//한건 추출한 결과 처리
		   if(rs.next()) {
		    	String name=rs.getString("name");
		    	String address=rs.getString("address");
		    	String phone=rs.getString("phone");
		    	System.out.println("이름: "+name+",주소:"+address+",연락처:"+phone);
		   }
		}catch(Exception e) {
			System.out.println("오류:"+e.getMessage());
		}
	}

}
