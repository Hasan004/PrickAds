package Service;

import Domain.Advertentie;
import Domain.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AdvertentieService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrickAds");
    private EntityManager em = emf.createEntityManager();
    Scanner scanner = new Scanner(System.in);

    final String[] product = {"Antiek", "Auto", "Boeken", "Dieren", "Elektronica", "Kleding", "Muziek", "Sport", "Vouchers", "Overige"};

    StringBuilder builder = new StringBuilder();
    Advertentie advertentie = new Advertentie();
    Categorie categorie = new Categorie();

    public void haalOpCategorie() {
        String categorieNaam;

        for (String value : product) {
            builder.append(value).append(", ");
        }
        System.out.println("Om een advertentie te plaatsen moeten wij eerst weten in welk categorie je advertentie valt. U kunt kiezen uit: \n"
                + builder.toString());

        do {
            String inputCategorie = scanner.nextLine();
            inputCategorie = inputCategorie.trim();

            switch (inputCategorie.toLowerCase()) {
                case "antiek":
                    categorieNaam = "Antiek";
                    break;
                case "auto":
                    categorieNaam = "Auto";
                    break;
                case "boeken":
                    categorieNaam = "Boeken";
                    break;
                case "dieren":
                    categorieNaam = "Dieren";
                    break;
                case "elektronica":
                    categorieNaam = "Elektronica";
                    break;
                case "kleding":
                    categorieNaam = "Kleding";
                    break;
                case "muziek":
                    categorieNaam = "Muziek";
                    break;
                case "sport":
                    categorieNaam = "Sport";
                    break;
                case "vouchers":
                    categorieNaam = "Vouchers";
                    break;
                case "overige":
                    categorieNaam = "Overige";
                    break;
                default:
                    System.out.println("Uw ingevoerde categorie bestaat niet! Kies uit een van de opgesomde categoriën");
                    categorieNaam = null;
            }
            categorie.setNaam(categorieNaam);
        }while(categorieNaam == null);
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
