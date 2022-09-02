package com.kk.picturequizapi.domain.users.service;

import com.kk.picturequizapi.domain.users.utility.MailHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService{

    private final JavaMailSender mailSender;

    @Override
    public void mailSend(String email) {

        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(email);
            mailHandler.setSubject("Picture-QUIZ 이메일 인증 메일입니다.");
            mailHandler.setText("아령하세연",false);
            mailHandler.send();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
