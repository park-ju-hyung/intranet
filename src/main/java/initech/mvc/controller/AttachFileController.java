package initech.mvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import initech.common.util.AppFileUtil;
import initech.common.util.AppStringUtil;
import initech.mvc.dto.AttachFileDTO;
import initech.mvc.mapper.AttachFileMapper;
import initech.mvc.service.common.AttachFileService;
import initech.mvc.vo.AttachFileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AttachFileController {
	
	private final AttachFileService attachFileService ; 
    private final AttachFileMapper attachFileMapper;
    
	@Value("${upload.root}")	private String uploadRoot;
    
    @Value("${path.attach}")	private String pathAttach;
    @Value("${path.editor}")	private String pathEditor;
    @Value("${path.static}")	private String pathStatic;
    @Value("${path.image}")		private String pathImage;

    @ResponseBody
    @RequestMapping(value = {"/attach/file"}, method = {RequestMethod.POST})
    public Object fileUpload(HttpServletRequest request) throws Exception {
        return attachFileService.fileUpload(request);
    }
    
    @RequestMapping(value = {"/download/attach/{attachId}"}, method = {RequestMethod.GET})
    public void downloadAttachFile(
    		@PathVariable("attachId") String attachId
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
    	
    	AttachFileDTO afDTO = new AttachFileDTO();
    	afDTO.setAfId(attachId);
        
    	AttachFileVO afVO = attachFileMapper.selectAttachFileInfo(afDTO);
        String strePath = afVO.getFilePath();
        strePath = AppStringUtil.removeFilePathBlackList(strePath);
        String fullPath = strePath;
        String orginlFileNm = afVO.getFileNm();
        String saveNm = afVO.getSaveNm();

        InputStream in = null;
        OutputStream os = null;

        try {

            File file = new File(fullPath, saveNm);

            if (file.exists() == true) {
                in = new FileInputStream(file);
                os = response.getOutputStream();

                response.reset();
                String browser = request.getHeader("User-Agent");

                if (browser.indexOf("MSIE 5.5") != -1) {
                    response.setHeader("Content-Type", "doesn/matter; charset=UTF-8");
                    response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(orginlFileNm, "utf-8").replace("+", "%20"));
                } else {
                    response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
                    response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(orginlFileNm, "utf-8").replace("+", "%20"));
                }

                response.setHeader("Content-Transfer-Encoding", "binary;");
                response.setHeader("Content-Length", "" + file.length());
                response.setHeader("Pragma", "no-cache;");
                response.setHeader("Expires", "-1;");

                byte b[] = new byte[4096];
                int leng = 0;
                while ((leng = in.read(b)) > 0) {
                    os.write(b, 0, leng);
                }
                os.flush();
            }
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            if (in != null) in.close();
            if (os != null) os.close();
        }
    }
    
    
    @ResponseBody
    @RequestMapping(value = {"/attach/image"}, method = {RequestMethod.POST})
    public Object imageUpload(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> rsMap = new HashMap<String, String>();

        //파일 작업
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String datePath = DateFormatUtils.format(new Date(), "/yyyy-MM-dd");
        String phyPath = uploadRoot + pathImage + datePath;
        String webPath = pathStatic + pathImage + datePath;

        //디렉토리가 없는 경우 생성
        FileUtils.forceMkdir(new File(phyPath));

        Iterator<String> fileNames = multipartRequest.getFileNames();

        if (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile multipartFile = multipartRequest.getFile(fileName);

            if (multipartFile != null && !multipartFile.isEmpty()) {
            	String orginlFileNm = multipartFile.getOriginalFilename();
                String ext = FilenameUtils.getExtension(orginlFileNm);
                String saveNm = AppFileUtil.getSaveFileNm() + "." + ext;

                //임시 폴더로 저장
                File saveFile = new File(phyPath, saveNm);//저장 파일
                multipartFile.transferTo(saveFile);//저장 파일 쓰기
                
                rsMap = new HashMap<String, String>();
                rsMap.put("filePath", phyPath + "/" + saveNm);
                rsMap.put("imageUrl", webPath + "/" + saveNm);
                rsMap.put("orginlFileNm", orginlFileNm);
                rsMap.put("encptFileNm", saveNm);
                rsMap.put("fileSize", String.valueOf(multipartFile.getSize()));
            }
        }

        return rsMap;
    }
    
    @RequestMapping(value = {"/image/{imagename}"}, method = {RequestMethod.GET})
    public ResponseEntity<byte[]> imageView(@PathVariable("imagename") String imagename) throws IOException {
        String filePath = uploadRoot + pathImage;
		InputStream imageStream = new FileInputStream(filePath + "/" + imagename);
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping(value = {"/file/upload/editor/image"}, method = {RequestMethod.POST})
    public Object fileUploadEditorImage(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");

        //파일 작업
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String datePath = DateFormatUtils.format(new Date(), "/yyyy-MM-dd");
        String phyPath = uploadRoot + pathEditor + datePath;
        String webPath = pathStatic + pathEditor + datePath;

        //디렉토리가 없는 경우 생성
        FileUtils.forceMkdir(new File(phyPath));

        String msg = "이미지가 등록되었습니다.";//messageSourceAccessor.getMessage("editor.image.upload.success");
        Iterator<String> fileNames = multipartRequest.getFileNames();

        String saveNm = "";
        if (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile multipartFile = multipartRequest.getFile(fileName);

            if (multipartFile != null && !multipartFile.isEmpty()) {
                String orginlFileNm = multipartFile.getOriginalFilename();
                String ext = FilenameUtils.getExtension(orginlFileNm);
                saveNm = AppFileUtil.getSaveFileNm() + "." + ext;

                //임시 폴더로 저장
                File saveFile = new File(phyPath, saveNm);//저장 파일
                multipartFile.transferTo(saveFile);//저장 파일 쓰기
            }
        }

        String rs = "";
        rs += "<script type=\"text/javascript\">";
        rs += "	window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ", \"" + (webPath + "/" + saveNm) + "\", \"" + msg + "\");";
        rs += "</script>";

        return rs;
    }
    
    
}
