package initech.common.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppCryptoUtil {
	//AES256 암호화
  	public static String encodeAES256(String key, String str){
  		String rs = "";
  		
  		if(key == null){
  			return "";
  		}
  		
  		key = key+"________________";
  		
  		try{
  			AES256 aes256 = new AES256(key);
  			rs = aes256.aesEncode(str);
  		}catch(Exception e){
  			log.error("{}", e);
  		}
  		
  		return rs;
  	}
  	
  	
  	//AES256 복호화
  	public static String decodeAES256(String key, String str){
  		String rs = "";
  		
  		if(key == null){
  			return "";
  		}
  		
  		key = key+"________________";
  		
  		try{
  			AES256 aes256 = new AES256(key);
  			rs = aes256.aesDecode(str);
  		}catch(Exception e){
  			log.error("{}", e);
  		}
  		
  		return rs;
  	}
    
	//MD5 암호화
	public static String encodeMD5(String str){
		String result = "";
		
		try{
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(str.getBytes()); 
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			result = sb.toString();
		}catch(Exception e){
			 log.error(e.toString());
		}
		return result;
	}
	
	//SHA-256
	public static String encodeSHA256(String str){
		String result = "";
		
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256"); 
			md.update(str.getBytes()); 
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			result = sb.toString();
		}catch(Exception e){
			 log.error(e.toString());
		}
		return result;
	}
	
	//base64 암호화
	public static String encodeBase64(String str){
		String rs = "";
		try{
			byte[] encoded = Base64.encodeBase64(str.getBytes());
			rs = new String(encoded);
		}catch(Exception e){
			log.error(e.toString());
		}
		
		return rs;
	}
	
	//base64 복호화
	public static String decodeBase64(String base64Str){
		String rs = "";
		try{
			byte[] decoded = Base64.decodeBase64(base64Str.getBytes());
			rs = new String(decoded);
		}catch(Exception e){
			log.error(e.toString());
		}
		
		return rs;
	}
}
