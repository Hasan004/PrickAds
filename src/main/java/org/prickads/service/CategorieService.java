package org.prickads.service;

import org.prickads.domain.Categorie;

import java.util.List;

import static org.prickads.SingleObjects.catDao;
import static org.prickads.SingleObjects.scanner;

public class CategorieService {

    public long haalOpCategorie() {
        List<Categorie> categorieList = catDao.findAll();

        System.out.println("U kunt kiezen uit de volgende categoriën:");
        for (Categorie categorie : categorieList) {
            System.out.print(categorie.getNaam() + ", ");
        }
        System.out.println();

        long categorieID;
        do {
            System.out.println("uit welke categorie kiest u?");
            String inputCategorie = scanner.nextLine();
            inputCategorie = inputCategorie.trim().toLowerCase();

            switch (inputCategorie) {
                case "antiek":
                    categorieID = 1;
                    break;
                case "auto":
                    categorieID = 2;
                    break;
                case "boeken":
                    categorieID = 3;
                    break;
                case "dieren":
                    categorieID = 4;
                    break;
                case "elektronica":
                    categorieID = 5;
                    break;
                case "kleding":
                    categorieID = 6;
                    break;
                case "muziek":
                    categorieID = 7;
                    break;
                case "sport":
                    categorieID = 8;
                    break;
                case "vouchers":
                    categorieID = 9;
                    break;
                case "overige":
                    categorieID = 10;
                    break;
                default:
                    System.out.println("Uw ingevoerde categorie bestaat niet! Kies uit een van de opgesomde categoriën");
                    categorieID = 0;
            }

        } while (categorieID == 0);
        catDao.findById(categorieID);
        return categorieID;
    }
}
