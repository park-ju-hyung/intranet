package initech.mvc.service.site;


import initech.mvc.mapper.StaffMapper;
import initech.mvc.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StaffEmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public StaffEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(StaffVO staff) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ptg0131@gmail.com"); // 보내는 이메일 주소 설정
        message.setTo(staff.getMemberEmail()); // 받는 이메일 주소 설정
        message.setSubject("[ini]회원가입용 인증번호입니다."); // 메일 제목
        message.setText("This is the test email content."); // 메일 내용
        mailSender.send(message);
    }
}
