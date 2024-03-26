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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class StaffEmailService {

    private final JavaMailSender StaffmailSender;
    private final SpringTemplateEngine templateEngine;
    private Map<String, String> verifyCodes = new ConcurrentHashMap<>();

    @Autowired
    public StaffEmailService(JavaMailSender StaffmailSender, SpringTemplateEngine templateEngine) {
        this.StaffmailSender = StaffmailSender;
        this.templateEngine = templateEngine;
    }

    public void sendSimpleEmail(StaffVO staff) throws MessagingException {
        // 6자리 숫자 인증 코드를 생성합니다.
        String verify_code = RandomStringUtils.randomNumeric(6);
        // 생성된 코드를 이메일 주소와 매핑하여 저장합니다.
        verifyCodes.put(staff.getMember_email(), verify_code);

        MimeMessage message = StaffmailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("name", staff.getMember_name());
        context.setVariable("verify_code", verify_code);

        String htmlContent = templateEngine.process("emailTemplate", context);

        helper.setFrom("noreply@example.com");
        helper.setTo(staff.getMember_email());
        helper.setSubject("[ini]회원가입용 인증번호입니다.");
        helper.setText(htmlContent, true);

        StaffmailSender.send(message);
    }



}