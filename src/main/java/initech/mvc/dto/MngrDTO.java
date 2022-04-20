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
public class MngrDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mngrId;
	private String mngrPasswd;
	private String mngrName;
	private String mngrPhone;
	private String mngrEmail;
	private String useYn;
	private String roleDiv;
	private Date regDate;
	
	// 추가된 필드
	private String errorCode;
}
