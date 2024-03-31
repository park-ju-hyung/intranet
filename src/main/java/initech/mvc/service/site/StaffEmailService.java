package initech.mvc.service.site;


import initech.mvc.mapper.StaffMapper;
import initech.mvc.vo.EmailVO;
import initech.mvc.vo.StaffVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class StaffEmailService {

    private final StaffMapper staffMapper;
    private final JavaMailSender StaffmailSender;
    private final SpringTemplateEngine templateEngine;
    private Map<String, String> verifyCodes = new ConcurrentHashMap<>();

    @Autowired
    public StaffEmailService(StaffMapper staffMapper, JavaMailSender StaffmailSender, SpringTemplateEngine templateEngine) {
        this.staffMapper = staffMapper;
        this.StaffmailSender = StaffmailSender;
        this.templateEngine = templateEngine;
    }


    // 이메일 저장
    @Transactional
    public void insertemail(EmailVO email) {
        staffMapper.insertemail(email);
    }

    // 이메일 중복 체크
    public boolean checkEmailExists(EmailVO email) {
        return staffMapper.existsemail(email) > 0;
    }

    // 기존 인증코드 삭제, 새 인증코드 삽입
    public void updateVerificationCode(EmailVO emailVO) throws MessagingException{
        // 기존 인증코드 삭제
        staffMapper.deleteverificationcodesbyemail(emailVO.getVerifyEmail());

        // 새 인증코드를 생성
        String newCode = RandomStringUtils.randomNumeric(6);
        emailVO.setVerifyCode(newCode);

        // 새 인증코드를 데이터베이스에 삽입합니다.
        staffMapper.insertverificationcode(emailVO);

        // 새 인증코드를 이메일로 발송합니다.
        sendSimpleEmail(emailVO);

    }

    public void deleteverificationcodesbyemail(String email) {
        staffMapper.deleteverificationcodesbyemail(email);
    }

    // 이메일 발송
    public void sendSimpleEmail(EmailVO email) throws MessagingException {

        // 인증코드 생성 및 매핑
        String verifycode = RandomStringUtils.randomNumeric(6);
        verifyCodes.put(email.getVerifyEmail(), verifycode);
        email.setVerifyCode(verifycode);

        // 이메일 생성 및 설정
        MimeMessage message = StaffmailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        // 이메일 안에 변수를 담게 해주는 역할
        Context context = new Context();
        context.setVariable("verifyCode", email.getVerifyCode());

        // 최종적으로 context 객체에 담긴 변수들을 emailTemplate 안에 넣어 최종적인 이메일 컨텐츠를 생성
        String htmlContent = templateEngine.process("emailTemplate", context);

        // 이메일 메세지의 세부 사항들을 설정하는 과정
        helper.setFrom("noreply@example.com");
        helper.setTo(email.getVerifyEmail());
        helper.setSubject("[ini]회원가입용 인증번호입니다.");
        helper.setText(htmlContent, true);

        StaffmailSender.send(message);
    }








}
