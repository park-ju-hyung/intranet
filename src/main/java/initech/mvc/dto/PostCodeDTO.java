package initech.mvc.dto;

import java.io.Serializable;

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
public class PostCodeDTO extends BaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String confmKey;
	private String keyword;
	private String resultType;
	private String hstryYn;
	private String firstSort;
	private String addInfoYn;
}
