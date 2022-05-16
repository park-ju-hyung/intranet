package initech.mvc.vo;

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
public class OnlineVO extends BaseVO implements Serializable {

	private long no;	// 온라인NO
	private long onlineId;	// 상담번호(PK)
	private String title;	// 제목
	private String writer;	// 작성자
	private String callNo;	// 전화번호
	private String email;	// 이메일
	private String compNm;	// 기업명
	private String prdtNm;	// 문의제품
	private String prdtCt;	// 문의내용
	private String registDate;	// 등록일
	
	
	//페이징
	private long pageNo = 1L;//페이지 번호
	private long pageSize = 10L;//페이지 사이즈
	private long pageBlock = 10L;//페이지 블록
	private long pageOffset;//페이징 범위

	//검색
	private String searchField;//검색 필드
	private String searchKwd;//검색 단어
}
