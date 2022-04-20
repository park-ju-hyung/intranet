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
public class UserVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	@JsonIgnore private String userPasswd;
	private String userName;
	private String userPhone;
	private String userEmail;
	private String userDiv;
	private String useYn;
	private Date regDate;
	
	// 추가된 항목
	private String cmpDiv;
	private String cmpDivName;
	private String cmpNo;
	private String corpRegNo;
	private String cmpName;
	private String cmpPostNo;
	private String cmpAddr1;
	private String cmpAddr2;
	private String cmpAreaCd;
	private String cmpOpenYmd;
	private String bburiYn;
	private String bburiRegNo;
	private String bburiTechAppointNo;
	private String bburiGoodAppointNo;
	private String bburiFamilyAppointNo;
	private String bburiCmpCertIssueYmd;
	private String bburiTechAppointYmd;
	private String bburiGoodAppointYmd;
	private String bburiFamilyAppointYmd;
	private String homepageUrl;
	private String phoneNo;
	private String faxNo;
	private int empCntTotal;
	private int empCntManage;
	private int empCntProduct;
	private int empCntResearch;
	private int empCntForeign;
	private String labYn;
	private String labOpenYmd;
	private String labSiteArea;
	private String facYn;
	private String facRegNo;
	private String facSiteArea;
	private String profitYn;
	private String productMain;
	private int cmplxIdx;
	private String cmplxNm;
	private String cmplxAreaCd;
	
	private String postNo;
	private String addr1;
	private String addr2;
	private String govAreaCd;
	private String govCityCd;
	private String dept;
	private String position;
	private String govDiv;
	private String govAreaCdNm;
	private String govCityCdNm;
	private String govDivCdNm;
	
	private String ceoName;
	
	private String kpscmNo;
}
