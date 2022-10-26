package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import domain.Review;
import dto.RReply;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReviewService {

	static Connection conn;
    static PreparedStatement pstmt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void startReviewService(Connection c) throws SQLException, IOException {
        conn = c;
        //처리 로직에 따라 회원가입, 로그인, 회원탈퇴 메소드 실행

        //회원가입
//        showReviewOne(1); // 우선 임시로 1 값을 넣음, 원래는 선택한 게시글의 아이디 가져와야 함
    }
    
    public void showReviewOne(int num) throws SQLException { // 게시글 상세보기
    	//게시글과 댓글이 함께 보여야 됨
    	 String sql = "select review_id, review_title, review_content, review_date, user_id,star_score,heart_count from review where review_id = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, num);

         ResultSet rs = pstmt.executeQuery();
         ReplyService.initRS(conn);
         
         List<RReply> list = ReplyService.showReviewReplys(num);// reply 조회하는 메소드 구현(REPLY서비스에) 및 호출
         System.out.println("sdff " + list.size()); //댓글수
         
         Review r1 = null;
         while (rs.next()) {   
        	 r1 = new Review(rs.getInt("review_id"), rs.getString("review_title"), 
        			 rs.getString("review_content"), rs.getDate("review_date"), rs.getString("user_id"),rs.getInt("star_score"),rs.getInt("heart_count"),1, list);
        	 //r1.setProductId(0);
//           
        }
        System.out.println(r1.toString());
    }
    
    public static void writeQna() {
    	
    }
}

