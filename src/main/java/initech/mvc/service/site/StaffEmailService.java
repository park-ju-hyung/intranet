package initech.mvc.service.site;


import initech.mvc.mapper.StaffMapper;
import initech.mvc.vo.StaffVO;
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


@Service
public class StaffEmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public StaffEmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendSimpleEmail(StaffVO staff) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        // UTF-8 인코딩을 설정합니다.
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariable("staff", staff);
        // 타임리프 템플릿에서 사용할 변수를 추가합니다.
        // 예를 들면, context.setVariable("name", staff.getMemberName());

        String htmlContent = templateEngine.process("emailTemplate", context);

        helper.setFrom("ptg0131@gmail.com");
        helper.setTo(staff.getMemberEmail());
        helper.setSubject("[ini]회원가입용 인증번호입니다.");
        helper.setText(htmlContent, true); // true를 설정하여 HTML을 사용하도록 합니다.

        mailSender.send(message);
    }
}
