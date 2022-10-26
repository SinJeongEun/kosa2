package domain;
import java.util.Date;
import java.util.List;

import dto.QReply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
@Data
@AllArgsConstructor
@ToString
public class Qna {
	private int qnaId;
	private String qnaTitle;
	private String qnaContent;
	private Date qnaDate;
	private int productId;
	private String userId;
	private List<QReply> replyList;
	
	
	
	//JSON으로 게시글과 댓글 한번에 보이기
	
	//글 목록 미리보기 (글Id, 제목, 글쓴이, 날짜)
	
}
