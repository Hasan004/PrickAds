package org.prickads.service;

import org.prickads.domain.Advertentie;
import org.prickads.domain.User;

import java.util.List;
import static org.prickads.SingleObjects.*;

public class AdvertentieHulpService {

    public double getPrijs() {
        double prijs = 0;
        while (true) {
            try {
                System.out.println("Voeg een bijbehorende prijs toe: ");
                String inputPrijs = scanner.nextLine();
                prijs = Double.parseDouble(inputPrijs);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Onjuiste waarde ingevuld");
            }
        }
        return prijs;
    }

    public List<Advertentie> simpelAdsZoeken() {
        System.out.println("Op welk advertentienaam wilt u zoeken?");
        String zoeken = scanner.nextLine();
        return adDao.findByName(zoeken);
    }

    public boolean isAdvertentieLeeg(User user) {
        long userid = user.getUser_id();
        List<Advertentie> advertenties = adDao.findByUserId(userid);
        if (advertenties.isEmpty()) {
            System.out.println("U heeft geen opgeslagen advertenties");
            return true;
        }
        return false;
    }

    public long getAdvertentie(User user) {
        long userid = user.getUser_id();
        List<Advertentie> advertenties = adDao.findByUserId(userid);
        for (Advertentie advertentie : advertenties) {
            System.out.println(advertentie.getId() + " - " + advertentie.getNaam());
        }
        long advertentieId = 0;
        while (true) {
            try {
                String input = scanner.nextLine();
                advertentieId = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige waarde, probeer opnieuw");
            }
        }
        return advertentieId;
    }

    public void plaatsAd(String line) {
        if (adDao.findAll().isEmpty()) {
            System.out.println("Er zijn geen advertenties beschikbaar");
            return;
        }
        List<Advertentie> advertentieList = adDao.findAll();
        int count = 1;
        for (Advertentie ad : advertentieList) {
            if (!ad.getVerkocht()) {
                System.out.println(line + "\n(" + count + ") | Categorie: " + ad.getCategorie().getNaam() + " | Naam: " + ad.getNaam() + " | Omschrijving: " + ad.getOmschrijving() + " | Prijs: " + ad.getPrijs() + " | Verkoper: " + ad.getUser().getNaam() + "\n" + line);
                count++;
            }
        }
        System.out.println("Wilt u zoeken naar advertenties?");
        String terug = scanner.nextLine();
        int countZoek = 0;
        if (terug.trim().toLowerCase().equals("ja")) {
            List<Advertentie> advertenties = advertentieHulpService.simpelAdsZoeken();
            for (Advertentie ad : advertenties) {
                countZoek++;
                System.out.println(line + "\n(" + countZoek + ") | Categorie: " + ad.getCategorie().getNaam() + " | Naam: " + ad.getNaam() + " | Omschrijving: " + ad.getOmschrijving() + " | Prijs: " + ad.getPrijs() + " | Verkoper: " + ad.getUser().getNaam() + "\n" + line);
            }
            System.out.println();
        }
    }

}
