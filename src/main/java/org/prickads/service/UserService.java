package org.prickads.service;

import org.prickads.dao.UserDao;
import org.prickads.domain.User;

import javax.mail.*;
import java.util.List;
import java.util.Scanner;

import static org.prickads.SingleObjects.*;

public class UserService {

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

        boolean voorwaarde;
        while(true) {
            System.out.println("Gaat u akkoord met onze algemene voorwaarden?");
            String input = scanner.nextLine();
            if(input.trim().toLowerCase().equals("ja")){
                voorwaarde = true;
                break;
            }else{
                System.out.println("Als u niet akkoord gaat met onze voorwaarden kunt u geen account aanmaken");
            }
        }

        User user = new User(naam, password, email, adres, postcode, woonplaats, voorwaarde);
        userDao.insert(user);
        System.out.println("Bevestigingsmail verzenden...");

        try {
            mailService.sendMail(user.getEmail(), user.getNaam());
            System.out.println("U heeft een bevestigingsmail ontvangen!");
        } catch (MessagingException e) {
            System.out.println("Niet gelukt!");
        }
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