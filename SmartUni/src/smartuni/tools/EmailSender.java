/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartuni.tools;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.TransportStrategy;


/**
 *
 * @author Shalitha Suranga
 */
public class EmailSender {
    
    public static void sendEmail(String toName, String toEmail, String subject, String message) {
        Email email=new Email();
        email.setTextHTML(message);
        email.setSubject(subject);
        email.setFromAddress("{YOUR GMAIL}", "{YOUR NAME}");
        email.addRecipient(toName, toEmail, RecipientType.TO);
        Mailer m=new Mailer("smtp.gmail.com", 25, "{YOUR GMAIL}", "{YOUR GMAIL PASSWORD}", TransportStrategy.SMTP_TLS);
        
        m.sendMail(email);
    }
    
    public static void main(String[] args) {
            sendEmail("Test", "testemail@gmail.com", "test", "test");
    }
}
