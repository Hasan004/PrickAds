package org.prickads.service;

import org.prickads.domain.Advertentie;
import org.prickads.domain.Categorie;
import org.prickads.domain.User;

import static org.prickads.SingleObjects.*;

public class AdvertentieService {

    public void saveAdvertentie(User currentUser) {
        long id = categorieService.haalOpCategorie();
        Categorie catAanmakenAd = catDao.findById(id);

        System.out.println("Voer de titel in van je advertentie (*):");
        String inputNaam = scanner.nextLine();

        System.out.println("Voeg een (korte) beschrijving toe (*): ");
        String inputOmschrijving = scanner.nextLine();

        boolean inputIsVerkocht = false;

        double prijs = advertentieHulpService.getPrijs();

        Advertentie advertentie = new Advertentie(inputNaam, catAanmakenAd, inputOmschrijving, prijs, inputIsVerkocht, currentUser);
        adDao.insert(advertentie);
        System.out.println("Succesvol opgeslagen van uw advertentie");
    }

    public void updateAds(long adId, long userId) {
        Advertentie advertentie = adDao.findById(userId, adId);

        if (advertentie == null) {
            System.out.println("Er bestaat geen data voor de opgegeven waarde!");
        } else {
            System.out.println("Uw advertentiegegevens zijn:");

            String line = "-----------------------------------------------------------------------------------------------------------------------------------";
            String verkocht = advertentie.getVerkocht() ? "Ja" : "Nee";
            System.out.println(line + "\n(1) - Naam: " + advertentie.getNaam() + " | (2) - Omschrijving: " + advertentie.getOmschrijving() + " | (3) - Prijs: " + advertentie.getPrijs() + " | (4) - Categorie: " + advertentie.getCategorie().getNaam() + " | (5) - Verkocht?: " + verkocht + "\n" + line);
            boolean wijzigen = true;

            while (wijzigen) {
                System.out.println("Welke gegevens wilt u wijzigen? (Typ het bijbehorende nummer in van de advertentiegegevens, (x) = terug)");
                String output1 = scanner.nextLine();
                output1 = output1.trim().toLowerCase();
                String bevestiging1 = "U heeft ervoor gekozen om de gegeven met nummer " + output1 + " te veranderen. Hoe wilt u het wijzigen?";
                String bevestiging2 = "Uw wijziging is opgeslagen";

                switch (output1) {
                    case "1":
                        System.out.println(bevestiging1);
                        advertentie.setNaam(scanner.nextLine());
                        System.out.println(bevestiging2);
                        break;
                    case "2":
                        System.out.println(bevestiging1);
                        advertentie.setOmschrijving(scanner.nextLine());
                        System.out.println(bevestiging2);
                        break;
                    case "3":
                        System.out.println(bevestiging1);
                        advertentie.setPrijs(advertentieHulpService.getPrijs());
                        System.out.println(bevestiging2);
                        break;
                    case "4":
                        System.out.println("U heeft ervoor gekozen om de gegeven met nummer " + output1 + " te veranderen.");
                        advertentie.setCategorie(catDao.findById(categorieService.haalOpCategorie()));
                        System.out.println(bevestiging2);
                        break;
                    case "5":
                        System.out.println("Is uw advertentie verkocht?");
                        String verkoop = scanner.nextLine();
                        boolean isVerkocht = verkoop.toLowerCase().trim().equals("ja");
                        advertentie.setVerkocht(isVerkocht);
                        System.out.println(bevestiging2);
                        break;
                    case "x":
                        System.out.println("Terug naar Menu...");
                        wijzigen = false;
                        break;
                    default:
                        System.out.println("Ongeldig nummer, probeer opnieuw!");
                }
            }
            adDao.update(advertentie);
        }
    }

    public void deleteAd(long adId, long userId) {
        Advertentie advertentie = adDao.findById(userId, adId);
        if (advertentie == null) {
            System.out.println("Het door u ingevoerde id bestaat niet");
        } else {
            System.out.println("weet je zeker dat je de advertentie met id: " + adId + " wilt verwijderen?");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("ja")) {
                Advertentie ad = adDao.findById(adId);
                adDao.remove(ad);
            }
        }
    }
}
