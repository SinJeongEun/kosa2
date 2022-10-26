package dto;

import lombok.AllArgsConstructor; 
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class QReply {
	private int replyId;
	private String replyContent;
	private String userId;
	private int qnaId;
	
	//JSON ㅎ형태의 출력함수
}
