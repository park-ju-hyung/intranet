package initech.mvc.dto;

import java.io.Serializable;
import java.util.Date;

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
public class UserDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String userPasswd;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userDiv;
	private String useYn;
	private Date regDate;
	
	// 추가된 필드
	private String errorCode;
	private String termsYn;
	private String privacyYn;
	private String redirectCode;
	private String cmpNo;
	
	private String searchUserId;
	private String searchUserName;
	private String searchCeoName;
	private String searchCmpName;
	private String searchCmpNo;
	private String searchBburiRegNo;
	private String searchRegDateStart;
	private String searchRegDateEnd;
	private String searchCmpAreaCd;
	private String searchCmplxIdx;
	private String searchCmplxNm;
	private String searchBasicAreaCd;
	
	private String kpscmNo;
	
	private String bburiTechAppointNo;
	private String bburiGoodAppointNo;
	private String bburiFamilyAppointNo;
}
