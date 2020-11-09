package org.prickads;

import org.prickads.domain.Advertentie;
import org.prickads.domain.User;
import java.util.List;
import static org.prickads.SingleObjects.*;

public class App {

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
                                            advertentieHulpService.plaatsAd(line);
                                        break;
                                        case "2":
                                            adservice.saveAdvertentie(currentUser);
                                            break;
                                        case "3":
                                            if (advertentieHulpService.isAdvertentieLeeg(currentUser)) break;
                                            System.out.println("Welke advertentie wilt u wijzigen? (Id van advertentie invoeren!)");
                                            adservice.updateAds(advertentieHulpService.getAdvertentie(currentUser), currentUser.getUser_id());
                                            break;
                                        case "4":
                                            if (advertentieHulpService.isAdvertentieLeeg(currentUser)) break;
                                            System.out.println("Welke advertentie wilt u verwijderen? (Id van advertentie invoeren!)");
                                            adservice.deleteAd(advertentieHulpService.getAdvertentie(currentUser), currentUser.getUser_id());
                                            break;
                                        case "5":
                                            if (advertentieHulpService.isAdvertentieLeeg(currentUser)) break;
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
}
