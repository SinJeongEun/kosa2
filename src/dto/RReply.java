package dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RReply {
	private int replyId;
	private String replyContent;
	private String userId;
	private int reviewId;
	
	//JSON ㅎ형태의 출력함수
}
