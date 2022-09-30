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
	
	private long no;	// 온라인NO
	private long onlineId;	// 상담번호(PK)
	private String title;	// 제목
	private String writer;	// 작성자
	private String callNo;	// 전화번호
	private String email;	// 이메일
	private String compNm;	// 기업명
	private String prdtNm;	// 문의제품
	private String prdtCt;	// 문의내용
	private Date registDate;	// 등록일
	
	// 추가된 필드
	private String schDateStart;	// 등록일(첫날짜) 검색
	private String schDateEnd;		// 등록일(끝날짜) 검색
	private String searchTitle;		// 제목 검색
	private String schPrdtNm1;		// 제품명1 검색
	private String schPrdtNm2;		// 제품명2 검색
	private String schCompNm;		// 기업명 검색
	private String schWriter;		// 작성자 검색
	
	
}
