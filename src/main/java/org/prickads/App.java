package org.prickads;

import org.prickads.domain.Advertentie;
import org.prickads.domain.Categorie;
import org.prickads.domain.User;
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

        User user1 = new User("Hasan", "test123", "has@outlook.com", "adresstraat 123", "3422LG", "amersfoort", true);
        userDao.insert(user1);

        adservice.overigeDataAds();

    }
}
