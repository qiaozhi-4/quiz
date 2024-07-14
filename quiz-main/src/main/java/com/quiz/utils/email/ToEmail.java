package com.quiz.utils.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author XGeorge
 * @since 2024/7/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToEmail implements Serializable {

    /**
     * 邮件接收方，可多人
     */
    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 发件人昵称
     */
    private String nickname;
}
