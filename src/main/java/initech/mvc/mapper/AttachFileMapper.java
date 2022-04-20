package initech.mvc.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import initech.common.config.DBMapper;
import initech.mvc.dto.AttachFileDTO;
import initech.mvc.vo.AttachFileVO;

@DBMapper
@Repository
public interface AttachFileMapper {
	// 파일 조회
	public AttachFileVO selectAttachFileInfo(AttachFileDTO dto);
	// 파일 조회
	public List<AttachFileVO> selectAttachFileBundle(AttachFileDTO dto);
	
	// 파일 등록
	public void insertAttachFile(AttachFileDTO dto);
	
	// 파일 수정
	public void updateAttachFile(AttachFileDTO dto);
	
	// 파일 사용여부 업데이트
	public void updateAttachFileUseYn(AttachFileDTO dto);
	
	// 파일번들 사용여부 업데이트
	public void updateAttachBundleUseYn(AttachFileDTO dto);
	
	// 파일 삭제
	public void deleteAttachFile(AttachFileDTO dto);

	// 검색된 파일 갯수 조회
	public long selectTotalCount() throws Exception;
}
