import Dao.AdvertentieDao;
import Dao.CategorieDao;
import Dao.UserDao;
import Domain.Categorie;
import Domain.User;
import Service.AdvertentieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    private final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrickAds");
        EntityManager em = emf.createEntityManager();

        UserDao userDao = new UserDao(em);
        AdvertentieDao adDao = new AdvertentieDao(em);
        CategorieDao catDao = new CategorieDao(em);

//        User user1 = new User("Hasan", "test123", "has@outlook.com", "adresstraat 123", "3422LG", "amersfoort", true);
        User user1 = User.builder().naam("Hasan").password("test123").email("has@outlook.com").adres("adresstraat 123").postcode("7322LG").woonplaats("amersfoort").isAkkoord(true).build();

        Categorie categorie = new Categorie("Laptops");

//        Advertentie advertentie = new Advertentie("Gloednieuwe MSI Laptop", categorie, "nieuwe laptop MSI niet gebruikt", 224.95, false, user1);
//        adDao.insert(advertentie);

        AdvertentieService adcontroller = new AdvertentieService();
        adcontroller.haalOpCategorie();

        adcontroller.overigeDataAds();

    }
}
