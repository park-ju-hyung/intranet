package initech.mvc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnlineDTO extends BaseDTO implements Serializable{
	
	private long onlineId;	// 상담번호(PK)
	private String title;	// 제목
	private String writer;	// 작성자
	private String callNo;	// 전화번호
	private String email;	// 이메일
	private String compNm;	// 기업명
	private String prdtNm;	// 문의제품
	private String prdtCt;	// 문의내용
	private Date registDate;	// 등록일
	
}
