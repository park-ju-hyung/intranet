package initech.mvc.service.site;


import initech.mvc.mapper.StaffMapper;
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
import java.util.concurrent.ConcurrentHashMap;


@Service
public class StaffEmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final ConcurrentHashMap<String, String> verifyCodes = new ConcurrentHashMap<>();



    @Autowired
    public StaffEmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendSimpleEmail(StaffVO staff) throws MessagingException {
        // 6자리 숫자 인증 코드를 생성합니다.
        String verificationCode = RandomStringUtils.randomNumeric(6);
        // 생성된 코드를 이메일 주소와 매핑하여 저장합니다.
        verifyCodes.put(staff.getMemberEmail(), verificationCode);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("name", staff.getMemberName());
        context.setVariable("verifyCode", verificationCode);

        // "verificationEmail"은 이메일 콘텐츠를 위한 타임리프 템플릿의 이름입니다.
        String htmlContent = templateEngine.process("emailTemplate", context);

        helper.setFrom("noreply@example.com");
        helper.setTo(staff.getMemberEmail());
        helper.setSubject("[ini]회원가입용 인증번호입니다.");
        helper.setText(htmlContent, true); // true를 설정하여 HTML 콘텐츠를 사용합니다.

        mailSender.send(message);
    }
    public boolean validateVerificationCode(String email, String code) {
        String validCode = verifyCodes.get(email);
        return validCode != null && validCode.equals(code);
    }

    // 이메일과 연관된 인증 코드를 가져오는 메소드(예: 유효성 검사 후)
    public String getVerificationCode(String email) {
        return verifyCodes.get(email);
    }

    // 인증 코드를 지우는 메소드(예: 인증 후)
    public void clearVerificationCode(String email) {
        verifyCodes.remove(email);
    }


}
