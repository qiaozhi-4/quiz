package com.quiz.utils.email;

import com.quiz.exception.APIException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author XGeorge
 * @since 2024/7/14
 */
@Component
@RequiredArgsConstructor
public class EmailUtil {
    private final JavaMailSender mailSender;//发送邮件
    @Value("${spring.mail.username}")
    private String from;//获取yml文件配置

    /**
     * 发送邮件
     *
     * @param toEmail 邮件信息
     */
    public void commonEmail(ToEmail toEmail ) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(toEmail.getNickname() + "<" + from + ">");
        //谁要接收
        message.setTo(toEmail.getTos());
        //邮件标题
        message.setSubject(toEmail.getSubject());
        //邮件内容
        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
        } catch (MailException mailException) {
            throw new APIException("邮箱不存在!请输入正确的邮箱地址!");
        }
    }
}
