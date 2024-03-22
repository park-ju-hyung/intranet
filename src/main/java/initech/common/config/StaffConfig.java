package initech.common.config;

import initech.common.bean.EmailService;
import initech.common.bean.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Slf4j
public class StaffConfig {

	@Bean
	public JavaMailSender StaffmailSender() {
		JavaMailSenderImpl StaffmailSender = new JavaMailSenderImpl();
		StaffmailSender.setHost("smtp.gmail.com");
		StaffmailSender.setUsername("ptg0131@gmail.com");
		StaffmailSender.setPassword("bdewagwozihrtcsh");
		StaffmailSender.setPort(587);
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		StaffmailSender.setJavaMailProperties(javaMailProperties);
		
		return StaffmailSender;
	}
	

}
