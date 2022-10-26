package domain;
import java.util.Date;
import java.util.List;

import dto.QReply;
import dto.RReply;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Review {
	private int reviewId;
	private String reviewTitle;
	private String reviewContent;
	private Date reviewDate;
	private String userId;
	private int starScore;
	private int heartCount;
	private int productId;
	private List<RReply> replyList;
	//JSON 미리보기(미리볼 항목은 qna와 동일)
	
	
	//JSON 자세히보기

}
