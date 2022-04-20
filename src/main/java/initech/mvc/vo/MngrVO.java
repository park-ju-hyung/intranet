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
public class MngrVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mngrId;
	@JsonIgnore private String mngrPasswd;
	private String mngrName;
	private String mngrPhone;
	private String mngrEmail;
	private String useYn;
	private String roleDiv;
	private Date regDate;
}
