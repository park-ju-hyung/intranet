package initech.mvc.vo;

import java.io.Serializable;
import java.sql.Date;

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
public class AttachFileVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String afId;
	private String afBundleId;
	private String filePath;
	private String webPath;
	private String fileNm;
	private String saveNm;
	private String fileExt;
	private long fileSize;
	private String useYn;
	private Date regDt;
	
}
