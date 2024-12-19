package org.jsp.cda.config;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class MailConfig {
	@Bean
	JavaMailSender javaMailSender() {
		JavaMailSenderImpl jmsi=new  JavaMailSenderImpl();
		jmsi.setPort(587);
		jmsi.setHost("smtp.gmail.com");
		jmsi.setUsername("tssnltejaswini2223@gmail.com");
		jmsi.setPassword("clek wryu slal mvng");	
		Properties props=jmsi.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return jmsi;
	}
}
