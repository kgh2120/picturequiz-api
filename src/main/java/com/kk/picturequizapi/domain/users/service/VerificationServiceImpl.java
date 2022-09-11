package com.kk.picturequizapi.domain.users.service;

import com.kk.picturequizapi.domain.users.utility.MailHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
@Service
public class VerificationServiceImpl implements VerificationService {

    private final JavaMailSender mailSender;

    private final StringRedisTemplate stringRedisTemplate;
    private final UserService userService;

    @Override
    public void mailSend(String email) {

        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(email);
            mailHandler.setSubject("Picture-QUIZ 이메일 인증 메일입니다.");

            String code = createEmailVerificationCode(email);

            mailHandler.setText(String.format(
                   "<div style=\" display: flex;\n" +
                           "    background-color: antiquewhite;\n" +
                           "    justify-content: center;\n" +
                           "    flex-direction: column;\n" +
                           "    align-items: center;\n" +
                           "    \">\n" +
                           "        <h2>이메일 인증 코드</span>\n" +
                           "        <div>%s</div>\n" +
                           "\n" +
                           "    </div>"
                    ,code),true);
            mailHandler.send();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void verifyCode(String email, String code) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String originCode = ops.get(email);

        if(originCode==null)
            throw new IllegalArgumentException("이메일 인증 코드가 만료되었습니다.");

        if(!code.equals(originCode))
            throw new IllegalArgumentException("인증 코드가 잘못되었습니다.");

        userService.registerEmailAccount(email);
    }

    private String createEmailVerificationCode(String email) {

        String verificationCode = UUID.randomUUID().toString().substring(0, 6);
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(email,verificationCode,5, TimeUnit.MINUTES);
        return verificationCode;
    }


}
