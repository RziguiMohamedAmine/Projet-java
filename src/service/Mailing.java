/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Properties;
import java.util.logging.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author R I B
 */

public class Mailing {
public static void sendMail(String reception) throws Exception{
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
       
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
       
        String myAccountEmail = "sratsirine4@gmail.com";
        String password = "sirinesrat4";
       
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
       
        Message message = prepareMessage(session, myAccountEmail, reception);
       
        Transport.send(message);
        System.out.println("Message send successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String reception) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(reception));
            message.setSubject(" Reponse ");
            message.setText("Bonjour Monsieur(Madame), \n");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Mailing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}}
