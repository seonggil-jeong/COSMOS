package com.cosmos.userservice.util;

import com.cosmos.userservice.dto.MailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class MailUtil {

    private final MailSender mailSender;

    @Value("${spring.mail.username}")
    private String myEmail;

    public void sendMail(MailDto mailDto) {
        log.info("### sendMail.MailUtil start !");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailDto.getTo());
        simpleMailMessage.setSubject(mailDto.getSubject());
        simpleMailMessage.setText(mailDto.getText());
        simpleMailMessage.setFrom(myEmail);
        simpleMailMessage.setReplyTo(myEmail);

        mailSender.send(simpleMailMessage);
        log.info("### sendMail.MailUtil end !");
    }
}
