package main;
import java.sql.Connection;
import java.sql.DriverManager;

import service.ProductService;
import service.QnAService;
import service.ReviewService;
import service.UserService;

public class Main {

	public static void main(String[] args) {
		Connection conn = null;
	      try { // JDBC Driver를 메모리로 로딩하고, DriverManager에 등록
	         Class.forName("oracle.jdbc.OracleDriver");

	         // DB와 연결 
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@kosa402.iptime.org:50021/orcl", "java", "oracle");
	         System.out.println("연결 성공"); 
	         
	        UserService.addUser("je5", "s55", "aaaa555", "55555555", "둘리5");
	        UserService.deleteUser();
	        UserService.findPw();
	         ReviewService.startReviewService(conn);
	         
	      } catch (Exception e) {
	    	  System.out.println(e.getMessage());
	    	  
	      }
	     
	      

	}

}
