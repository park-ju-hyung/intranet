package initech.common.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import initech.common.bean.EmailService;
import initech.common.bean.ExcelService;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppConfig {

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setUsername("bahn1034@gmail.com");
		mailSender.setPassword("spbwnbjbiygfmfqx");
		mailSender.setPort(587);
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		mailSender.setJavaMailProperties(javaMailProperties);
		
		return mailSender;
	}
	
	@Bean
	public EmailService emailService() {
		return new EmailService("email/mailForm.html");
	}
	
	@Bean
	public ExcelService excelService() {
		return new ExcelService();
	}
	
}
