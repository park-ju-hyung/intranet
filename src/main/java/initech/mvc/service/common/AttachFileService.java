	package initech.mvc.service.common;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import initech.common.constant.AppConstant;
import initech.common.util.AppFileUtil;
import initech.mvc.dto.AttachFileDTO;
import initech.mvc.mapper.AttachFileMapper;
import initech.mvc.vo.AttachFileVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AttachFileService {

    private final AttachFileMapper attachFileMapper;

	@Value("${upload.root}")	private String uploadRoot;
    
    @Value("${path.attach}")	private String pathAttach;
    @Value("${path.editor}")	private String pathEditor;
    @Value("${path.static}")	private String pathStatic;
    @Value("${path.image}")		private String pathImage;

    /**
     * 첨부파일 목록 조회
     * @param attachFileDTO
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = true)
	public Map<String, Object> selectAttachFileBundle(AttachFileDTO attachFileDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();

		List<AttachFileVO> list = attachFileMapper.selectAttachFileBundle(attachFileDTO);

		long totalCount = attachFileMapper.selectTotalCount();

		rs.put("attachFileDTO", attachFileDTO);
		rs.put("list", list);
		rs.put("totalCount", totalCount);

		return rs;
	}
    
    /**
     * 첨부파일 단건 조회
     * @param attachFileDTO
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = true)
	public Map<String, Object> selectAttachFileInfo(AttachFileDTO attachFileDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		if (attachFileDTO.getAfId() == null)
			throw new Exception("Required parameter missing.");
		
		// 첨부파일정보 조회
		AttachFileVO afVO = attachFileMapper.selectAttachFileInfo(attachFileDTO);
		
		// 첨부파일정보

		rs.put("attachFileDTO", attachFileDTO);
		rs.put("info", afVO);

		return rs;
	}
    
    /**
     * 첨부파일 등록
     * @param attachFileDTO
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false, rollbackFor = { Exception.class })
	public Map<String, Object> fileUpload(HttpServletRequest request) throws Exception {
		
        Map<String, Object> rsMap = new HashMap<String, Object>();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        
        String afBundleId = StringUtils.defaultString(request.getParameter("afBundleId"));
        
        String datePath = DateFormatUtils.format(new Date(), "/yyyy-MM-dd");
        String phyPath = uploadRoot + pathAttach +  datePath;
        String webPath = pathStatic + pathAttach +  datePath;
        
        FileUtils.forceMkdir(new File(phyPath));
        
        Iterator<String> fileNames = multipartRequest.getFileNames();
        
        
        if (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile multipartFile = multipartRequest.getFile(fileName);

            if (multipartFile != null && !multipartFile.isEmpty()) {
                String orginlFileNm = multipartFile.getOriginalFilename();//원본 명
                String ext = FilenameUtils.getExtension(orginlFileNm);//파일 확장자
                String saveNm = AppFileUtil.getSaveFileNm() + "." + ext;//저장 명

                //임시 폴더로 저장
                File saveFile = new File(phyPath, saveNm);//저장 파일
                multipartFile.transferTo(saveFile);//저장 파일 쓰기
                long fileSize = multipartFile.getSize();
                AttachFileDTO dto = new AttachFileDTO();
                
                dto.setAfBundleId(afBundleId);
                dto.setFilePath(phyPath);
                dto.setWebPath(webPath);
                dto.setFileNm(orginlFileNm);
                dto.setSaveNm(saveNm);
                dto.setFileExt(ext);
                dto.setFileSize(fileSize);
                dto.setUseYn("N");
        		
                attachFileMapper.insertAttachFile(dto);

                
                
                rsMap = new HashMap<String, Object>();
                rsMap.put("af", dto);
        		rsMap.put("result", AppConstant.RESULT_SUCCESS);
            }
        }
        
        return rsMap;
		
    }
    
    
    /**
     * 첨부파일 등록
     * @param attachFileDTO
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false, rollbackFor = { Exception.class })
	public Map<String, Object> insertAttachFile(AttachFileDTO attachFileDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		
		attachFileMapper.insertAttachFile(attachFileDTO);
		
		rs.put("result", AppConstant.RESULT_SUCCESS);
		return rs;
    }

    /**
     * 첨부파일 수정
     * @param attachFileDTO
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false, rollbackFor = { Exception.class })
	public Map<String, Object> updateAttachFile(AttachFileDTO attachFileDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		if (attachFileDTO.getAfId() == null)
			throw new Exception("Required parameter missing.");
		
		// 첨부파일 수정
		attachFileMapper.updateAttachFile(attachFileDTO);
		
		rs.put("result", AppConstant.RESULT_SUCCESS);
		return rs;
    }
    
    /**
     * 첨부파일 삭제
     * @param attachFileDTO
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = false, rollbackFor = { Exception.class })
	public Map<String, Object> deleteAttachFile(AttachFileDTO attachFileDTO) throws Exception {
		Map<String, Object> rs = new HashMap<String, Object>();
		if (attachFileDTO.getAfId() == null)
			throw new Exception("Required parameter missing.");
		
		// 첨부파일 삭제
		attachFileMapper.deleteAttachFile(attachFileDTO);
		
		rs.put("result", AppConstant.RESULT_SUCCESS);
		return rs;
    }
}
