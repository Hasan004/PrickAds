package org.prickads.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

    public void sendMail(String recepient, String naam) throws MessagingException {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "prickads@gmail.com";
        String password = "prickads12";

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient, naam);
        Transport.send(message);
    }

    private Message prepareMessage(Session session, String myAccountEmail, String recepient, String naam) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Aanmeldbevestiging " + naam);
            String htmlCode = "<h3 style=\"text-align:center\">Hi " + naam + ", bedankt voor het aanmelden bij PrickAds!</h3><br/> <p>Cool dat je " +
                    "je hebt aangemeld bij PrickAds. PrickAds is een advertentiesite die kopers en verkopers samenbrengt. " +
                    "Gebruikers kunnen er onder meer terecht voor het kopen of verkopen van tweedehands producten. <br/> <br/>" +
                    "Zoals je weet is PrickAds de beste advertentiesite van Nederland gekozen! De website is zeer eenvoudig in gebruik. " +
                    "<br/> <br/>Iedereen kan er een advertentie plaatsen of producten verkopen. " +
                    "U kunt nu inloggen met uw emailadres en wachtwoord. " +
                    "<br/> <br/>Dus waar wacht je nog op? <br/> <br/> <button type=\"button\" style=\"border:none;background-color:blue;color:white;display: inline-block;font-size: 16px;margin: 4px 2px;padding: 15px 32px;\" >Ga naar PrickAds</button></p>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ignored) {
        }
        return null;
    }
}
