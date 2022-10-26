package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import domain.Qna;
import domain.Reply;
import dto.QReply;

public class QnAService {
	static Connection conn;
    static PreparedStatement pstmt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void startQnAService(Connection c) throws SQLException, IOException {
        conn = c;
        //처리 로직에 따라 회원가입, 로그인, 회원탈퇴 메소드 실행

        //회원가입
        showQnaOne(1); // 우선 임시로 1 값을 넣음, 원래는 선택한 게시글의 아이디 가져와야 함
    }
    
    public static void showQnaOne(int num) throws SQLException { // 게시글 상세보기
    	//게시글과 댓글이 함께 보여야 됨
    	 String sql = "select qna_id, qna_title, qna_content, qna_date, user_id from Qna where qna_id = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, num);

         ResultSet rs = pstmt.executeQuery();
         ReplyService.initRS(conn);
         
         List<QReply> list = ReplyService.showQnaReplys(num);// reply 조회하는 메소드 구현(REPLY서비스에) 및 호출
         System.out.println("sdff " + list.size()); //댓글수
         
         Qna q1 = null;
         while (rs.next()) {
        	 q1 = new Qna(rs.getInt("qna_id"), rs.getString("qna_title"), 
        			 rs.getString("qna_content"), rs.getDate("qna_date"),1, rs.getString("user_id"), list);
        	 //q1.setProductId(0); //정은아 이거 모야 ㅋ ㅎ . ..??ㅠㅠ
//           
        }
        System.out.println(q1.toString());
    }
}
