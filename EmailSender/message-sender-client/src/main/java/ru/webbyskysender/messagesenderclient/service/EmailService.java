package ru.webbyskysender.messagesenderclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.webbyskysender.messagesenderclient.dto.Message;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    //mailSender светится красным
    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Message message){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(message.getMail());
            mailMessage.setSubject("Test");
            mailMessage.setText(message.getText());
            mailMessage.setFrom("murza.ran@gmail.com");

            mailSender.send(mailMessage);
            log.info("Email sent successfully to: {}", message.getMail());
        }catch (Exception e){
            log.error("Failed to send email to {}: {}", message.getMail(), e.getMessage(), e);
        }
    }
}
