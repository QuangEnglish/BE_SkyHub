package com.company_management.service.impl;

import com.company_management.model.request.MailRequest;
import com.company_management.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;
    @Value("${upload.path}")
    private String uploadFilePath;

    @Override
    public void send(MailRequest mail) {

        try {
            log.info("Gửi mail tới: {}", mail.getToMail());
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setText(mail.getContent(), mail.isHtml());
            helper.setTo(mail.getToMail());
            helper.setSubject(mail.getTitle());

//            if (mail.getInlineImages() != null) {
//                for (InLineImageDto img : mail.getInlineImages()) {
//                    helper.addInline(
//                            img.getContentId(),
//                            img.getInputStreamSource(),
//                            img.getMimeType()
//                    );
//                }
//            }

            if (mail.getAttachmentName() != null) {
                File attachment = new File(uploadFilePath + "/" + mail.getAttachmentName());
                FileSystemResource source = new FileSystemResource(attachment);
                helper.addAttachment(mail.getAttachmentName(), source);
            }
            emailSender.send(message);

            // TODO: save to logistics mail info table
        } catch (Exception e) {
            log.error("Lỗi gửi mail tới: {}", mail.getToMail());
            e.printStackTrace();
        }
    }
}
