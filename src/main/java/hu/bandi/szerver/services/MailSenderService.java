package hu.bandi.szerver.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    private static final Logger logger = LoggerFactory.getLogger(MailSenderService.class);

    JavaMailSender mailSender;

    public MailSenderService(final JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private void sendMessage(final SimpleMailMessage mmessage) {
        if (mmessage == null) {
            return;
        }
        try {
            mailSender.send(new MimeMessagePreparator() {
                @Override
                public void prepare(final javax.mail.internet.MimeMessage mimeMessage) throws Exception {
                    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    message.setFrom(""); //KITÖLTENI AZ EMAILCÍMMEL
                    message.setTo(mmessage.getTo());
                    message.setSubject(mmessage.getSubject());
                }

            });
        } catch (final org.springframework.mail.MailSendException e) {
            logger.info("Invalid emailadress: " + mmessage.getTo());
            logger.error(e.getMessage());
        }
    }

    public void sendForgetPassword(final String sendToEmailAddress, final String newPassword) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("");//KITÖLTENI AZ EMAILCÍMMEL
        message.setTo(sendToEmailAddress);
        //@formatter:off
        message.setText("<a>Dear Customer,<br>" +
                "You get this email because you forget your password!<br>" +
                "Your new password:<br>" + newPassword +
                "<br>" +
                "If you didn't wanted to regenerate your password, please contact us!<br>" +
                "Thanks</a>");
        message.setSubject("Forgotten password");
        //@formatter:on
        sendMessage(message);
    }
}
