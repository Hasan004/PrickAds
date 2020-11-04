package org.prickads.service;
import org.prickads.domain.Categorie;

import java.util.List;
import static org.prickads.SingleObjects.*;

public class AdvertentieService {

    public void haalOpCategorie() {

        List<Categorie> categorieList = catDao.findAll();

        System.out.println("Om een advertentie te plaatsen moeten wij eerst weten in welk categorie je advertentie valt. U kunt kiezen uit: \n");
        for(Categorie categorie : categorieList){
            System.out.print(categorie.getNaam() + ", ");
        }

        long categorieID;
        do {
            String inputCategorie = scanner.nextLine();
            inputCategorie = inputCategorie.trim();

            switch (inputCategorie.toLowerCase()) {
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
            catDao.findByIdWithNamedQuery(categorieID);

        }while(categorieID == 0);
    }

    public void overigeDataAds(){
        System.out.println("Voer de naam in van je advertentie (*):");
        String inputNaam = scanner.nextLine();
        advertentie.setNaam(inputNaam);

        System.out.println("Voeg een (korte) beschrijving toe (*): ");
        String inputOmschrijving = scanner.nextLine();
        advertentie.setOmschrijving(inputOmschrijving);


    }

}
