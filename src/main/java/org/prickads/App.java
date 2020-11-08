package org.prickads;

import org.prickads.domain.Advertentie;
import org.prickads.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.prickads.SingleObjects.*;

public class App {

    private final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {

        User user = new User("he", "he", "he", "he", "he", "he", true);
        User user2 = new User("rt", "rt", "rt", "rt", "rt", "rt", true);
        userDao.insert(user);
        userDao.insert(user2);

        new VulDatabase().voerUit();

        System.out.println("Welkom bij PrickAds, de nummer 1 advertentiesite van Nederland!");

        while (true) {
            String line = "--------------------------------------------------------------------------------";
            System.out.println(line + "\n(1) - Registreren | (2) - Inloggen | (x) - Exit\n" + line);
            String output = scanner.nextLine();
            switch (output) {
                case "1":
                    userService.registreren();
                    break;
                case "2":
                    boolean gelukt = true;
                    while (gelukt) {
                        try {
                            User currentUser = userService.login();
                            if (currentUser.getNaam() != null) {
                                gelukt = false;
                                boolean stop = true;
                                while (stop) {
                                    System.out.println("ADVERTENTIE MENU");
                                    line = "---------------------------------------------------------------------------------------------------------------------------------------------------------------";
                                    System.out.println(line + "\n(1) - Tijdline! | (2) - Advertentie plaatsen | (3) - Advertentie updaten | (4) - Advertentie verwijderen | (5) - Laat mijn Advertenties zien | (x) - Uitloggen\n" + line);
                                    String input = scanner.nextLine();
                                    switch (input) {
                                        case "1":
                                            if (adDao.findAll().isEmpty()) {
                                                System.out.println("Er zijn geen advertenties beschikbaar");
                                                break;
                                            }
                                            List<Advertentie> advertentieList = adDao.findAll();
                                            int count = 1;
                                            for (Advertentie ad : advertentieList) {
                                                count++;
                                                if (!ad.getVerkocht()) {
                                                    System.out.println(line + "\n(" + count + ") | Categorie: " + ad.getCategorie().getNaam() + " | Naam: " + ad.getNaam() + " | Omschrijving: " + ad.getOmschrijving() + " | Prijs: " + ad.getPrijs() + " | Verkoper: " + ad.getUser().getNaam() + "\n" + line);
                                                }
                                            }
                                            System.out.println("Wilt u zoeken naar advertenties?");
                                            String terug = scanner.nextLine();
                                            int countZoek = 0;
                                            if (terug.trim().toLowerCase().equals("ja")) {
                                                List<Advertentie> advertenties = adservice.simpelAdsZoeken();
                                                for (Advertentie ad : advertenties) {
                                                    countZoek++;
                                                    System.out.println(line + "\n(" + countZoek + ") | Categorie: " + ad.getCategorie().getNaam() + " | Naam: " + ad.getNaam() + " | Omschrijving: " + ad.getOmschrijving() + " | Prijs: " + ad.getPrijs() + " | Verkoper: " + ad.getUser().getNaam() + "\n" + line);
                                                }
                                                break;
                                            }
                                            break;
                                        case "2":
                                            adservice.overigeDataAds(currentUser);
                                            break;
                                        case "3":
                                            if (isAdvertentieLeeg(currentUser)) break;
                                            System.out.println("Welke advertentie wilt u wijzigen? (Id van advertentie invoeren!)");
                                            adservice.updateAds(getAdvertentie(currentUser), currentUser.getUser_id());
                                            break;
                                        case "4":
                                            if (isAdvertentieLeeg(currentUser)) break;
                                            System.out.println("Welke advertentie wilt u verwijderen? (Id van advertentie invoeren!)");
                                            adservice.deleteAd(getAdvertentie(currentUser), currentUser.getUser_id());
                                            break;
                                        case "5":
                                            if (isAdvertentieLeeg(currentUser)) break;
                                            List<Advertentie> advertenties = adDao.findByUserId(currentUser.getUser_id());
                                            System.out.println("Dit zijn uw opgeslagen advertenties");
                                            for (Advertentie advertentie : advertenties) {
                                                System.out.println(advertentie.getId() + " - " + advertentie.getNaam());
                                            }
                                            break;
                                        case "x":
                                            System.out.println("U bent uitgelogd");
                                            stop = false;
                                            break;
                                        default:
                                            System.out.println("Ongeldige keuze, probeer opnieuw");
                                    }
                                }
                            }
                        } catch (NullPointerException e) {
                            System.out.println("Uw inloggegevens kloppen niet!");
                            gelukt = false;
                        }
                    }
                    break;
                case "x":
                    System.exit(1);
                default:
                    System.out.println("Ongeldige waarde, probeer opnieuw!");
            }
        }
    }

    private boolean isAdvertentieLeeg(User user) {
        long userid = user.getUser_id();
        List<Advertentie> advertenties = adDao.findByUserId(userid);
        if (advertenties.isEmpty()) {
            System.out.println("U heeft geen opgeslagen advertenties");
            return true;
        }
        return false;
    }

    private long getAdvertentie(User user) {
        long userid = user.getUser_id();
        List<Advertentie> advertenties = adDao.findByUserId(userid);
        for (Advertentie advertentie : advertenties) {
            System.out.println(advertentie.getId() + " - " + advertentie.getNaam());
        }
        long advertentieId = 0;
        boolean gelukt = true;
        while (gelukt) {
            try {
                String input = scanner.nextLine();
                advertentieId = Long.parseLong(input);
                gelukt = false;
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige waarde, probeer opnieuw");
            }
        }
        return advertentieId;
    }
}
