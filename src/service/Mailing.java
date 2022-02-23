/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Billet;
import java.util.logging.Level;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Houssem Charef
 */
public class Mailing {

    public Mailing() {
    }

    public void envoyerMail(String recepient, Billet billet) {
        String email = "formulamailone@gmail.com";
        String password = "formulaone1_";

        System.out.println("Preparing to send email");
        try {

            Properties properties = new Properties();

            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });
            Message message = prepareMessage(session, email, recepient, billet);
            Transport.send(message);
        } catch (MessagingException ex) {
            java.util.logging.Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String email, String recepient, Billet billet) {
        String htmlCode;
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(email));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
            message.setSubject("billet match");

            htmlCode = "<!doctype html>\n"
                    + "<html lang=\"en-US\">\n"
                    + "\n"
                    + "<head>\n"
                    + "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n"
                    + "    <title>mot de passe oublié</title>\n"
                    + "    <meta name=\"description\" content=\"billet details \">\n"
                    + "    <style type=\"text/css\">\n"
                    + "        a:hover {text-decoration: underline !important;}\n"
                    + "    </style>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n"
                    + " <p>" + billet + "</p>"
                    + "</html>";

            message.setContent(htmlCode, "text/html");
            return message;

        } catch (AddressException ex) {
            java.util.logging.Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            java.util.logging.Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
