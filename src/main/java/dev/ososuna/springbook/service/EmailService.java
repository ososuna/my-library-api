package dev.ososuna.springbook.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import dev.ososuna.springbook.configuration.PropertiesConfig;
import dev.ososuna.springbook.model.EmailDetails;

@Service
public class EmailService {

  private final PropertiesConfig propertiesConfig;
  private final JavaMailSender javaMailSender;

  public EmailService(PropertiesConfig propertiesConfig, JavaMailSender javaMailSender) {
    this.propertiesConfig = propertiesConfig;
    this.javaMailSender = javaMailSender;
  }

  public String sendSimpleMail(EmailDetails details) {
    try {
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setFrom(propertiesConfig.getMailUsername());
      mailMessage.setTo(details.getRecipient());
      mailMessage.setText(details.getMsgBody());
      mailMessage.setSubject(details.getSubject());
      javaMailSender.send(mailMessage);
      return "Mail Sent Successfully...";
    } catch (Exception e) {
      return "Error while Sending Mail";
    }
  }
}
