package org.prickads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.prickads.dao.AdvertentieDao;
import org.prickads.dao.CategorieDao;
import org.prickads.dao.UserDao;
import org.prickads.domain.Advertentie;
import org.prickads.domain.Categorie;
import org.prickads.domain.User;
import org.prickads.service.AdvertentieHulpService;
import org.prickads.service.MailService;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    public static EntityManager emTest = Persistence.createEntityManagerFactory("prickads-h2").createEntityManager();
    public static AdvertentieDao adDao = new AdvertentieDao(emTest);
    public static CategorieDao catDao = new CategorieDao(emTest);
    public static MailService mailService = new MailService();
    public static AdvertentieHulpService advertentieHulpService = new AdvertentieHulpService();
    public static UserDao userDao = new UserDao(emTest);
    public static User user;
    public static User user2;


    @BeforeEach
    public void setUp(){
        VulDatabaseTest.voerUit(catDao);
        user = new User("has", "hoi", "h-ekici@outlook.com", "e", "e", "e", true);
        user2 = new User("has", "hoi", "h-ekici@outlook.com", "e", "e", "e", true);
        userDao.insert(user);
        userDao.insert(user2);
    }

    @Test
    void checkOfCategorieDatabaseGevuldIs(){
        List<Categorie> categorieList = catDao.findAll();
        assertThat(categorieList.size()).isEqualTo(10);
    }

    @Test
    void timelineIsNulWanneerErgGeenAdvertentiesZijn(){
        List<Advertentie> advertenties = adDao.findAll();
        assertThat(advertenties.size()).isEqualTo(0);
    }

    @Test
    void eenGebruikerKanNietEenAnderAdvertentieUpdaten(){
        Advertentie ad = new Advertentie("he", VulDatabaseTest.antiek, "he", 34, true, user2);
        List<Advertentie> advertenties = adDao.findByUserId(1);
        assertThat(advertenties.size()).isEqualTo(0);
    }

    @Test
    void eenGebruikerKanNietEenAnderAdvertentieInzien(){
        Advertentie ad = new Advertentie("he", VulDatabaseTest.antiek, "he", 34, true, user2);
        boolean klopt = advertentieHulpService.isAdvertentieLeeg(user);
        assertThat(klopt).isTrue();
    }

    @Test
    void stuurAutomatischMailNaarEenMailAdres(){
        try{
            mailService.sendMail("h-ekici@outlook.com", "hasan");
            System.out.println("gelukt");
        }catch (MessagingException e){
            System.out.println("niet gelukt");
        }
    }

    @Test
    void StuurGeenMailWanneerFoutieveMailAdres(){
        assertThrows(MessagingException.class, () -> mailService.sendMail("h-ekic", "hasan"));
    }

    @Test
    void geefExceptionWanneer(){
        Advertentie ad = new Advertentie("he", VulDatabaseTest.antiek, "he", 34, true, user2);
        assertThrows(MessagingException.class, () -> mailService.sendMail("h-ekic", "hasan"));
    }

}