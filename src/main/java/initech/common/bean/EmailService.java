package initech.common.bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import initech.common.util.AppStringUtil;
import initech.mvc.dto.EmailDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailService {

	private String fileName;
	
	@Autowired
	private JavaMailSender mailSender;

	public EmailService(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * <pre>
	 * 메일을 전송한다.
	 * </pre>
	 *
	 * @param mail
	 * 			메일 제목, 내용, 송신자, 수신자 등의 정보를 담고 있는
	 * 			MailDto 객체
	 * @return
	 * 			메일 전송의 성공, 실패 여부
	 */
	public boolean sendMail(EmailDTO mail) throws Exception {

		return this.sendMail(mail, true);
	}

	/**
	 * <pre>
	 * 메일을 전송한다.
	 * </pre>
	 *
	 * @param mail
	 * 			메일 제목, 내용, 송신자, 수신자 등의 정보를 담고 있는
	 * 			MailDto 객체
	 * @return
	 * 			메일 전송의 성공, 실패 여부
	 */
	public boolean sendMail(EmailDTO mail, boolean ishtml) throws Exception {
		try{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "EUC-KR");
			helper.setFrom(mail.getFrom());
			helper.setSubject(mail.getSubject());
			helper.setText(this.createMailContent(mail, ishtml), ishtml);

			for (int i=0;i<mail.getReceiverTo().size();i++) {
				log.debug("to: " + mail.getReceiverTo().get(i));
				helper.setTo((String)mail.getReceiverTo().get(i));
				mailSender.send(message);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

    /**
     * <pre>
	 * 알림 메일의 mail content 생성
     * </pre>
     *
     * @param mail
     * @return
     * @throws Exception
     */
    private String createMailContent(EmailDTO mail) throws Exception
    {
    	StringBuffer mailContent = new StringBuffer();
    	FileReader reader = null;
    	BufferedReader bReader = null;
    	
    	try
    	{
    		//메일 디자인 Load
    		ClassPathResource resource = new ClassPathResource(this.fileName);

    		bReader = new BufferedReader(new InputStreamReader(resource.getInputStream(),"UTF-8"));

    		String lnString = "";
    		boolean isHeader = false;
    		boolean isInfo = false;
    		boolean isLine = false;
    		boolean isEtc = false;
    		boolean isFooter = false;
    		StringBuffer header = new StringBuffer();
    		StringBuffer info = new StringBuffer();
    		StringBuffer line = new StringBuffer();
    		StringBuffer etc = new StringBuffer();
    		StringBuffer footer = new StringBuffer();

    		while ((lnString = bReader.readLine()) != null)
    		{
    			//Header 정보 설정
    			if (lnString.indexOf("<!-- HeaderStart -->") >= 0)
    			{
    				isHeader = true;
    			}
    			if (isHeader)
    			{
    				lnString = AppStringUtil.replace(lnString, "<!-- HeaderTitle -->", mail.getHearderTitle());
    				header.append(lnString).append("\n");
    			}
    			if (lnString.indexOf("<!-- HeaderEnd -->") >= 0)
    			{
    				isHeader = false;
    			}

    			//info 정보 설정
    			if (lnString.indexOf("<!-- InformationStart -->") >= 0)
    			{
    				isInfo = true;
    			}
    			if (isInfo)
    			{
    				info.append(lnString).append("\n");
    			}
    			if (lnString.indexOf("<!-- InformationEnd -->") >= 0)
    			{
    				isInfo = false;
    			}

    			//구분 Line 설정
    			if (lnString.indexOf("<!-- LineStart -->") >= 0)
    			{
    				isLine = true;
    			}
    			if (isLine)
    			{
    				line.append(lnString).append("\n");
    			}
    			if (lnString.indexOf("<!-- LineEnd -->") >= 0)
    			{
    				isLine = false;
    			}

    			//footer 정보 설정
    			if (lnString.indexOf("<!-- FooterStart -->") >= 0)
    			{
    				isFooter = true;
    			}
    			if (isFooter)
    			{
    				lnString = AppStringUtil.replace(lnString, "<!-- FooterNotice -->", mail.getFooterNotice());
    				footer.append(lnString).append("\n");
    			}
    			if (lnString.indexOf("<!-- FooterEnd -->") >= 0)
    			{
    				isFooter = false;
    			}
    		}


    		//메일 본문 생성
    		//사용 키 : DocumentName	Title: 문서명
			//사용 키 : DocumentId		Title: 문서 번호
			//사용 키 : ReceiptDate	Title: 수신 일시
			//사용 키 : SenderName		Title: 발급(승인)기관
			//사용 키 : IssueId		Title: 발급(승인)번호
			//사용 키 : ApplicationId	Title: 신청 문서 번호
			//사용 키 : Description	Title: 비고 - UserName과 Description은 mail design을 load 할때 설정
			//사용 키 : lcNum			Title: L/C 번호

    		/** 
    		 * DocumentID 일때 URL 정보도 링크하여  함께 넣는다.
    		 * url정보 (docUrl)는 DB에서 가져온다.
    		 */
    		mailContent.append(header);
    		for (int i=0;i<mail.getMessageMapSize();i++)
    		{
    			String title = mail.getMessageTitle(i);
    			String value = mail.getMessageValue(i);

        		String tmpInfo = info.toString();
        		tmpInfo = AppStringUtil.replace(tmpInfo, "<!-- InformationTitle -->", title);
        		if("답변내용".equals(title)) {
        			tmpInfo = AppStringUtil.replace(tmpInfo, "<!-- InformationValue -->", "<div style=\"clear:both;\"></div><div style=\"display:inline-block;padding-left: 64px;\">"+value+"</div>");
        		}else {
        			tmpInfo = AppStringUtil.replace(tmpInfo, "<!-- InformationValue -->", value);
        		}
        		
        		mailContent.append(tmpInfo);

        		if (i<mail.getMessageMapSize()-1)
        		{
        			mailContent.append(line);
        		}
    		}

    		mailContent.append(footer);

    	}
    	finally
    	{
    		try
    		{
	    		if (reader != null)
	    		{
	    			reader.close();
	    		}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}

    		try
    		{
	    		if (bReader != null)
	    		{
	    			bReader.close();
	    		}
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}

    	return mailContent.toString();
    }

    /**
     * <pre>
	 * 알림 메일의 mail content 생성
     * </pre>
     *
     * @param mail
     * @return
     * @throws Exception
     */
    private String createMailContent(EmailDTO mail, boolean ishtml) throws Exception
    {
    	if (ishtml) {
    		return this.createMailContent(mail);
    	} else {
    		StringBuffer mailContent = new StringBuffer();
    		mailContent.append(mail.getHearderTitle());
    		mailContent.append("\n\n");
    		mailContent.append(mail.getMessage());
    		return mailContent.toString();
    	}
    }
}