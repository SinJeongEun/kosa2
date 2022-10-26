package service;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Qna;
import domain.Reply;
import dto.QReply;
import dto.RReply;
import lombok.AllArgsConstructor;

public class ReplyService {
	static Connection conn;
	static PreparedStatement pstmt;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void initRS(Connection c) {
		conn = c;
	}

	public static void startQnaAService(Connection c) throws SQLException, IOException {
		conn = c;
		//showQnaReplys(1);
		showReviewReplys(1);
	}

	public static List<QReply> showQnaReplys(int boardId) throws SQLException { //qna글에 해당하는 댓글 출력하기
		String sql = "select reply_id, reply_content, user_id, qna_id from Reply where qna_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardId);
        // 데이터를 가져와 읽을 것이므로 executeQuery
        ResultSet rs = pstmt.executeQuery();
        List<QReply>list = new ArrayList<>();
        
        System.out.println("d  " + boardId);
        // list 에 reply 객체를 담아야됨
        while (rs.next()) {
        	System.out.print("aaaaaa");
        	QReply qr1 = new QReply(rs.getInt("reply_id"), rs.getString("reply_content"), 
       			 rs.getString("user_id"), rs.getInt("qna_id"));
        	list.add(qr1);
        }
        
        return list;
		
	}
	
	
	public static List<RReply> showReviewReplys(int boardId) throws SQLException { //qna글에 해당하는 댓글 출력하기
		String sql = "select reply_id, reply_content, user_id, review_id from Reply where review_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardId);
        // 데이터를 가져와 읽을 것이므로 executeQuery
        ResultSet rs = pstmt.executeQuery();
        List<RReply>list = new ArrayList<>();
        
        System.out.println("d  " + boardId);
        // list 에 reply 객체를 담아야됨
        while (rs.next()) {
        	System.out.print("aaaaaa");
        	RReply qr1 = new RReply(rs.getInt("reply_id"), rs.getString("reply_content"), 
       			 rs.getString("user_id"), rs.getInt("review_id"));
        	list.add(qr1);
        }
        
        return list;
		
	}

}
