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

    // 이메일 발송
    public void sendSimpleEmail(EmailVO email) throws MessagingException {
        // 6자리 숫자 인증 코드를 생성합니다.
        String verifycode = RandomStringUtils.randomNumeric(6);
        // 생성된 코드를 이메일 주소와 매핑하여 저장합니다.
        verifyCodes.put(email.getVerifyEmail(), verifycode);

        // 여기에 추가: EmailVO 객체에 인증번호를 설정합니다.
        email.setVerifyCode(verifycode);

        MimeMessage message = StaffmailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("verifyCode", email.getVerifyCode());

        String htmlContent = templateEngine.process("emailTemplate", context);

        helper.setFrom("noreply@example.com");
        helper.setTo(email.getVerifyEmail());
        helper.setSubject("[ini]회원가입용 인증번호입니다.");
        helper.setText(htmlContent, true);

        StaffmailSender.send(message);
    }


    // 이메일 중복 체크
    public boolean checkEmailExists(EmailVO email) {
        return staffMapper.existsemail(email) > 0;
    }




}
