package org.prickads.service;

import org.prickads.dao.UserDao;
import org.prickads.domain.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static org.prickads.SingleObjects.em;

public class UserService {
    Scanner scanner = new Scanner(System.in);
    UserDao userDao = new UserDao(em);

    public void registreren() {
        System.out.println("Wat leuk dat je PrickAds komt joinen!\nVoer uw naam in");
        String naam = scanner.nextLine();
        naam = naam.substring(0, 1).toUpperCase() + naam.substring(1);

        System.out.println("Maak een eigen password aan!");
        String password = scanner.nextLine();

        System.out.println("Hoi " + naam + ", Wat is je email?");
        String email = scanner.nextLine();

        System.out.println("Wat is je adres (straat + huisnummer)");
        String adres = scanner.nextLine();

        System.out.println("Wat is je postcode?");
        String postcode = scanner.nextLine();

        System.out.println("Wat is je woonplaats?");
        String woonplaats = scanner.nextLine();

        System.out.println("Gaat u akkoord met onze algemene voorwaarden?");
        String input = scanner.nextLine();
        boolean voorwaarde = input.trim().toLowerCase().equals("ja");

        User user = new User(naam, password, email, adres, postcode, woonplaats, voorwaarde);
        userDao.insert(user);
        System.out.println("Bevestigingsmail verzenden...");

        try {
            sendMail(user.getEmail(), user.getNaam());
            System.out.println("U heeft een bevestigingsmail ontvangen!");
        } catch (MessagingException e) {
            System.out.println("Niet gelukt!");
        }
    }

    public static void sendMail(String recepient, String naam) throws MessagingException {
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

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String naam) {
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
                    "<br/> <br/>Dus waar wacht je nog <br/> <br/> <button type=\"button\" style=\"border:none;background-color:blue;color:white;display: inline-block;font-size: 16px;margin: 4px 2px;padding: 15px 32px;\" >Ga naar PrickAds</button></p>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ignored) {
        }
        return null;
    }

    public User login() {
        System.out.println("Voer uw emailadres in!");
        String email = scanner.nextLine().trim().toLowerCase();

        System.out.println("Voer uw wachtwoord in!");
        String wachtwoord = scanner.nextLine();

        List<User> users = userDao.findAll();
        for (User user : users) {
            if (user.getEmail().toLowerCase().equals(email) && user.getPassword().equals(wachtwoord)) {
                System.out.println("U bent ingelogd!");
                return user;
            }
        }
        return null;
    }
}