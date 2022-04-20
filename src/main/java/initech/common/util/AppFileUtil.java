package initech.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppFileUtil {
	public static String getFileUniqKey(){
		String rs = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		rs = sdf.format(new java.util.Date());
		rs += AppCryptoUtil.encodeMD5(UUID.randomUUID().toString());
		return rs;
	}

	public static String getAfBundleId(){
		String rs = "";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		rs += formatter.format(new java.util.Date());
		rs += UUID.randomUUID().toString().replaceAll("-", "");

		return rs;
	}

	public static String getSaveFileNm(){
		String rs = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		rs = sdf.format(new java.util.Date());
		rs += AppCryptoUtil.encodeMD5(UUID.randomUUID().toString());
		return rs;
	}

	public static String getFilename(String fileName){
		int startIdx = 0;
		int endIdx = fileName.lastIndexOf("_");
		return  fileName.substring(startIdx, endIdx);
	}

	public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
		File f = new File(multipartFile.getOriginalFilename());
		f.createNewFile();
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(multipartFile.getBytes());
		fos.close();
		return f;
	}
	
	public static File convMultipartFileToFile(MultipartFile multipartFile) throws IOException {
		File f = new File(multipartFile.getOriginalFilename());
		multipartFile.transferTo(f);
		return f;
	}
	
	public static String getDownloadFileName(String fullFileName, HttpServletRequest request) {
		String rs = "";
		
		String fileName = fullFileName.substring(0, fullFileName.lastIndexOf("."));
		String fileExt = fullFileName.substring(fullFileName.lastIndexOf(".")+1, fullFileName.length());
		
		fileName = fileName + DateFormatUtils.format(new Date(), "_yyyyMMddHHmm.") + fileExt;
		
		try {
			String browser = request.getHeader("User-Agent");
	        if (browser.indexOf("MSIE") > -1) {
	            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	        } else if (browser.indexOf("Trident") > -1) {       // IE11
	            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	        } else if (browser.indexOf("Firefox") > -1) {
	            fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
	        } else if (browser.indexOf("Opera") > -1) {
	            fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
	        } else if (browser.indexOf("Chrome") > -1) {
	            StringBuffer sb = new StringBuffer();
	            for (int i = 0; i < fileName.length(); i++) {
	               char c = fileName.charAt(i);
	               if (c > '~') {
	                     sb.append(URLEncoder.encode("" + c, "UTF-8"));
	                       } else {
	                             sb.append(c);
	                       }
	                }
	                fileName = sb.toString();
	        } else if (browser.indexOf("Safari") > -1){
	            fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1")+ "\"";
	        } else {
	             fileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1")+ "\"";
	        }
	        
	        rs = fileName;
		}catch(Exception e) {
			log.error("{}", e);
		}
		
		return rs;
	}
}
