package org.prickads;

import org.prickads.service.AdvertentieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.prickads.SingleObjects.*;

public class App {

    private final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {

        new VulDatabase().voerUit(em);

//        User user1 = new User("Hasan", "test123", "has@outlook.com", "adresstraat 123", "3422LG", "amersfoort", true);
//        User user1 = User.builder().naam("Hasan").password("test123").email("has@outlook.com").adres("adresstraat 123").postcode("7322LG").woonplaats("amersfoort").isAkkoord(true).build();

//        Advertentie advertentie = new Advertentie("Gloednieuwe MSI Laptop", categorie, "nieuwe laptop MSI niet gebruikt", 224.95, false, user1);
//        adDao.insert(advertentie);

        adservice.haalOpCategorie();

        adservice.overigeDataAds();

    }
}
